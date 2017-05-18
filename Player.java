public class Player{

    int gamesWon;
    int gamesPlayed;
    int _score;
    boolean won;
    ArrayList<Card> _hand;

    public Player(){
	gamesWon = 0;
	gamesPlayed = 0;
	_score = 0;
	won = false;
	_hand = new ArrayList<Card>;
    }

    public Player(int gW, int gP, int s){
	this();
	gamesWon = gW;
	gamesPlayed = gP;
	_score = s;
    }

    public Card drawCard(){
    }

    public Card playCard(){
    }

    public String callUno(){
	won = true;
    }

    public String toString(){
	String ret = "";
	for (Card x : _hand){
	    ret += x;
	}
	return ret;
    }
}
