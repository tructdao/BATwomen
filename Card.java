import cs1.Keyboard;

public class Card{

    private String _color;
    private String _symbol;
  
    //default constructor
    public Card(){
	_color = "";
	_symbol = "";
    }

    //overloaded constructor
    public Card( String symbol, String color ){
	_symbol = symbol;
	_color = color;
    }

    //----------------vv Accessors vv-----------------

    public String getColor(){
	return _color;
    }

    public String getSymbol(){
	return _symbol;
    }

    //----------------^^ Accessors ^^-----------------

    //----------------vv Mutators vv-----------------
   
    //----------------^^ Mutators ^^-----------------
    public boolean match( Card other ){

	//matching symbols
	if ( _symbol.equals(other.getSymbol())){
	    return true;
	}

	//matching color
	else if ( _color.equals(other.getColor()) ){
	    return true;
	}

	//black cards (plus 4 and wild)
	else if ( _color.equals("black")){
	    return true;
	}

	//nope nothing matches oh well
	return false;
    }
    
    //for wild cards and draw4, after playing the card you need to call this
    public void setColor(){
	System.out.println( "What color would you like to play next?" );
	System.out.println( "red\tyellow\tblue\tgreen" );
	System.out.print( "Your choice: " );
	_color = Keyboard.readString().toLowerCase();
	while(!(_color.equals("red")) &&
	      !(_color.equals("yellow")) &&
	      !(_color.equals("blue")) &&
	      !(_color.equals("green"))){  
	    System.out.println( "Please pick one of these 4 colors:" ) ;
	    System.out.println("\t1: red");
	    System.out.println("\t2: yellow");
	    System.out.println("\t3: blue");
	    System.out.println("\t4: green");
	    _color = Keyboard.readString().toLowerCase() ;
	}

    }

    public String toString(){
	String ret = "";
	ret= _symbol+ " : " + _color;
	return ret;
    }
}
