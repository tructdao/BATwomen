import java.io.*;
import java.util.*;
import cs1.Keyboard;

public class AIUno{

    public static LinkedList<Card> _deck;
    private LinkedList<Player> _players;
    public LLStack<Card> _discard;
    public Stack<Player> _winners;

    public AIUno() {
	_deck = new LinkedList<Card>();
	_players = new LinkedList<Player>();
	_discard = new LLStack<Card>();
	//_winners = new Stack<Player>(); --> Gives an error, stack is abstract
	//Note: instantiating _winners in Classic Uno works so idk whats up here
    }

    //each parameter comes from Classic Uno
    //all has been initialized in newGame() in ClassicUno
    public AIUno( LinkedList<Card> deck, LinkedList<Player> players,
		  LLStack<Card> discard){
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
	    // vv change n < 2 to n < 7 vv
	    for( int n = 0 ; n < 2 ; n ++ ) {
		i.setHand( _deck.remove(0)) ;
	    }
	}
    }//ends deal()

    /**
     * if there are still Players in the game, continue
     **/
    public boolean continueGame(){
        return _players.size() != 0;
    }

    public void play(){
	Card top;
	int index, choice;
	while (  continueGame()  ){
	    for( Player i : _players ){
		
	        top = _discard.peek();
		System.out.print("Top card: " + top);

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
		    i.setHand( _deck.pop() );
		    i.setHand( _deck.pop() );
		}

		if ( top.getSymbol().equals( "+4" ) ){
		    for ( int n = 0; n < 4; n++ ){
			i.setHand( _deck.pop() );
		    }
		}

		//Now for the player's actions:

		//AI
		if ( i.getName().equals( "AI" ) ){
		    index = ( ( PlayerAI ) i ).turn( top );
		    if ( index != -1 ){
			_discard.push( ( ( PlayerAI ) i ).play(index) );
		    }
		    else{
			i.setHand( _deck.pop() );
			System.out.println("AI has no playable cards.");
		    }
		}

		//regular person
		else{
		    System.out.println( "Your hand: " + i );
		    choice = -1;
		    while( choice != 1 || choice != 2 ){
			System.out.println( "Please choose 1 or 2.");
			System.out.println( "Would you like to\n" +
					    "\t1. Draw\n" +
					    "\t2. Play a card.");
		    }
		    if (choice == 1){
			i.setHand( _deck.pop() );
		    }
		    else{
			index = chooseCard( i, top );
			_discard.push( i.getCard( index ) );
		    }
		}//end regular person else
	    }//end for
	}//end while
    }//end play()

    /**
     *regular person choosing a card
     **/
    public int chooseCard( Player current, Card top ) {
	int index = -1;
	System.out.println("Please play a card: ");
	System.out.println( current );
	System.out.print("Your choice: ");
	index = Keyboard.readInt();
	while ( index < 0 || index >= current.getHandSize() ){
	    System.out.println("Invalid index range!");
	    System.out.print("Your choice: ");
	    index = Keyboard.readInt();
	}
	Card chosen = current.getCard( index );
	if ( chosen.match( top ) ){
	    return index;
	}
	else{
	    System.out.println( "The card doesn't match!!\n" +
				"Choose again.");
	    return chooseCard( current, top );
	}
    }

    /*********
     *********/

}
