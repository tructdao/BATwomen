public class ActionCard extends Card {
    String _action ;
    String _color ;

    //"draw2" = draw 2 card
    //"skip"
    //"reverse"

    public ActionCard() {
	_action="";
	_color="";
    }
    
    public ActionCard(String a,String c){
	_action=a;
	_color=c;
    }

    public boolean isActionCard(){
	return true;
    }

    public String getAction() {
	return _action;
    }

    public String getColor() {
	return _color ;
    }
    
    public String addTwo(){
	return "";
    }
    
   
	/* although skip is a card, it doesnt deal with/change chards. it has more to do with a player
	actual skipping will be in the while loop (running the game): if( player's card == skip ) { do nothing and move onto next player ; } 
	OR
	if( player's card != skip ) { do whatever players can do } ;
    public void skip(){
    }
	*/
	
    public String toString(){
	String ret= "";
	ret+= this._action+ " : "+ this._color +"\n";
	return ret;
    }
}
