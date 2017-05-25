import java.io.*;
import java.util.*;
import cs1.Keyboard;
import java.util.Collections;
import java.util.LinkedList;

public class ClassicUno{
    
    public static LinkedList<Card> _deck;
    private LList<Player> _players;
    public LLStack<Card> _discard;
    public LLStack<Player> _winners;
    
    public ClassicUno(){
	_deck= new LinkedList<Card>();
	_players= new LList<Player>();
	_discard= new LLStack<Card>();
    }//ends constructor

    //------------------v-----v--Accessors------------
    public LList<Player> getPlayers(){
	return _players;
    }
    public LLStack<Card> getDiscard(){
	return _discard;
    }
    //----------------^------^--Accessors-------------

    
    
    public void newGame(){
	int v= chooseVersion();
	//	setDiscard();
	if (v==3){
	    printRules();
	}
	else if (v==1){
	    populateDeck();
	    Collections.shuffle(_deck);
	    //	    System.out.println(_deck);
	    System.out.println("Woo let's play Uno!!");
	    int nP=numPlayers();
	    for( int x=1; x<=nP; x++){
		Player newPlayer= new Player();
		newPlayer.setName(playerName());
		_players.add(newPlayer);
	    }
	    
	    setDiscard();
	    deal();
	    System.out.println("Player Hands:");
	    System.out.println(_players);
	    System.out.println("Discard Pile:");
	    System.out.println(_discard.peek());

	    takeTurns() ;
	}

	//	System.out.println("The Deck of Cards(after top card is discarded)DELETE LATER");
	//System.out.println(_deck);	

	
    }//ends newGame

    public void populateDeck() {
	for( int n = 0 ; n < 10 ; n ++ ) {
	    for( int i = 0 ; i < 2 ; i ++ ) {
		_deck.add( new NumberCard( n, "red" )) ;
		_deck.add( new NumberCard( n, "yellow" )) ;
		_deck.add( new NumberCard( n, "green" )) ;
		_deck.add( new NumberCard( n, "blue" )) ;
		
		if( n == 0 ) { // idk if works
		    break ;
		}
	    }
	}
	for( int n = 0 ; n < 2 ; n ++ ) {
	    _deck.add( new AddTwo( "red" )) ;
	    _deck.add( new Reverse( "red" )) ;
	    _deck.add( new Skip( "red" )) ;
	    _deck.add( new AddTwo( "yellow" )) ;
	    _deck.add( new Reverse( "yellow" )) ;
	    _deck.add( new Skip( "yellow" )) ;
	    _deck.add( new AddTwo( "green" )) ;
	    _deck.add( new Reverse( "green" )) ;
	    _deck.add( new Skip( "green" )) ;
	    _deck.add( new AddTwo( "blue" )) ;
	    _deck.add( new Reverse( "blue" )) ;
	    _deck.add( new Skip( "blue" )) ;
	}
	for( int n = 0 ; n < 4 ; n ++ ) {
	    _deck.add( new AddFour()) ;
	    _deck.add( new Wild()) ;
	}
    }//ends populate deck
    
    public int chooseVersion(){
	System.out.println("What version would you like to play?(int response)");
	System.out.println("\t1: Classic");
	System.out.println("\t2: Tournament");
	System.out.println("\t3: Rules");
	int vers= Keyboard.readInt();
	return vers;
    }//ends chooseVersion
    public void printRules(){
        System.out.println("UNO-Classic");
	System.out.println("At the beginning, each player is dealt 7 cards face down. The rest of the cards are placed in a draw pile. \nThen one card is flipped from the top of the draw pile and added to the discard pile. \nAs the game goes on, the goal is to match the top card of the discard pile with a card of the same color, number, or action.\n if the player has no matches or chooses not to play any of their cards they must draw a card. \n If the drawn card can be played, then play it. Otherwise it's the next player's turn.\n\n Have Fun!!");
	
    }

    
    public String playerName(){
	System.out.println("Hello, What's your name?");
	String nombre= Keyboard.readString();
	return nombre;
    }//ends playerName()

    public int numPlayers(){
	System.out.println("How many players will be playing this round of UNO(2-12)");
	int num;
	while(true){
	    num = Keyboard.readInt() ;    
	    if( num >= 2 && num <= 12 ) { 
		return num;
	    }
	    else if( num<2){
		System.out.println("You need more players!\nHow many players will be playing this round of UNO(2-12)? ");
	    }
	    else if(num>12){
		System.out.println("Too many players!\nHow many players will be playing this round of UNO(2-12)");
	    }
	}
    }//ends numPlayers
    
    public String sortRank(){
	return "";
    }//ends sortRank()
    
    public void deal(){
	for( Player i : _players ) {
	    for( int n = 0 ; n < 7 ; n ++ ) {
		i.setHand( _deck.remove(0)) ;
		//	System.out.println(i.getHand());
	    }
	}
    }//ends deal()

    public Card printDiscard(){
	return _discard.peek();
    }
    
    public void setDiscard(){
	Card top= _deck.remove(0);
	_discard.push(top);
	//return _discard;
    }
    
    public boolean match(Card other){
	if(!(other.getColor().equals(_discard.peek().getColor()))){
	    System.out.println("That's not a playable move");
	    pickCard();
	}
	else if( other.getColor().equals(_discard.peek().getColor())){//color match
	}
	
	else if(other.isNumberCard()){
	    if(other.getNumber().equals(_discard.peek())){}
	}
	else if(other.getColor().equals(_discard.peek().getColor())||//if color matches
		((NumberCard)other).getNumber() == (_discard.peek().getNumber())||// if number matches
		((ActionCard)other).getAction().equals(_discard.peek().getAction())){//if action matches
	    
	    return true;
	}
	else if (isWildCard()){
	    return true;
	}
	// cards match!
	return false ;
    }//ends match

    // player inputs index of card he/she wants to play
    public int pickCard(){ 
	System.out.println("It's your turn, what card would you like to play(index of your hand). \nIf you'd like to draw a card simply type in the word draw");
	try{
	    int x= Keyboard.readInt();
	}
	catch(Exception e){
	    int x= Integer.MAX_VALUE;
	}
	return x;
    }
    public void takeTurns(){
	while(_players.size()!=1 && _deck.size()!=0){
	    for(int n = 0 ; n < _player.size() ; n ++ ){
		Player person = _player.get( n ) ;
		System.out.println( person );
		System.out.println();
		int ind=  pickCard();
		// checks if index is out of range, and if they do, if match
		while( ( ind < 0 || ind >= person.getHandSize()) || !(match(person.getHand().get(ind))))
		    ind=pickCard();
		    if(person == Integer.MAX_VALUE){//draw
			person.setHand(_deck.remove(0));
		    }
		    else if(match(person.getHand(ind))){//if it matches push it to discard
			_discard.push(person.getHand().remove(ind));
		    }
		    
	    }
		// also, if no cards match or if a player simply wants a new card, then remove one from deck and add to player hand
	}
		/*
		if( x.getHandSize() == 2 and theres a usable card ) {
			call UNO and stuff
		}
		*/
	//	if( x.getHandSize() == 0 ) {
			// remove player from _player and i guess add it to llist of winningplayers
			// but must remove by index while this is a foreach loop :( oh noes
	//	}
		
	//	}
	//	}
    }
    /*
      public void reverse()
      LLNode temp = _players._head ;
	_players._head =_players. _tail ;
	_players._tail = temp ;
    }*/

    
}//ends class
