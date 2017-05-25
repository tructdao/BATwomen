public class Skip extends ActionCard{

    String _action;
    String _color;

    public Skip(){
	_action = "skip";
	_color = "";
    }

    public Skip( String color ){
	this();
	_color = color;
    }

    public String getAction(){
	return _action;
    }

    public String getColor(){
	return _color;
    }

    /* although skip is a card, it doesnt deal with/change chards. it has more to do with a player
	actual skipping will be in the while loop (running the game): if( player's card == skip ) { do nothing and move onto next player ; } 
	OR
	if( player's card != skip ) { do whatever players can do } ;
    public void skip(){
    }
	*/
}
