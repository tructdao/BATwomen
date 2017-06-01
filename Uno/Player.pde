import java.io.*;
import java.util.*;
import cs1.Keyboard;
   class Player{

       ArrayList<Card> _hand;
       String _name;
       int times ;
       boolean Uno ;

       Player(){
  _hand = new ArrayList<Card>();
  _name = "";
  times = 0 ;
  Uno = false ;
    }

       Player( String name ){
  this();
  _name = name;
    }

    //--------------------------- vv Accessors vv -----------------------

       String getName(){
  return _name;
    }

       ArrayList<Card> getHand(){
  return _hand;
    }

       int getHandSize(){
  return _hand.size();
    }

       int getTimes() {
  return times; 
    }

       boolean getUno() {
  return Uno ;
    }
    
 //--------------------------- ^^ Accessors ^^ -----------------------


 //--------------------------- vv Mutators vv -----------------------

       void setHand(Card card){
  _hand.add(card);
    }
       void setName(String name){
  _name=name;
    }

       void setTimes( int n ) {
  times = n ;
    }

       void setUno( boolean u ) {
  Uno = u ;
    }
  
 //--------------------------- ^^ Mutators ^^ -----------------------
       ArrayList<Card> drawCard(){
  _hand.add(ClassicUno._deck.remove(0));
  return _hand;
    }
    
       void decideWinner(){
  if( _hand.size() == 0){
      _won = true;
      System.out.println("UNO!");
  }
    }

       boolean isAI(){
  return false;
    }

       String toString(){
        
  String ret = "\nPLAYER " + _name ;
  ret += "\nINDEX\tCARD\n" ;
  for( int n = 0 ; n < _hand.size() ; n ++ ) {
      ret += n + "\t" + _hand.get( n ) + "\n" ;
  }
  return ret;
  
    }
}