import java.io.*;
import java.util.*;
import cs1.Keyboard;
import java.util.Stack;
import java.util.LinkedList;

//STACK WON'T COMPILE IN THIS CLASS 

public class AIUno{

    public static LinkedList<Card> _deck;
    private LinkedList<Player> _players;
    public Stack<Card> _discard;

    //default constructor
    public AIUno() {
	_deck = new LinkedList<Card>();
	_players = new LinkedList<Player>();
	_discard = new Stack<Card>();
    }

    //each parameter comes from Classic Uno
    //all has been initialized in newGame() in ClassicUno
    public AIUno( LinkedList<Card> deck, LinkedList<Player> players,
		  Stack<Card> discard){
	this();
	_deck = deck;
	_players = players;
	_discard = discard;
    }

    public void setDiscard(){
	Card top = _deck.remove(0);
	_discard.push(top);
    }

    /**
     * when setting up the game it deals 7 cards to each player by removing
         the cards from the deck
    **/
    public void deal(){
	for( Player i : _players ) {
	    for( int n = 0 ; n < 7 ; n ++ ) {
	        i.setHand( _deck.remove(0) ) ;
	    }
	}
    }//ends deal()

    /**
     * if there is still more than 1 Player in the game, continue
     * if only 1 or no Players are left, return false 
     **/
    public boolean continueGame(){
	int stillPlaying = 0;
        for ( Player i : _players ){
	    if ( i.getHandSize() != 0 ){
		stillPlaying += 1;
	    }
	    if ( stillPlaying > 1 ){
		return true;
	    }
	}
	return false;
    }

    public void play(){
	Card top;
	int index, choice;
	while (  continueGame()  ){
	    for( Player p : _players ){
		
	        top = _discard.peek();
		System.out.println("Top card: " + top);

		//check if is an action Card
		
		if ( top.getSymbol().equals( "reverse" ) ){
		    System.out.println("REVERSE");
		    break;
		}

		if ( top.getSymbol().equals( "skip" ) ){
		    System.out.println("SKIP");
		    break;
		}

		if ( top.getSymbol().equals( "+2" ) ){
		    p.setHand( _deck.pop() );
		    p.setHand( _deck.pop() );
		}

		if ( top.getSymbol().equals( "+4" ) && ! top.getUsed() ){
		    for ( int n = 0; n < 4; n++ ){
			p.setHand( _deck.pop() );
		    }
		    top.setUsed();
		}

		//Now for the player's actions:

		//AI
		if ( p.getName().equals( "AI" ) ){
		    index = p.turn( top );
		    if ( index != -1 ){
			_discard.push( p.play(index) );
		    }
		    else{
			p.setHand( _deck.pop() );
			System.out.println("AI is drawing....");
			index = p.turn( top );
			if ( index != -1 ){
			    _discard.push( p.play( index ) );
			}
		    }
		    if ( p.getHandSize() == 0 ){
			System.out.println("AI WINS!");
			return;
		    }
		}

		//regular person
		else{
		    p.setTimes( 0 );
		    System.out.println( "Your hand: " + p );
		    choice = -1;
		    while( choice != 1 && choice != 2 ){
			System.out.println( "Please choose 1 or 2.");
			System.out.println( "Would you like to\n" +
					    "\t1. Draw\n" +
					    "\t2. Play a card");
			System.out.print("Your choice: ");
			choice = Keyboard.readInt();
		    }
		    if (choice == 1){
			drawCard( p );
			choice = -1;
			while( choice != 1 && choice != 2 ){
			    System.out.println( "Please choose 1 or 2.");
			    System.out.println( "Would you like to\n" +
						"\t1. Play\n" +
						"\t2. Pass");
			    System.out.print("Your choice: ");
			    choice = Keyboard.readInt();
			}
			if ( choice == 1 ){
			    index = chooseCard( p, top );
			    if ( index != -1 ){
				_discard.push( p.removeCard( index ) );
			    }
			}
		    }
		    else{
			index = chooseCard( p, top );
			_discard.push( p.removeCard( index ) );
		    }
		    if( p.getHandSize() == 0){
			System.out.println("YOU WIN!!");
			return;
		    }
		}//end regular person else
	    }//end for
	}//end while
    }//end play()


    /*********
     *********/

    //--------------------- vv Regular Player Methods vv ---------------------

    /***
     * Prints out player's cards
     * Player chooses card based on index
     * If invalid card --> asked to choose again or draw
     * If player has drawn before, then asked to play another or pass
     ***/
    public int chooseCard( Player current, Card top ) {
	int index = -1;
	System.out.println("Please play a card: ");

	System.out.println( "Top Card: " + top );
	//print player's hand
	System.out.println( current );
	System.out.print("Your choice: ");
	index = Keyboard.readInt();

	//invalid index chosen
	while ( index < 0 || index >= current.getHandSize() ){
	    System.out.println("Invalid index range!");
	    System.out.print("Your choice: ");
	    index = Keyboard.readInt();
	}

	//Card matches
	Card chosen = current.getCard( index );
	if ( chosen.match( top ) ){
	    return index;
	}

	//Card doesn't match
	else{
	    System.out.println( "The card doesn't match!!\n" );

	    //Player has never drawn
	    if ( current.getTimes() == 0 ){
		int choice = -1;
		while( choice != 1 && choice != 2 ){
		    System.out.println( "Please choose 1 or 2.");
		    System.out.println( "Would you like to\n" +
					"\t1. Draw\n" +
					"\t2. Play a card");
		    System.out.print("Your choice: ");
		    choice = Keyboard.readInt();
		}

		//draw a card
		if (choice == 1){
		    drawCard( current );
		    choice = -1;
		    while( choice != 1 && choice != 2 ){
			System.out.println( "Please choose 1 or 2.");
			System.out.println( "Would you like to\n" +
					    "\t1. Play\n" +
					    "\t2. Pass");
			System.out.print("Your choice: ");
			choice = Keyboard.readInt();
		    }

		    //play, go back to start
		    if ( choice == 1 ){
			return chooseCard( current, top );
		    }

		    //pass
		    else{
			return -1;
		    }
		}

		//play again, getTimes() == 0
		else{
		    return chooseCard( current, top );
		}
	    }
	    
	    //player has drawn in the past
	    else{
		int choice = -1;
		while( choice != 1 && choice != 2 ){
		    System.out.println( "Please choose 1 or 2.");
		    System.out.println( "Would you like to\n" +
					"\t1. Play\n" +
					"\t2. Pass");
		    System.out.print("Your choice: ");
		    choice = Keyboard.readInt();
		}
		if ( choice == 1 ){
		    return chooseCard( current, top );
		}
		else{
		    return -1;
		}
	    }
	}
    }

    public void drawCard( Player p ){
	p.setTimes( 1 );
	p.setHand( _deck.pop() );
	System.out.println( "You drew a " + p.getCard( p.getHandSize() - 1 ) );
    }
    //--------------------- ^^ Regular Player Methods ^^ ---------------------

}
