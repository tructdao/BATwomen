public class WildCard extends ActionCard {
    
    String _ability ;

    //"draw4"
    //"colorSwitch"
    
    public WildCard() {
	_ability = "";
    }

    public WildCard( String a ){
	_ability = a;
    }
    
    public String Ability() {
    }

    public String getAbility() {
	return _ability;
    }
   
}
