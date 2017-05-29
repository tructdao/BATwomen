import cs1.Keyboard;

public class Card{

    private String _color;
    private String _action;
    private int _number;

    //default constructor
    public Card(){
	_color = "";
	_action = "";
	_number = -1;
    }

    //numberCard constructor
    public Card( int num, String color ){
	_number = num;
	_color = color;
	_action = "none";
    }

    //actionCard constructor
    //skip, reverse, add2, add4, wild
    public Card( String action, String color ){
	_action = action;
	_color = color;
	_number = -1; //its not a number card
    }

    //----------------vv Accessors vv-----------------

    public String getColor(){
	return _color;
    }

    public String getAction(){
	return _action;
    }

    public int getNumber(){
	return _number;
    }
    
    //----------------^^ Accessors ^^-----------------

    public boolean match( Card other ){

	//matching number
	if ( _number == other.getNumber() && _number != -1){
	    return true;
	}

	//matching color
	else if ( _color.equals(other.getColor()) ){
	    return true;
	}

	//matching actions --> IS THIS A THING??
	else if ( _action.equals(other.getAction()) &&
		  ! _action.equals("none")){
	    return true;
	}

	//nope nothing matches oh well
	return false;
    }

    //for wild cards and draw4, after playing the card you need to call this
    public void setColor(){
	System.out.println( "What color would you like to play next?" );
	System.out.println( "red\tyellow\tblue\tgreen" );
	System.out.print( "Your choice " );
	_color = Keyboard.readString().toLowerCase();
    }

    public String toString(){
	String ret = "";
	if ( _number != -1 ){
	    ret += _number + "";
	}
	else{
	    ret += _action;
	}
	ret += "  " + _color;
	return ret;
    }

    public static void main(String[] args){

	Card a = new Card( 1, "red");
	Card b = new Card( 1, "blue");
	Card c = new Card( 2, "red" );
	Card d = new Card( "skip", "red" );
	Card e = new Card( "reverse", "red" );
	Card f = new Card( "reverse", "blue" );
	Card g = new Card( "add4", "blue" ); //set to blue for testing purposes

	System.out.println("a: " + a);
	System.out.println("b: " + b);
	System.out.println("c: " + c);
	System.out.println("d: " + d);
	System.out.println("e: " + e);
	System.out.println("f: " + f);
	System.out.println("g: " + g);

	System.out.println("a match b: " + a.match(b));
	System.out.println("a match c: " + a.match(c));
	System.out.println("b match c: " + b.match(c));
	System.out.println("d match e: " + d.match(e));
	System.out.println("d match f: " + d.match(f));
	System.out.println("e match f: " + e.match(f));
	
    }

}
