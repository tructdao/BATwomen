import cs1.Keyboard;

public class Wild extends ActionCard {
    
    String _ability;
    String _color;

    //"colorSwitch"
    
    public Wild() {
	_ability = "colorSwitch";
	_color = "white";
    }

    public String getAbility() {
	return _ability;
    }

    public String setColor(){
	System.out.println("What would you like the new color to be?");
	System.out.println("Red\tYellow\tBlue\tGreen");
	System.out.print("Your choice: ");
	_color = Keyboard.readString().toLowerCase();
        return _color;
    }

    public boolean isNumberCard(){ return false; }
    public boolean isActionCard(){ return false; }
    public boolean isWildCard(){ return true; }
    
}
