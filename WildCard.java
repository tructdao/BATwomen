public class WildCard implements Card {
    
    String _ability ;

    //"draw4"
    //"colorSwitch"
    
    public WildCard() {
	_ability = "";

	//should this throw an error? -> never want it to happen
    }

    public WildCard( String a ){
	_ability = a;
    }
    
    public String Ability() {
    }

    public String getAbility() {
	return _ability;
    }

    public boolean isNumberCard(){ return false; }
    public boolean isActionCard(){ return false; }
    public boolean isWildCard(){ return true; }
   
}
