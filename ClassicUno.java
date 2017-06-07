import java.io.*;
import java.util.*;
import cs1.Keyboard;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
public class ClassicUno{
    
    public static LinkedList<Card> _deck;
    private LinkedList<Player> _players;
    public Stack<Card> _discard;
    public Stack<Player> _winners;
    
    
    /*default constructor*/ 
    public ClassicUno(){
	_deck= new LinkedList<Card>();
	_players= new LinkedList<Player>();
	_discard= new Stack<Card>();
	_winners = new Stack<Player>();
    }//ends constructor

    //------------------v-----v--Accessors------------
    public LinkedList<Player> getPlayers(){
	return _players;
    }
    public Stack<Card> getDiscard(){
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
		// user input for # players
	    int nP=numPlayers();
	    for( int x=1; x<=nP; x++){
		Player newPlayer= new Player();
		// ask each player for their name and add to _players
		newPlayer.setName(playerName());
		_players.add(newPlayer);
	    }
	    setDiscard();
	    deal();
	    takeTurns();
		// leaderboard
	    printWinners() ;
	}
	else if ( v == 2 ){
	    populateDeck();
	    Collections.shuffle(_deck);
	    System.out.println( "Woo let's play Uno!!" );

	    System.out.println("What is your name?");
	    String name = Keyboard.readString();
	    _players.add( new Player( name ) );
	    _players.add( new Player( "AI" ) );
	    	    
	    setDiscard();
	    deal();
	   
	    AIUno game = new AIUno( _deck, _players, _discard );
	    game.play();
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
	    System.out.println("\t2: vs Computer Player");
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
	System.out.println( "Which Rules would you like to read?" );
	System.out.println( "1. UNO-Classic" );
	System.out.println( "2. UNO-AI mode" );
	System.out.print( "Your choice: " );
	int choice = Keyboard.readInt();
	while ( choice < 1 || choice > 2 ){
	    System.out.println( "Please choose 1 or 2!" );
	    System.out.println( "Which Rules would you like to read?" );
	    System.out.println( "1. UNO-Classic" );
	    System.out.println( "2. UNO-AI mode" );
	    System.out.print( "Your choice: " );
	    choice = Keyboard.readInt();
	}
	if (choice == 1){
	    System.out.println("UNO-Classic");
	    System.out.println("At the beginning, each player is dealt 7 " +
			       "cards face down. The rest of the cards are " +
			       "placed in a draw pile. \nThen one card is " +
			       "flipped from the top of the draw pile and " +
			       "added to the discard pile.\nAs the game goes " +
			       "on, the goal is to match the top card of the " +
			       "discard pile with a card of the same color, " +
			       "number, or action.\nIf the player has no " +
			       "matches or chooses not to play any " +
			       "of their cards they must draw a card. \n If " +
			       "the drawn card can be played, then play it." +
			       "Otherwise it's the next player's turn.\n\n" +
			       "Have Fun!!");
	}
	if (choice == 2){
	    System.out.println("UNO-AI mode");
	    System.out.println("AI Uno is a game between you and one AI " +
			       "opponent. Your objective is to defeat this AI" +
			       "before the deck runs out of cards.\nThe game " +
			       "begins with the flipping of a card from the " +
			       "deck to the discard pile. You and the AI will" +
			       " take turns trying to play cards that match " +
			       "the top card of the discard pile. A card is " +
			       "considered matching if both have the same " +
			       "color, number, and/or action.\n" +
			       "You have the option to either play a card, " +
			       "or draw a new card and try to play or pass. " +
			       "You can only draw once per turn.\n\n" +
			       "Have fun and good luck!!");
	}
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
				   "round of UNO (2-12)? ");
	    }
	    else if(num>12){
		System.out.println("Too many players!\n" +
				   "How many players will be playing this " +
				   "round of UNO (2-12)");
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
	
    // printing winners
    public void printWinners() {
	String winners = "" ;
	int n = 1 ; 
	while( !( _winners.isEmpty())) {
	    winners = "\n" + n + "\t" + _winners.pop().getName()
		+ winners ;
	    n += 1 ;
	}
	winners = "POSITION\tPLAYER\n" + winners ;
	System.out.println( winners ) ;
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
	    System.out.println("Hey player, what do you want to do?\n" +
			       _players.get(n).getName()+
			       "(int response)");
	    System.out.println("\t1: Draw (You can only draw once per round)");
	    System.out.println("\t2: Pick a card");
	    int choice= Keyboard.readInt();
	    if(choice>=1 && choice<=2){
		return choice;
	    }
	    else{
		System.out.println("Please pick choice 1 or 2 !! Nothing else!");
	    }
	}
     }//options end
    
    public int passOrPlay(int n ){
	while(true){
	    System.out.println("Hey player, " +
			       _players.get(n).getName() +
			       " what do you want to do?\n" +
			       "(int response)");
	    System.out.println("\t1: Play");
	    System.out.println("\t2: Pass");
	    int choice= Keyboard.readInt();
	    if(choice>=1 && choice<=2){
		return choice;
	    }
	    else{
		System.out.println("Please pick choice 1 or 2!! Nothing else!");
	    }
	}
    }//options end
    
    /**
     * reader input method, asks specified player(n) what card they want to play
     */
    public int pickCard(int n,Player person){
	while (true){
	    System.out.println("It's your turn, player "+
			       _players.get(n).getName() +
			       ", what card would you like to play" +
			       " (index of your hand)?\n\n");
	    System.out.println();
	    int x ;
	    x= Keyboard.readInt();
	    if ( x >= 0 && x < person.getHandSize() ){
		return x;
	    }
	    else{
		System.out.println("pick a valid card");
	    }
	}
    }
    //________vvv________HELPERS FOR TAKETURNS_____________vvv__________
	// prints hand and top of discard
    public int startingTurns(Player person,int n){
	
	System.out.println(person);
	System.out.println( "Here's the discard pile");
	System.out.println(printDiscard());
	int toDo= options(n);		
	return toDo;
    }
    
	// if draw choice is picked
    public void toDoDraw(Player person){
	person.setHand(_deck.remove(0));
	System.out.println( person ) ;
    }

    public void printSetUp(Player person){
	System.out.println(person);
	System.out.println("discard\n"+printDiscard());
    }
	
	// card is removed from player hand and added to top of discard
    public void placeCard(Player person,int ind){
	_discard.push(person.getHand().remove(ind));
    }
    
    public int chooseUno(){
	while(true){
	    System.out.println("Would you like to call UNO?\n"
			       + "  Type 1 for YES, 2 for NO: " ) ;
	    int ans= Keyboard.readInt();
	    if(ans>=1 && ans<=2){
		return ans;
	    }
	    else{
		System.out.println("choice 1 or 2 !! nothing else");
	    }
	}
    }
	
	// players get choice to call uno after playing a card
    public void oneCard( int n ) {	

	    int ans = chooseUno() ;
	    if( ans == 1 ) {
		System.out.println( ":)" ) ;
		if( _players.get( n ).getHandSize() == 1  ) {
		    _players.get( n ).setUno( true ) ;
		}
		else if(n==0){
			// if previous player has 1 card and did not call uno, 2 cards will be added to theirs
		    if(_players.get( _players.size()-1 ).getHandSize()==1&&
		       _players.get(_players.size()-1).getUno()==false){
			_players.get(_players.size()-1).setHand( _deck.remove(0)) ;
			_players.get(_players.size()-1).setHand( _deck.remove(0)) ;
			System.out.println( _players.get(_players.size()-1).getName()+ 
					    ", You just got UNO-ed");
		    }
			
		}
		else if(_players.get( n - 1 ).getHandSize() == 1 && 
			_players.get(n-1).getUno() == false ) {
		    _players.get(n-1).setHand( _deck.remove(0)) ;
		    _players.get(n-1).setHand( _deck.remove(0)) ;
		    System.out.println( _players.get(n-1).getName()+ 
					", You just got UNO-ed");
		}
	    }
    }
	
	// checks if player has no cards left AFTER player plays a valid card. if so, player is removed and pushed into _winners
	public boolean noCards( int n ) {
	    if( _players.get( n ).getHandSize() == 0 ) {
		_winners.push( _players.remove( n )) ;
		System.out.println( "Player " + _players.get(n).getName() + ", you have no more cards left :)." );
		return true ;
	    }
	    
	    return false ;
	}
	
    public void afterDrawing(Player person, int ind, int n){

	if(ind>=person.getHand().size()){
	    System.out.println("that doesn't work");
	}
    }
	
	// if the first ever card in the discard is a +4
    public boolean firstDiscard(int discardSize){
	return discardSize==1 &&(_discard.peek().getSymbol().equals( "wild" )|| _discard.peek().getSymbol().equals("+4"));
    }
	    

    /**
     * loops through the list of players and asks them what they'd like to do.
     * if the move is legal then play it, otherwise ask them again. 
     */
    public void takeTurns(){
	int discardSize=1;
	while(_players.size()!=1 && _deck.size()!=0){
	    int n = 0 ;
	    while (n<_players.size()){
		Player person = _players.get( n ) ;
		// ask player if they want to 1: draw or 2: play
		int toDo = startingTurns(person, n);
		int ind ;
		// draw is picked
		if(toDo == 1 && person.getTimes() < 1){//will only allow drawing once
		    toDoDraw( person);
			// player has drawed 1 time
		    person.setTimes( person.getTimes() + 1 ) ;
			// choices for pass or play
		    int p = passOrPlay( n ) ;
		    if(p==2){ // pass
			person.setTimes(0);
			n+=1; } // move onto next player 
			// play
		    else if (p==1){toDo = 5 ; }
		}
		// play is picked initially or after drawing
		if(toDo==2 || toDo == 5 ){ // if player chose to play after drawing
		    System.out.println();
		    printSetUp(person);
		    ind= pickCard(n,person); 
		    afterDrawing( person, ind,  n);
		    // if the FIRST ever discard card is a wild black
		    // necessary bc there is no person to set its color
		    if(firstDiscard(discardSize )) {
				placeCard( person, ind ) ;
				discardSize+=1;
				oneCard( n ) ;
				person.setTimes(0);
				n += 1 ;
		    }
		    //incorporates wild
		    else if( ind < person.getHand().size() && 
			     person.getHand().get( ind ).getSymbol().equals( "wild" ) ) {
				placeCard( person, ind ) ;
				// ask player for color choice
				_discard.peek().setColor() ;
				person.setTimes(0);
				if( !(noCards( n ))) { 
				// there must be >= 1 cards. ask if they want to call uno
				oneCard( n ) ;
				n += 1 ;}
		    }
		    //incorporating skip--tested
		    else if(skipTurn(person.getHand().get(ind) , person , ind)){
			if(n==_players.size()-1){ // if last player...
			    person.setTimes(0);
			    placeCard(person,ind);
			    discardSize+=1;
				// if this player wins, it'll be removed so no need to *skip* b/c players will be shifted down 
			    if( noCards( n )) {n = 0 ; }
				else {
					oneCard( n ) ;
					n = 1 ;}
			}
			else if(n==_players.size()-2){
			    person.setTimes(0);
			    placeCard(person,ind);
			    discardSize+=1;
			    if( noCards( n )) {n = _players.size() - 1 ; }
			    else {
					oneCard( n ) ;
					n=0;
				}
			}
			else{
			    person.setTimes(0);
			    placeCard( person, ind ) ;
			    discardSize+=1;   
			    if( noCards( n )) {n += 1 ; }
				else {
			    oneCard( n ) ; 
				n += 2 ;}
			}
		    }
		    //incorporating +2--tested
		    else if(addTwoCheck(person.getHand().get(ind) , person , ind)){
			if(n==_players.size()-1){ drawTwo(-1);}
			else{ drawTwo(n);}
			placeCard( person, ind ) ;
			discardSize+=1;	
			person.setTimes(0);
			
			if( !(noCards( n ))) {
				oneCard( n ) ;
			    n += 1 ;
			}
		    }
		    //incorporating reverse
		    else if(reverseCheck(person.getHand().get(ind) , person , ind)){
		        Collections.reverse(_players);
			n= _players.size()-1-n;
			person.setTimes(0);
			placeCard(person,ind);	
			discardSize+=1;
			if( !(noCards( n ))) {
				oneCard( n ) ;
			    n += 1 ;
			}
		    }
		    //incorporating +4
		    else if(addFourCheck(person.getHand().get(ind) , person , ind)){
			if(n==_players.size()-1){
			    drawFour(-1);
			    person.getHand().get(ind).setColor();
			}
			else{
			    drawFour(n);
			    person.getHand().get(ind).setColor();
			}
			placeCard( person, ind ) ;
			discardSize+=1;
			person.setTimes(0);
			if( !(noCards( n ))) {
				oneCard( n ) ;
			    n += 1 ;
			}
		    }
			// doesn't match, out of range
		    else if (wrongMove(person,ind)){
			System.out.println("That move doesn't work!" +
					   "Try picking another card or draw");
		    }
		    else if (match(person.getHand().get(ind))){
			placeCard(person,ind);
			discardSize+=1;
			person.setTimes(0);
			if( !(noCards( n ))) {
				oneCard( n ) ;
			    n += 1 ;
			}
		    }	    
		}
	    }
	}
	System.out.println( "The game has ended.\n" ) ;
	// there may be multiple remaining players after game ended - like if there are no more cards in deck.
	for( int i = 0 ; i < _players.size() ; i ++ ) {
		_winners.push( _players.remove( i )) ;
	}
	
    }
    public void drawTwo(int n){
	_players.get(n+1).setHand(_deck.remove(0));
	_players.get(n+1).setHand(_deck.remove(0));
    }
    public void drawFour(int n){
	_players.get(n+1).setHand(_deck.remove(0));
	_players.get(n+1).setHand(_deck.remove(0));
	_players.get(n+1).setHand(_deck.remove(0));
	_players.get(n+1).setHand(_deck.remove(0));
    }
    
    public boolean wrongMove(Player person, int ind){
	return ind>=person.getHand().size() || !(match(person.getHand().get(ind)));
    }
    public boolean skipTurn(Card playedCard, Player person, int ind){
	return ind<person.getHand().size() && match(person.getHand().get(ind))&& (playedCard).getSymbol().equals("skip");
    }
    public boolean addTwoCheck(Card playedCard, Player person, int ind){
	return ind<person.getHand().size() && match(person.getHand().get(ind))&& (playedCard).getSymbol().equals("+2");
    }
    public boolean reverseCheck(Card playedCard,Player person, int ind){
	return ind<person.getHand().size() && match(person.getHand().get(ind))&& (playedCard).getSymbol().equals("reverse");
    }
    public boolean addFourCheck(Card playedCard,Player person, int ind){
	return	ind<person.getHand().size()&& match(person.getHand().get(ind))&&(playedCard).getSymbol().equals("+4");
    }
    public boolean wildCheck(Card playedCard){
	return (playedCard).getSymbol().equals("wild");
    }
//----------------^------^--Playing-------------		   		    
}



