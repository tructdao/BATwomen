import java.io.*;
import java.util.*;
import cs1.Keyboard;

public class ClassicUno{
    public static RQueue<Card> _deck;
    private LList<Player> _players;
    private LLStack<Card> _discard;
    
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
	numPlayers();
	
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
    }
    
    public String chooseVersion(){
	return "";
    }//ends chooseVersion

    
    public String playerName(){
	System.out.println("Hello player. What's your name?");
	String nombre= Keyboard.readString();
	return nombre;
    }//ends playerName()

    public int numPlayers(){
	System.out.println("How many players will be playing this round of UNO");
	int num;
	while(true){
	    num = Keyboard.readInt() ;    
	    if( num >= 2 || num <= 12 ) { 
		return num;
	    }
	    else if( num<2){
		System.out.println("You need more players!");
	    }
	    else if(num>12){
		System.out.println("Too many players!");
	    }
	}
    }//ends numPlayers
    
    public String sortRank(){
	return "";
    }//ends sortRank()
    
    public ArrayList<Card> deal(int indexOfPlayer){
	return new ArrayList<Card>();
    }//ends deal()
    
    public boolean match(Card other){
	return false;
    }//ends match

}//ends class
