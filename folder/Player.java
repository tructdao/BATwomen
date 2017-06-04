import java.io.*;
import java.util.*;
import cs1.Keyboard;
public class Player{

    private ArrayList<Card> _hand;
    private String _name;
    private int times ;
    private boolean Uno ;
    private boolean _isAI;

    public Player(){
	_hand = new ArrayList<Card>();
	_name = "";
	times = 0 ;
	Uno = false ;
	_isAI=false;
    }

    public Player( String name ){
	this();
	_name = name;
	if(name.equals("AI")){
	    _isAI=true;
	}
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

    public Card getCard( int index ){
	return _hand.get( index );
    }
    public boolean getAI(){
	return _isAI;
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

    public Card removeCard( int index ){
	return _hand.remove( index );
    }
	
 //--------------------------- ^^ Mutators ^^ -----------------------
    public ArrayList<Card> drawCard(){
	_hand.add(ClassicUno._deck.remove(0));
	return _hand;
    }
    
    public void decideWinner(){
	if( _hand.size() == 0){
	    // _won = true;
	    System.out.println("UNO!");
	}
    }

    public String toString(){
        
	String ret = "\nPLAYER " + _name ;
	ret += "\nINDEX\tCARD\n" ;
	for( int n = 0 ; n < _hand.size() ; n ++ ) {
	    ret += n + "\t" + _hand.get( n ) + "\n" ;
	}
	return ret;
    }

    //--------------------- vv AI METHODS vv --------------------

    public int turn( Card top ){
	for (int i = 0; i < _hand.size(); i++ ){
	    if ( _hand.get( i ).match( top ) ){
		//play( i );
		return i;
	    }
	}
	System.out.println("No playable cards, drawing a card now.");
	return -1;
	//DRAW CARD
    }

    public Card play( int index ){
	return _hand.remove( index );
    }

    //--------------------- ^^ AI METHODS ^^ --------------------

}
