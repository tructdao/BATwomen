import cs1.Keyboard;

public class Wild extends ActionCard {
    
    String _action;
    String _color;

    //"colorSwitch"
    
    public Wild() {
	_action = "colorSwitch";
	_color = "white";
    }

    public String getAction() {
	return _action;
    }

    public String setColor(){
	System.out.println("What would you like the new color to be?");
	System.out.println("Red\tYellow\tBlue\tGreen");
	System.out.print("Your choice: ");
	_color = Keyboard.readString().toLowerCase();
        return _color;
    }

    public String toString(){
	String ret = "";
	ret += _action + " : " + _color +"\n";
	return ret;
    }
    
}
