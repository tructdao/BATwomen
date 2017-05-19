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
    //----------------^------^--Accessors-----------
    public void newGame(){
	System.out.println("Woo let's play Uno!!");
	numPlayers();
	System.out.println("OK, what are your names");
	
    }//ends newGame

    
    
    public String chooseVersion(){
	return "";
    }//ends chooseVersion

    
    public void playerName(){
	for (int p=1; p<=numPlayers();p++){
	    System.out.println("Hello player "+ p + " What's your name?");
	    String nombre= Keyboard.readString();
	    _players.get(p-1).setName(nombre);
	}
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
