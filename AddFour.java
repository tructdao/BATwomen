import cs1.Keyboard;

public class AddFour extends ActionCard{

    String _action;
    String _color;

    public AddFour(){
	_action = "draw4";
	_color = "white";
    }

    public String getAction(){
	return _action;
    }

    public String setColor(){
	System.out.println("What would you like the new color to be?");
	System.out.println("Red\tYellow\tBlue\tGreen");
	System.out.print("Your choice: ");
	_color = Keyboard.readString();
        return _color;
    }
    
    public String toString(){
	String ret = "";
	ret += _action + " : " + _color +"\n";
	return ret;
    }
}
