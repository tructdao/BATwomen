import java.io.*;
import java.util.*;
import cs1.Keyboard;
import java.util.Collections;
import java.util.LinkedList;

class ClassicUno{
    
    static LinkedList<Card> _deck;
    LinkedList<Player> _players;
    LLStack<Card> _discard;
    LLStack<Player> _winners;
    
    
    /*default constructor*/ 
    ClassicUno(){
      _deck= new LinkedList<Card>();
      _players= new LinkedList<Player>();
      _discard= new LLStack<Card>();
      _winners = new LLStack<Player>();
    }//ends constructor

    //------------------v-----v--Accessors------------
    LinkedList<Player> getPlayers(){
      return _players;
    }
    LLStack<Card> getDiscard(){
      return _discard;
    }
    //----------------^------^--Accessors-------------

    void newGame(){
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
        printWinners() ;
      }
    }//ends newGame

     //------------------v-----v--Setting the Game Up------------

    /**
     * populateDeck() adds 108 cards to the deck:
     * RGBY in each color:
     * 1 zero, 2 of each 1-9, 1 wild, 1 plus4, 2 of each reverse, skip, plus2
     * (108 cards) 
     */

    void populateDeck() {
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
      _deck.add( new Card( "+2", "red" )) ;
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

    int chooseVersion(){
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

    void printRules(){
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
    String playerName(){
  System.out.println("Hello, What's your name?");
  String nombre= Keyboard.readString();
  return nombre;
    }//ends playerName()

    /**
     * numPLayers() returns a user input of an int between 2-12 for number
         of players playing
     */
    int numPlayers(){
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
    void deal(){
  for( Player i : _players ) {
      for( int n = 0 ; n < 2 ; n ++ ) {
    i.setHand( _deck.remove(0)) ;
    
      }
  }
    }//ends deal()
    /**
     * removes a card from the deck and adds it to the _discard stack
     */
    void setDiscard(){
  Card top = _deck.remove(0);
  _discard.push(top);
  
    }
  
  // printing winners
  void printWinners() {
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
    Card printDiscard(){
  return _discard.peek();
    }
  
    
    /**
     * compares top card of _discard with param other
     * returns true if cards match color, symbol, number, or is a wild card
     * false otherwise
     */
    boolean match(Card other){

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
      


     int options(int n ){
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
    
    int passOrPlay(int n ){
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
    int pickCard(int n){ 
  System.out.println("It's your turn, player "+ _players.get(n).getName() +
         ", what card would you like to play" +
         " (index of your hand)?\n\n");
  System.out.println();
  int x ;
  x= Keyboard.readInt();
  return x;
    }
    //________vvv________HELPERS FOR TAKETURNS_____________vvv__________
    int startingTurns(Player person,int n){
  
  System.out.println(person);
  System.out.println( "Here's the discard pile");
  System.out.println(printDiscard());
  int toDo= options(n);    
  return toDo;
    }
    
    void toDoDraw(Player person){
  person.setHand(_deck.remove(0));
  // times++ ; // this line doesnt affect the times local var in takeTurns()
  System.out.println( person ) ;
    }

    void printSetUp(Player person){
  System.out.println(person);
  System.out.println("discard\n"+printDiscard());
    }
    void placeCard(Player person,int ind){
  _discard.push(person.getHand().remove(ind));
    }
    
    int chooseUno(){
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
    void oneCard( int n ) {
  //  if( person.getHandSize() == 1 ) {
  
      // System.out.println( "Would you like to call UNO? Type 1 for YES, 2 for NO:" ) ;
      int ans = chooseUno() ;
      if( ans == 1 ) {
    System.out.println( ":)" ) ;
    if( _players.get( n ).getHandSize() == 1  ) {
        _players.get( n ).setUno( true ) ;
    }
    else if(_players.get( n - 1 ).getHandSize() == 1 && 
      _players.get(n-1).getUno() == false ) {
        _players.get(n-1).setHand( _deck.remove(0)) ;
        _players.get(n-1).setHand( _deck.remove(0)) ;
        System.out.println( _players.get(n-1).getName()+ 
          ", You just got UNO-ed");
    }

    /*
      else { // meaning anything other than 1. doesnt have to be 2 only
    System.out.println( ":(\nTwo cards added." ) ;
    person.setHand( _deck.remove( 0 )) ;
    person.setHand( _deck.remove( 0 )) ;
      }
    */
  
      }
    }
  
  boolean noCards( int n ) {
      if( _players.get( n ).getHandSize() == 0 ) {
    _winners.push( _players.remove( n )) ;
    return true ;
      }
      
      return false ;
  }
    
    /**
     * loops through the list of players and asks them what they'd like to do.
     * if the move is legal then play it, otherwise ask them again. 
     */
    void takeTurns(){
  int discardSize=1;
  while(_players.size()!=1 && _deck.size()!=0){
      int n = 0 ;
      while (n<_players.size()){
    Player person = _players.get( n ) ;
    int toDo = startingTurns(person, n);
    int ind ;
    if(toDo == 1 && person.getTimes() < 1){//will only allow drawing once
        toDoDraw( person);
        person.setTimes( person.getTimes() + 1 ) ;
        
        int p = passOrPlay( n ) ;
        if(p==2){ // pass
      person.setTimes(0);
      n+=1;
        }
        else if (p==1){ // play
      toDo = 5 ;
        }
    }
    if(toDo==2 || toDo == 5 ){ // if player chose to play after drawing
        System.out.println();
        printSetUp(person);
        ind= pickCard(n); 
        if(ind>=person.getHand().size()){
      System.out.println("that doesn't work");
        }
        // if the FIRST ever discard card is a wild black
        // necessary bc there is no person to set its color
        if( discardSize==1&&
      (_discard.peek().getSymbol().equals( "wild" )||
       _discard.peek().getSymbol().equals("+4"))) {
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
      _discard.peek().setColor() ;
      oneCard( n ) ;
      person.setTimes(0);
      if( !(noCards( n ))) {
          n += 1 ;
      }
        }
        //incorporating skip--tested
        else if(ind<person.getHand().size() && 
          match(person.getHand().get(ind))&&
          skipTurn(person.getHand().get(ind))){
      
      if(n==_players.size()-1){
          // System.out.println("last ind so player at ind 1 goes COMMENT OUT LATER");
          person.setTimes(0);
          placeCard(person,ind);
          oneCard( n ) ;
          discardSize+=1;
          
          if( noCards( n )) {
          n = 0 ;
          }
        else {
          n = 1 ;
        }
      }
      else if(n==_players.size()-2){
          // System.out.println("2nd to last so player at ind 0 COMMENT OUT LATER");
          person.setTimes(0);
          
          placeCard(person,ind);
          discardSize+=1;
          oneCard( n ) ;
          
          if( noCards( n )) {
          n = _players.size() - 1 ;
          }
        else {
          n=0;
        }
      }
      else{
          // System.out.println("just increment by 1 CO L8R");
          person.setTimes(0);
          placeCard( person, ind ) ;
          discardSize+=1;
          oneCard( n ) ;
          
          if( noCards( n )) {
          n += 1 ;
          }
        else {
          n += 2 ;
        }
      }
        }
        //incorporating +2--tested
        else if(ind<person.getHand().size() && 
          match(person.getHand().get(ind))&&
          addTwoCheck(person.getHand().get(ind))){
      
      if(n==_players.size()-1){
          // System.out.println("last ind so player at ind 0 gets the cards COMMENT OUT LATER");
          _players.get(0).setHand(_deck.remove(0));
          _players.get(0).setHand(_deck.remove(0));
      }
      else{
          // System.out.println("justadd 2 to the next person 1 CO L8R");
          _players.get(n+1).setHand(_deck.remove(0));
          _players.get(n+1).setHand(_deck.remove(0));
      }
      placeCard( person, ind ) ;
      discardSize+=1;          
      oneCard( n ) ;
      person.setTimes(0);
      
      if( !(noCards( n ))) {
          n += 1 ;
      }
        }
        //incorporating reverse
        else if(ind<person.getHand().size() && 
          match(person.getHand().get(ind))&&
          reverseCheck(person.getHand().get(ind))){
            Collections.reverse(_players);
      n= _players.size()-1-n;
      person.setTimes(0);
      placeCard(person,ind);  
      discardSize+=1;
      oneCard( n ) ;
      
      if( !(noCards( n ))) {
          n += 1 ;
      }
        }
        //incorporating +4
        else if(ind<person.getHand().size() && 
          match(person.getHand().get(ind))&&
          addFourCheck(person.getHand().get(ind))){
      
      if(n==_players.size()-1){
          // System.out.println("last ind so player at ind 0 gets the Four cards COMMENT OUT LATER");
          _players.get(0).setHand(_deck.remove(0));
          _players.get(0).setHand(_deck.remove(0));
          _players.get(0).setHand(_deck.remove(0));
          _players.get(0).setHand(_deck.remove(0));
          person.getHand().get(ind).setColor();
      }
      else{
          // System.out.println("justadd 2 to the next person 1 CO L8R");
          _players.get(n+1).setHand(_deck.remove(0));
          _players.get(n+1).setHand(_deck.remove(0));
          _players.get(n+1).setHand(_deck.remove(0));
          _players.get(n+1).setHand(_deck.remove(0));
          person.getHand().get(ind).setColor();
      }
      
      placeCard( person, ind ) ;
      discardSize+=1;
      oneCard( n ) ;
      person.setTimes(0);
      
      if( !(noCards( n ))) {
          n += 1 ;
      }
        }
        else if ( ind>=person.getHand().size() ||
            !(match(person.getHand().get(ind)))){
      System.out.println("That move doesn't work!" +
             "Try picking another card or draw");
        }
        else if (match(person.getHand().get(ind))){
      placeCard(person,ind);
      discardSize+=1;
      oneCard( n ) ;
      person.setTimes(0);
      
      if( !(noCards( n ))) {
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
    
    
    boolean skipTurn(Card playedCard){
  return (playedCard).getSymbol().equals("skip");
    }
    boolean addTwoCheck(Card playedCard){
  return (playedCard).getSymbol().equals("+2");
    }
    boolean reverseCheck(Card playedCard){
  return (playedCard).getSymbol().equals("reverse");
    }    
    boolean addFourCheck(Card playedCard){
  return (playedCard).getSymbol().equals("+4");
    }
    boolean wildCheck(Card playedCard){
  return (playedCard).getSymbol().equals("wild");
    }
    
}


//----------------^------^--Playing-------------           
/*
  Put it in another method. 
  if( x.getHandSize() == 2 and theres a usable card ) {
  // call UNO and stuff
  }*/

/*  if( x.getHandSize() == 0 ) {
//remove player from _player and i guess add it to llstack of winning players
_winners.push( _players.remove( n )) ;
*/