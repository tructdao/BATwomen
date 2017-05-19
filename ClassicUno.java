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

    
    public void newGame(){
	
    }//ends newGame
    
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
	int num= Keyboard.readInt();
	while( num <= 1 || num >= 54 ) { // idk i put 54 but what should the max # of players be?
		num = Keyboard.readInt() ;
	}
	return num;
    }//ends numPlayers
    
    public String sortRank(){
	return "";
    }//ends sortRank()
    
    public ArrayList<Card> deal(){
	return new ArrayList<Card>();
    }//ends deal()
    
    public boolean match(Card other){
	return false;
    }//ends match

    public static void main(String[] args){
    }//ends main
}//ends class
