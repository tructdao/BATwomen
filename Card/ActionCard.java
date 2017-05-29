public class ActionCard implements Card {
    
    String _action ;
    String _color ;

    public ActionCard() {
	_action = "";
	_color = "";
    }
    
    public ActionCard(String a,String c){
	_action=a;
	_color=c;
    }
    // public void action();
    public String getAction() {
	return _action;
    }

    public String getColor() {
	return _color ;
    }

    public String toString(){
	String ret = "";
	ret += _action + " : " + _color +"\n";
	return ret;
    }

    public boolean isNumberCard(){ return false; }
    public boolean isActionCard(){ return true; }
    public boolean isWildCard(){ return false; }
    
}
