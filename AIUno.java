public class AIUno{

    public static LinkedList<Card> _deck;
    private LinkedList<Player> _players;
    public LLStack<Card> _discard;

    public AIUno() {
	_deck = new LinkedList<Card>();
	_players = new LinkedList<Player>();
	_discard = new LLStack<Card>();
    }

    //each parameter comes from Classic Uno
    //all has been initialized in newGame() in ClassicUno
    public AIUno( LinkedList<Card> deck, LinkedList<Player> players,
		  LLStack<Card> discard){
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

    /*********
    public void play(){
	while (  none of the _players' getHandSize() == 0  ){
	    for( Player i : _players ){
		if ( i.isAI() ){
		    i.turn();
		}
		else{
		    normal Player playing stuff like in ClassicUno;
		}
	    }
	}
     *********/

}
