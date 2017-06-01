import java.io.*;
import java.util.*;

/***
 * Create an AI which will loop through the hand check for cards that work if a
 number card works place it, if not move on to an action card, other wise see if
 there is a black card. if not draw check if that card works. if it does place
 it. Otherwise move on to the next player(human player).
 * Work on moving everything to processing while having terminal version backed
 up on github. 
***/

public class PlayerAI extends Player{

    private ArrayList<Card> _hand;
    private String _name;

    public PlayerAI(){
	_hand = new ArrayList<Card>();
	_name = "AI";
    }

    /***
     * Takes the top card of the discard pile
     * Goes through all the cards in _hand
     * finds a Card to play
     * returns the index of the first playable card
     * if card doesn't exist return -1
     ***/
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

    /***
     * takes the Card at index in _hand
     * removes and returns the Card
     ***/
    public Card play( int index ){
	return _hand.remove( index );
    }

    public boolean isAI(){
	return true;
    }

    //--------------------------- vv Accessors vv -----------------------

    public String getName(){
	return _name;
    }

    public ArrayList<Card> getHand(){
	return _hand;
    }

    // while getHandSize() != 0, player still plays
    public int getHandSize(){
	return _hand.size();
    }
    
    //--------------------------- ^^ Accessors ^^ -----------------------

    public String toString(){
        
	String ret = "\nPLAYER " + _name ;
	ret += "\nINDEX\tCARD\n" ;
	for( int n = 0 ; n < _hand.size() ; n ++ ) {
	    ret += n + "\t" + _hand.get( n ) + "\n" ;
	}
	return ret;
	
    }
    /****
     * HOW TO IMPLEMENT THIS IN CLASSIC UNO:
     * if ( AIPlayer.turn( _discard.pop() ) == - 1){ AIPlayer.draw() }
     * else{ _discard.push( AIPlayer.play( AIPlayer.turn( _discard.pop() ) ) }
     ****/
}
