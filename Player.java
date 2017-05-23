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


 //--------------------------- vv Mutators vv -----------------------
    public void setGamesWon(int gamesWon){
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
    }
    public void setHand(Card card){
	_hand.add(card);
    }
    public void setName(String name){
	_name=name;
    }
	
 //--------------------------- ^^ Mutators ^^ -----------------------
    public ArrayList<Card> drawCard(){
	_hand.add(ClassicUno._deck.dequeue());
	return _hand;
    }

    /*public Card playCard( Card top ){
	System.out.println("Which card will you play?");
	System.out.println(this);
	//int playingCard = Keyboard.readInt();
	//should we use mouse clicks to choose a card?
	//after Card next is chosen....
	if ( next.isNumberCard() ){
	    if ( top.isWildCard() == true ){
	    break;
	    }
	    if ( next.getNumber() != top.getNumber() ){ //not the same number
		if ( next.getColor() != top.getColor() ){ //not the same color
		    System.out.println("INVALID CARD!!!!!")
			return playCard( Card top );
		}
	    }
	}
	//ClassicUno._discard.push(_hand.remove(playingCard));
	}*/
    
    public void decideWinner(){
	if( _hand.size() == 0){
	    _won = true;
	    System.out.println("UNO!");
	}
    }

    public void play( Card top ){
	
	System.out.println( "Card just dealt : " + top );
	
	if ( top.isActionCard() ){
	    if ( (( ActionCard )top ).getAction().equals("draw2") ){
		System.out.println("Draw 2 cards.");
		drawCard();
		drawCard();
	    }
	    else if ( (( ActionCard )top ).getAction().equals("skip") ){
		System.out.println("You've been skipped!");
		return;
	    }
	    else if ( (( ActionCard )top ).getAction().equals("reverse") ){
		//reverse in ClassicUno player DLLNode
		System.out.println("Reversing.... ");
		return;
	    }
	}
	
	else if ( top.isWildCard() ){
	    if ( (( WildCard )top ).getAbility.equals("draw4") ){
		System.out.println("Draw 4 cards.");
		for (int i = 0; i < 4; i++){
		    drawCard();
		}
	    }
	}
	
        playCard( top );
	
    }

    public String toString(){
	String ret = _name + "\n";
	for (Card x : _hand){
	    ret += x;
	    ret+= "\t";
	}
	return ret;
    }
}
