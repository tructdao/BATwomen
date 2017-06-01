import java.io.*;
import java.util.*;
import cs1.Keyboard;
public class Player{

    private ArrayList<Card> _hand;
    private String _name;
    private int times ;
    private boolean Uno ;

    public Player(){
	_hand = new ArrayList<Card>();
	_name = "";
	times = 0 ;
	Uno = false ;
    }

    public Player( String name ){
	this();
	_name = name;
    }

    //--------------------------- vv Accessors vv -----------------------

    public String getName(){
	return _name;
    }

    public ArrayList<Card> getHand(){
	return _hand;
    }

    public int getHandSize(){
	return _hand.size();
    }

    public int getTimes() {
	return times; 
    }

    public boolean getUno() {
	return Uno ;
    }
    
 //--------------------------- ^^ Accessors ^^ -----------------------


 //--------------------------- vv Mutators vv -----------------------

    public void setHand(Card card){
	_hand.add(card);
    }
    public void setName(String name){
	_name=name;
    }

    public void setTimes( int n ) {
	times = n ;
    }

    public void setUno( boolean u ) {
	Uno = u ;
    }
	
 //--------------------------- ^^ Mutators ^^ -----------------------
    public ArrayList<Card> drawCard(){
	_hand.add(ClassicUno._deck.remove(0));
	return _hand;
    }
    
    public void decideWinner(){
	if( _hand.size() == 0){
	    _won = true;
	    System.out.println("UNO!");
	}
    }

    public boolean isAI(){
	return false;
    }

    public String toString(){
        
	String ret = "\nPLAYER " + _name ;
	ret += "\nINDEX\tCARD\n" ;
	for( int n = 0 ; n < _hand.size() ; n ++ ) {
	    ret += n + "\t" + _hand.get( n ) + "\n" ;
	}
	return ret;
	
    }
}
