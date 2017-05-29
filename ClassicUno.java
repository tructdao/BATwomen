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
		_deck.add( new Card( ""+n, "red" )) ;
		_deck.add( new Card( ""+n, "yellow" )) ;
		_deck.add( new Card( ""+n, "green" )) ;
		_deck.add( new Card( ""+n, "blue" )) ;
		
		if( n == 0 ) {
		    break ;
		}
	    }
	    }
	for( int n = 0 ; n < 2 ; n ++ ) {
	    _deck.add( new Card( "+2", "red" )) ;
	    _deck.add( new Card( "reverse", "red" )) ;
	    _deck.add( new Card( "skip", "red" )) ;
	    _deck.add( new Card( "+2", "yellow" )) ;
	    _deck.add( new Card( "reverse", "yellow" )) ;
	    _deck.add( new Card( "skip", "yellow" )) ;
	    _deck.add( new Card( "+2", "green" )) ;
	    _deck.add( new Card( "reverse", "green" )) ;
	    _deck.add( new Card( "skip", "green" )) ;
	    _deck.add( new Card( "+2", "blue" )) ;
	    _deck.add( new Card( "reverse", "blue" )) ;
	    _deck.add( new Card( "skip", "blue" )) ;
	}
	for( int n = 0 ; n < 4 ; n ++ ) {
	    _deck.add( new Card("+4", "black")) ;
	    _deck.add( new Card("wild", "black")) ;
	}
    }//ends populate deck

     /**
      * chooseVersion() returns the user input for which choice they would
          like to complete. 
      * Classic: classic game of uno
      * tournament: winner moves on to another round
      * rules: prints out the rules and then asks the user to choose the
          version again
      */

    public int chooseVersion(){
	while(true){
	    System.out.println("What version would you like to play?" +
			       "(int response)");
	    System.out.println("\t1: Classic");
	    System.out.println("\t2: Tournament");
	    System.out.println("\t3: Rules");
	    int vers= Keyboard.readInt();
	    if(vers>=1 && vers<=3){
		return vers;
	    }
	    else{
		System.out.println("choice 1 or 2 or 3!! nothing else");
	    }
	}
	//return vers;	
    }//ends chooseVersion

    /**
     * printRules() will be run only if the user inputs three for
         chooseVersion()
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
     * numPLayers() returns a user input of an int between 2-12 for number
         of players playing
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
     * when setting up the game it deals 7 cards to each player by removing
         the cards from the deck
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
	Card top = _deck.remove(0);
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

	if(other.getSymbol().equals(_discard.peek().getSymbol())){
	    return true;
	}
	else if(other.getColor().equals(_discard.peek().getColor())){
	    return true;
	}
	else if(other.getColor().equals("black")){
	    return true;
	}
	return false;
    }//ends match
	    


     public int options(int n ){
	while(true){
	    System.out.println("Hey player what do you want to do?\n" +
			       _players.get(n).getName()+
			       "(int response)");
	    System.out.println("\t1: Draw");
	    System.out.println("\t2: pick a card");
	    int choice= Keyboard.readInt();
	    if(choice>=1 && choice<=2){
		return choice;
	    }
	    else{
		System.out.println("choice 1 or 2 !! nothing else");
	    }
	}
     }//options end

     public int passOrPlay(int n ){
	while(true){
	    System.out.println("Hey player what do you want to do?\n" +
			       _players.get(n).getName()+
			       "(int response)");
	    System.out.println("\t1: Play");
	    System.out.println("\t2: Pass");
	    int choice= Keyboard.readInt();
	    if(choice>=1 && choice<=2){
		return choice;
	    }
	    else{
		System.out.println("choice 1 or 2!! nothing else");
	    }
	}
     }//options end
    /**
     * reader input method, asks specified player(n) what card they want to play
     */
    public int pickCard(int n){ 
	System.out.println("It's your turn player "+ _players.get(n).getName() +
			   ", what card would you like to play" +
			   "(index of your hand).\n\n");
	System.out.println();
	int x ;
	x= Keyboard.readInt();
	return x;
    }
    //________vvv________HELPERS FOR TAKETURNS_____________vvv__________
    public int startingTurns(Player person,int n){

	System.out.println(person);
	System.out.println( "Here's the discard pile");
	System.out.println(printDiscard());
	int toDo= options(n);		
	return toDo;
    }
    
    public void toDoDraw(int times,Player person){
	person.setHand(_deck.remove(0));
	times++ ;
	System.out.println( person ) ;
    }

    public void printSetUp(Player person){
	System.out.println(person);
	System.out.println("discard\n"+printDiscard());
    }
    public void placeCard(Player person,int ind){
	_discard.push(person.getHand().remove(ind));
    }
    /**
     * loops through the list of players and asks them what they'd like to do.
     * if the move is legal then play it, otherwise ask them again. 
     */
    public void takeTurns(){
	while(_players.size()!=1 && _deck.size()!=0){
	    int n= 0;
	    while (n<_players.size()){
		Player person = _players.get( n ) ;
		int toDo=startingTurns(person,n);
		int times=0;
		if(toDo == 1 && times < 1){//draw. will only allow 1000 choice once
		    toDoDraw(times, person);
		    int p = passOrPlay( n ) ;
		   if(p==2){
		       n+=1;
		   }
		   else if (p==1){
		       printSetUp(person);
		       int ind= pickCard(n);
		   }
		}
		if(toDo==2){
		    System.out.println();
		    printSetUp(person);
		    int ind= pickCard(n);		    
		    if(ind>=person.getHand().size()){
			System.out.println("that doesn't work");
		    }
		    else if (match(person.getHand().get(ind))){
			placeCard(person,ind);
			n+=1;
		    }
		    //incorporating skip
		    else if(match(person.getHand().get(ind))&&
			    ind<person.getHand().size()&&
			    skipTurn(person.getHand().get(ind))){
			if(n==_players.size()-1){
			    System.out.println("last ind so player at ind 1 goes COMMENT OUT LATER");
				n=1;
			    }
			    else if(n==_players.size()-2){
				System.out.println("2nd to last so player at ind 0 COMMENT OUT LATER");
				n=0;
			    }
			    else{
				System.out.println("just increment by 1 CO L8R");
				n += 2 ;
				_discard.push(person.getHand().remove(ind));//removes from hand and adds to discard pile
			    }
			}
		    //incorporating +2
		    else if(match(person.getHand().get(ind))&&
			    ind<person.getHand().size()&&
			    addTwoCheck(person.getHand().get(ind))){
			if(n==_players.size()-1){
			    System.out.println("last ind so player at ind 0 gets the cards COMMENT OUT LATER");
			    _players.get(0).setHand(_deck.remove(0));
			    _players.get(0).setHand(_deck.remove(0));
			}
			else{
			    System.out.println("justadd 2 to the next person 1 CO L8R");
			    _players.get(n+1).setHand(_deck.remove(0));
			    _players.get(n+1).setHand(_deck.remove(0));
			}
		    }
		    else if ( ind>=person.getHand().size() &&
			      ( ind!=1000 || !(match(person.getHand().get(ind))))){
			System.out.println("That move doesn't work!" +
					   "Try picking another card or draw");
		    }
		}
	    }
	}
    }
    

    public boolean skipTurn(Card playedCard){
	return (playedCard).getSymbol().equals("skip");
    }
    public boolean addTwoCheck(Card playedCard){
	return (playedCard).getSymbol().equals("+2");
    }
   
}


 //----------------^------^--Playing-------------		   		
/*
Put it in another method. 
	if( x.getHandSize() == 2 and theres a usable card ) {
	    // call UNO and stuff
	    }*/
	    
	    /*	if( x.getHandSize() == 0 ) {
	    //remove player from _player and i guess add it to llstack of winning players
	    _winners.push( _players.remove( n )) ;
	    */
    

  
    

