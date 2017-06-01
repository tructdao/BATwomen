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
     ***/
    public void turn( Card top ){
	for (int i = 0; i < _hand.size(); i++ ){
	    if ( _hand.get( i ).match( top ) ){
		play( i );
		return;
	    }
	}
	System.out.println("No playable cards");
	//DRAW CARD
    }

    /***
     * takes the Card at index in _hand
     * removes and returns the Card
     ***/
    public Card play( int index ){
	return _hand.remove( index );
    }
    
}
