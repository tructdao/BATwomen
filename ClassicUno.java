import java.io.*;
import java.util.*;
import cs1.Keyboard;
import DLL.*;
import RQueue.*;
import Stack.*;
public class ClassicUno{
    public static RQueue<Card> _deck;
    private LLNode<Player> _players;
    private Stack<Card> _discard;

    public ClassicUno(){
	_deck= new RQueue<Card>();
	_players= new LLNode<Player>();
	_discard= new Stack<Card>();
    }//ends constructor

    
    public void newGame(){
	
    }//ends newGame
    
    public String chooseVersion(){
    }//ends chooseVersion

    
    public String playerName(){
	System.out.println("Hello player. What's your name?");
	String nombre= Keyboard.readString();
	return nombre;
    }//ends playerName()

    public int numPlayers(){
	System.out.println("How many players will be playing this round of UNO");
	int num= Keyboard.readInt();
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
