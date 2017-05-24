import java.io.*;
import java.util.*;
import cs1.Keyboard;
import java.util.Collections;
import java.util.LinkedList;

public class ClassicUno{
    public static LinkedList<Card> _deck;
    private LList<Player> _players;
    public LLStack<Card> _discard;
    
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
	    _deck.add( new ActionCard( "+2", "red" )) ;
	    _deck.add( new ActionCard( "reverse", "red" )) ;
	    _deck.add( new ActionCard( "skip", "red" )) ;
	    _deck.add( new ActionCard( "+2", "yellow" )) ;
	    _deck.add( new ActionCard( "reverse", "yellow" )) ;
	    _deck.add( new ActionCard( "skip", "yellow" )) ;
	    _deck.add( new ActionCard( "+2", "green" )) ;
	    _deck.add( new ActionCard( "reverse", "green" )) ;
	    _deck.add( new ActionCard( "skip", "green" )) ;
	    _deck.add( new ActionCard( "+2", "blue" )) ;
	    _deck.add( new ActionCard( "reverse", "blue" )) ;
	    _deck.add( new ActionCard( "skip", "blue" )) ;
	}
	for( int n = 0 ; n < 4 ; n ++ ) {
	    _deck.add( new ActionCard( "+4", "" )) ;
	    _deck.add( new ActionCard( "wild", "" )) ;
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
	    return false ;	    
	}
	// cards match!
	return true ;
    }//ends match

    // player inputs index of card he/she wants to play
    public int pickCard(){ 
	System.out.println("It's your turn, what card would you like to play(index of your hand)");
	int x= Keyboard.readInt();
	return x;
    }
    public void takeTurns(){
	while(_players.size()!=1 && _deck.size()!=0){
	    for(Player x : _players){
		System.out.println(x);
		System.out.println();
		int ind= pickCard();
		// checks if index is out of range, and if they do, if match
		while( ( ind < 0 || ind >= x.getHandSize()) || !(match(x.getHand().get(ind)))) {
		    ind = pickCard() ;
			// also, if no cards match or if a player simply wants a new card, then remove one from deck and add to player hand
		}
		/*
		if( x.getHandSize() == 2 and theres a usable card ) {
			call UNO and stuff
		}
		*/
		_discard.push( /* add a method in Player class to remove played card */ ) ;
	    
		if( x.getHandSize() == 0 ) {
			// remove player from _player and i guess add it to llist of winningplayers
			// but must remove by index while this is a foreach loop :( oh noes
		}
		
		}
	}
    }
    /*
      public void reverse()
      LLNode temp = _players._head ;
	_players._head =_players. _tail ;
	_players._tail = temp ;
    }*/
    
}//ends class
