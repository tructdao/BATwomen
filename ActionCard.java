public class ActionCard extends Card {
    String _action ;
    String _color ;

    public ActionCard() {
	_action="";
	_color="";
    }
    
    public ActionCard(String a,String c){
	_action=a;
	_color=c;
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
    
    public void reverse(){
        
    }
    
    public void skip(){
    }
    public String toString(){
	String ret= "";
	ret+= this._action+ " : "+ this._color +"\n";
	return ret;
    }
}
