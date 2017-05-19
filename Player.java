import java.io.*;
import java.util.*;
import cs1.Keyboard;
public class Player{

    int _gamesWon;
    int _gamesPlayed;
    int _score;
    boolean _won;
    ArrayList<Card> _hand;
    String _name;

    public Player(){
	_gamesWon = 0;
	_gamesPlayed = 0;
	_score = 0;
	_won = false;
	_hand = new ArrayList<Card>();
	_name = "";
    }

    public Player(int gW, int gP, int s, String name){
	this();
	_gamesWon = gW;
	_gamesPlayed = gP;
	_score = s;
	_name = name;
    }

    //--------------------------- vv Accessors vv -----------------------

    public String getName(){
	return _name;
    }

    public int getGamesWon(){
	return _gamesWon;
    }

    public int getScore(){
	return _score;
    }

    public int getGamesPlayed(){
	return _gamesPlayed;
    }
    
    //--------------------------- ^^ Accessors ^^ -----------------------

    public ArrayList<Card> drawCard(){
	_hand.add(ClassicUno._deck.dequeue());
	return _hand;
    }

    /*  public Card playCard(){
    }

    public String callUno(){
	_won = true;
    }
    */
    /*public String toString(){
	String ret = "";
	for (Card x : _hand){
	    ret += x;
	}
	return ret;
	}*/
}
