import java.io.*;
import java.util.*;
import cs1.Keyboard;
public class Uno{
    private RQueue<Card> _deck;
    private DLL<Player> _players;
    private Stack<Card> _discard;

    public Uno(){
	_deck= new RQueue<Card>();
	_players= new DLL<Player>();
	_discard= new Stack<Card>();
    }
    
    public String chooseVersion(){
    }
    public void newGame(){
    }
    public String sortRank(){
    }
    public ArrayList<Card> deal(){
    }
    public boolean match(Card other){
    }

    public static void main(String[] args){
    }
}
