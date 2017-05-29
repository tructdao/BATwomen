public class AddTwo extends ActionCard{

    String _action;
    String _color;
    
    public AddTwo(){
	_action = "draw2";
    }

    public AddTwo( String color ){
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
