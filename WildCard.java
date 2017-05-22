public class WildCard extends ActionCard {
    
    String _ability ;
    
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
