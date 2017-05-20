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
		for( int n = 0 ; n < 2 ; n ++ ) {
			_deck.enqueue( new ActionCard( "+2", red )) ;
			_deck.enqueue( new ActionCard( "reverse", red )) ;
			_deck.enqueue( new ActionCard( "skip", red )) ;
			_deck.enqueue( new ActionCard( "+2", yellow )) ;
			_deck.enqueue( new ActionCard( "reverse", yellow )) ;
			_deck.enqueue( new ActionCard( "skip", yellow )) ;
			_deck.enqueue( new ActionCard( "+2", green )) ;
			_deck.enqueue( new ActionCard( "reverse", green )) ;
			_deck.enqueue( new ActionCard( "skip", green )) ;
			_deck.enqueue( new ActionCard( "+2", blue )) ;
			_deck.enqueue( new ActionCard( "reverse", blue )) ;
			_deck.enqueue( new ActionCard( "skip", blue )) ;
		}
		for( int n = 0 ; n < 4 ; n ++ ) {
			_deck.enqueue( new ActionCard( "+4", "" )) ;
			_deck.enqueue( new ActionCard( "wild", "" )) ;
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
		for( Player i : _players ) {
			for( int n = 0 ; n < 7 ; n ++ ) {
				( i._hand ).add( _deck.dequeue()) ;
			}
		}
		return new ArrayList<Card>();
    }//ends deal()
    
    public boolean match(Card other){
	return false;
    }//ends match
	
	public void discard() {
		
	}

}//ends class
