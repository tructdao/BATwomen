import java.io.*;
import java.util.*;

public class PlayerAI extends Player{

    private ArrayList<Card> _hand;

    public PlayerAI(){
	_hand = new ArrayList<Card>();
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

    /****
     * HOW TO IMPLEMENT THIS IN CLASSIC UNO:
     * if ( AIPlayer.turn( _discard.pop() ) == - 1){ AIPlayer.draw() }
     * else{ _discard.push( AIPlayer.play( AIPlayer.turn( _discard.pop() ) ) }
     ****/
}
