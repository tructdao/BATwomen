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
    
    
    /*default constructor*/ 
    public ClassicUno(){
	_deck= new LinkedList<Card>();
	_players= new LList<Player>();
	_discard= new LLStack<Card>();
	_winners = new LLStack<Player>();
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
	if (v==3){
	    printRules();
	    newGame();
	}
	else if (v==1){
	    populateDeck();
	    Collections.shuffle(_deck);
	    System.out.println("Woo let's play Uno!!");
	    int nP=numPlayers();
	    for( int x=1; x<=nP; x++){
		Player newPlayer= new Player();
		newPlayer.setName(playerName());
		_players.add(newPlayer);
	    }
	    
	    setDiscard();
	    deal();
	    // System.out.println("Discard Pile:");
	    //System.out.println(_discard.peek());
	    takeTurns();
	}
    }//ends newGame

     //------------------v-----v--Setting the Game Up------------

    /**
     * populateDeck() adds 108 cards to the deck:
     * RGBY in each color:
     * 1 zero, 2 of each 1-9, 1 wild, 1 plus4, 2 of each reverse, skip, plus2
     * (108 cards) 
     */

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

     /**
      * chooseVersion() returns the user input for which choice they would like to complete. 
      * Classic: classic game of uno
      * tournament: winner moves on to another round
      * rules: prints out the rules and then asks the user to choose the version again
      */

    public int chooseVersion(){
	System.out.println("What version would you like to play?" +
			   "(int response)");
	System.out.println("\t1: Classic");
	System.out.println("\t2: Tournament");
	System.out.println("\t3: Rules");
	int vers= Keyboard.readInt();
	return vers;
    }//ends chooseVersion

    /**
     * printRules() will be run only if the user inputs three for chooseVersion()
     */

    public void printRules(){
        System.out.println("UNO-Classic");
	System.out.println("At the beginning, each player is dealt 7 cards " +
			   "face down. The rest of the cards are placed in a " +
			   "draw pile. \nThen one card is flipped from the " +
			   "top of the draw pile and added to the discard " +
			   "pile. \nAs the game goes on, the goal is to match" +
			   " the top card of the discard pile with a card of " +
			   "the same color, number, or action.\nIf the " +
			   "player has no matches or chooses not to play any " +
			   "of their cards they must draw a card. \n" +
			   "If the drawn card can be played, then play it. " +
			   "Otherwise it's the next player's turn.\n\n" +
			   "Have Fun!!");
    }

    /**
     * playerName() is a helper method to get the user's names 
     * useful for adding players with names into the _players instance var
     */
    public String playerName(){
	System.out.println("Hello, What's your name?");
	String nombre= Keyboard.readString();
	return nombre;
    }//ends playerName()

    /**
     * numPLayers() returns a user input of an int between 2-12 for number of players playing
     */
    public int numPlayers(){
	System.out.println("How many players will be playing this round " +
			   "of UNO(2-12)");
	int num;
	while(true){
	    num = Keyboard.readInt() ;    
	    if( num >= 2 && num <= 12 ) { 
		return num;
	    }
	    else if( num<2){
		System.out.println("You need more players!\n" +
				   "How many players will be playing this " +
				   "round of UNO(2-12)? ");
	    }
	    else if(num>12){
		System.out.println("Too many players!\n" +
				   "How many players will be playing this " +
				   "round of UNO(2-12)");
	    }
	}
    }//ends numPlayers

    /**
     * when setting up the game it deals 7 cards to each player by removing the cards from the deck
     */
    public void deal(){
	for( Player i : _players ) {
	    for( int n = 0 ; n < 7 ; n ++ ) {
		i.setHand( _deck.remove(0)) ;
	
	    }
	}
    }//ends deal()
    /**
     * removes a card from the deck and adds it to the _discard stack
     */
    public void setDiscard(){
	Card top= _deck.remove(0);
	_discard.push(top);

    }

     //----------------^------^--Setting the Game Up-------------

    //------------------v-----v--Playing------------
    
    /**
     * prints the top card of the discard pile. 
     */
    public Card printDiscard(){
	return _discard.peek();
    }
  
    
    /**
     * compares top card of _discard with param other
     * returns true if cards match color, symbol, number, or is a wild card
     * false otherwise
     */
    public boolean match(Card other){
       
        if( other.isNumberCard() && _discard.peek().isNumberCard()) {
	    // num == num
	    if(((NumberCard  )other).getNumber() == ((NumberCard)_discard.peek()).getNumber()) {
		return true ;
	    }
	    // color == color for num cards
	    else if( other.getColor().equals(_discard.peek().getColor())) {
		return true ;
	    }
	    
	    return false ;
	}
	else if(other.isActionCard() && _discard.peek().isActionCard()) {
	    // color == color for action cards
	    if(other.getColor() == _discard.peek().getColor()) {
		return true ;
	    }  
	    // action == action
	    else if(((ActionCard) other).getAction().equals( ((ActionCard)_discard.peek()).getAction())) {
		return true ;
	    }
	}

	else if( ((ActionCard)other).getAction().equals("colorSwitch")
		 ||((ActionCard) other).getAction().equals( "draw4")) {
	    return true ;
	}
	//color equals color for any card
	else if(other.getColor().equals(_discard.peek().getColor())){
	    return true;
	}
	else {
	    return false ;
	}
	return false;
    }//ends match
    
    /**
     * reader input method, asks specified player(n) what card they want to play
     */
    public int pickCard(int n){ 
	System.out.println("It's your turn player "+ _players.get(n).getName() +
			   ", what card would you like to play" +
			   "(index of your hand). \nIf you'd like to draw a " +
			   "card type 1000");
	System.out.println();
	//	System.out.println("DISCARD PILE");
	//System.out.println(printDiscard());
	int x ;
	x= Keyboard.readInt();
	/*	try{
	    x=Keyboard.readInt();
	}
	catch(Exception e){
	    x= Integer.MAX_VALUE;
	    }*/
	return x;
    }
    
    
    /**
     * loops through the list of players and asks them what they'd like to do.
     * if the move is legal then play it, otherwise ask them again. 
     */
    public void takeTurns(){
	while(_players.size()!=1 && _deck.size()!=0){
	    for(int n = 0 ; n < _players.size() ; n ++ ){
		Player person = _players.get( n ) ;
		System.out.println( person );
		System.out.println();
		System.out.println("Here's the discard pile \n"+ printDiscard());
		
		// 1st conditional checks if its an actioncard, so it's safe to classcast it afterwards for 2nd conditional
		if( _discard.peek().isActionCard() && ((ActionCard) _discard.peek()).getAction().equals( "skip" )) {
			// do nothing?
		}
		else {
		    int ind =  pickCard(n);
		    if(ind == 1000){//draw
			person.setHand(_deck.remove(0));
		    }
		    else if(match(person.getHand().get(ind))){
			//if it matches push it to discard
			_discard.push(person.getHand().remove(ind));//removes from hand and adds to discard pile
			/*
			  if(skipTurn(person.getHand().get(ind))){		
			  if(n==_players.size()-1){
			  System.out.println("it's player at index 1's turn");
			  n=1;
			  }
			  else{
			  System.out.println("skip the next person");
			  n+=1;
			  }
			  }
			*/
			
		    }
		    else{
			System.out.println("That move doesn't work!" +
					   "Try picking another card or draw");
			pickCard(n);
		    }   
		}
	    }
	}
    }


    public boolean skipTurn(Card playedCard){
	return ((ActionCard)playedCard).getAction().equals("skip");
    }
    /*
    public void skipTurn(Card playedCard){
	if(((ActionCard)playedCard).getAction().equals("skip")){
	    System.out.println("your turn was skipped");
	}
    }
    */
    /*
      public void reverse(Card playedCard){
	if(((ActionCard)playedCard).getAction().equals("reverse")){
	}
    }
    
    public void addTwoCards(Card playedCard){
	if(((ActionCard)playedCard).getAction().equals("draw2")){
	}
    }

    public void addFourCards(Card playedCard){
	if(((ActionCard)playedCard).getAction().equals("draw4")){
	}
    }

    public void playWild(Card playedCard){
	if(((ActionCard)playedCard).getAction().equals("colorSwitch")){
	}
    }
      public String sortRank(){
	return "";
    }//ends sortRank()
    */
}


 //----------------^------^--Playing-------------		   		
/*
if( person.getHand().get(ind).(ActionCard)getAction().equals("skip")){
			n+=1;
		    }
		    else if(person.getHand().get(ind).(ActionCard)getAction().equals("reverse")){
		    }
		    else if(person.getHand().get(ind).(ActionCard)getAction().equals("draw2")){
			person.setHand(_deck.remove(0));
			person.setHand(_deck.remove(0));
		    }
================================================================

Put it in another method. 
	if( x.getHandSize() == 2 and theres a usable card ) {
	    // call UNO and stuff
	    }*/
	    
	    /*	if( x.getHandSize() == 0 ) {
	    //remove player from _player and i guess add it to llstack of winning players
	    _winners.push( _players.remove( n )) ;
    

    /*
      public void reverse()
      LLNode temp = _players._head ;
	_players._head =_players. _tail ;
	_players._tail = temp ;
    }*/

    

