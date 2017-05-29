public class Reverse extends ActionCard{

    String _action;
    String _color;

    public Reverse(){
	_action = "reverse";
    }

    public Reverse( String color ){
	this();
	_color = color;
    }

    public String getAction(){
	return _action;
    }

    public String toString(){
	String ret = "";
	ret += _action + " : " + _color +"\n";
	return ret;
    }
    
}
