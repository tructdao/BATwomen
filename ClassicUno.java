import java.io.*;
import java.util.*;
import cs1.Keyboard;

public class ClassicUno{
    public static RQueue<Card> _deck;
    private LList<Player> _players;
    public LLStack<Card> _discard;
    
    public ClassicUno(){
	_deck= new RQueue<Card>();
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
	System.out.println("Woo let's play Uno!!");
	int nP=numPlayers();
	for( int x=1; x<=nP; x++){
	    Player newPlayer= new Player();
	    newPlayer.setName(playerName());
	    _players.add(newPlayer);
	}
	int v= chooseVersion();
	populateDeck();
	
	//	setDiscard();
	System.out.println(_discard.peek());
	/*if (v==3){
	    printRules();
	}
	else if (v==1){
	    setDiscard();
	    System.out.println(printDiscard());
	    }*/
	//	_deck.sample();
		System.out.println(_deck);	

	
    }//ends newGame

    public void populateDeck() {
	for( int n = 0 ; n < 10 ; n ++ ) {
	    for( int i = 0 ; i < 2 ; i ++ ) {
		_deck.enqueue( new NumberCard( n, "red" )) ;
		_deck.enqueue( new NumberCard( n, "yellow" )) ;
		_deck.enqueue( new NumberCard( n, "green" )) ;
		_deck.enqueue( new NumberCard( n, "blue" )) ;
		
		if( n == 0 ) { // idk if works
		    break ;
		}
	    }
	}
	for( int n = 0 ; n < 2 ; n ++ ) {
	    _deck.enqueue( new ActionCard( "+2", "red" )) ;
	    _deck.enqueue( new ActionCard( "reverse", "red" )) ;
	    _deck.enqueue( new ActionCard( "skip", "red" )) ;
	    _deck.enqueue( new ActionCard( "+2", "yellow" )) ;
	    _deck.enqueue( new ActionCard( "reverse", "yellow" )) ;
	    _deck.enqueue( new ActionCard( "skip", "yellow" )) ;
	    _deck.enqueue( new ActionCard( "+2", "green" )) ;
	    _deck.enqueue( new ActionCard( "reverse", "green" )) ;
	    _deck.enqueue( new ActionCard( "skip", "green" )) ;
	    _deck.enqueue( new ActionCard( "+2", "blue" )) ;
	    _deck.enqueue( new ActionCard( "reverse", "blue" )) ;
	    _deck.enqueue( new ActionCard( "skip", "blue" )) ;
	}
	for( int n = 0 ; n < 4 ; n ++ ) {
	    _deck.enqueue( new ActionCard( "+4", "" )) ;
	    _deck.enqueue( new ActionCard( "wild", "" )) ;
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
    
    public void deal(int indexOfPlayer){
	for( Player i : _players ) {
	    for( int n = 0 ; n < 7 ; n ++ ) {
		( i._hand ).add( _deck.dequeue()) ;
	    }
	}
    }//ends deal()

    public Card printDiscard(){
	return _discard.peek();
    }
    
    public LLStack<Card> setDiscard(){
	Card top= _deck.dequeue();
	_discard.push(top);
	return _discard;
    }
    
    public boolean match(Card other){
	return false;
    }//ends match
    
    /*
      public void reverse(){
      LLNode temp = _players._head ;
	_players._head =_players. _tail ;
	_players._tail = temp ;
    }*/
    
}//ends class
