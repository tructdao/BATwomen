import java.io.*;
import java.util.*;
import cs1.Keyboard;
public class Player{

    //private int _gamesWon;
    //private  int _gamesPlayed;
    //private int _score;
    //private boolean _won;
    private ArrayList<Card> _hand;
    private String _name;

    public Player(){
	//	_gamesWon = 0;
	//_gamesPlayed = 0;
	//_score = 0;
	_won = false;
	_hand = new ArrayList<Card>();
	_name = "";
    }

    public Player(int gW, int gP, int s, String name){
	this();
	//_gamesWon = gW;
	//_gamesPlayed = gP;
	//_score = s;
	_name = name;
    }

    //--------------------------- vv Accessors vv -----------------------

    public String getName(){
	return _name;
    }
    /*
    public int getGamesWon(){
	return _gamesWon;
    }

    public int getScore(){
	return _score;
    }

    public int getGamesPlayed(){
	return _gamesPlayed;
	}*/
    public ArrayList<Card> getHand(){
	return _hand;
    }

    public int getHandSize(){
	return _hand.size();
    }
    
 //--------------------------- ^^ Accessors ^^ -----------------------


 //--------------------------- vv Mutators vv -----------------------
    /* public void setGamesWon(int gamesWon){
	_gamesWon=gamesWon;
    }
    public void setGamesPlayed(int gamesPlayed){
	_gamesPlayed=gamesPlayed;
    }
    public void setScore(int score){
	_score=score;
    }
    public void setWin(boolean won){
	_won=won;
	}*/
    public void setHand(Card card){
	_hand.add(card);
    }
    public void setName(String name){
	_name=name;
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

    public String toString(){
        
	String ret = "\nPLAYER " + _name ;
	ret += "\nINDEX\tCARD\n" ;
	for( int n = 0 ; n < _hand.size() ; n ++ ) {
		ret += n + "\t" + _hand.get( n ) +"\n" ;
	}
	
	return ret;
    }
}
