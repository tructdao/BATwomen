import cs1.Keyboard;

public class Card{

    protected static final String reset = "\u001B[0m";
    protected static final String black = "\u001B[30m";
    protected static final String red = "\u001B[31m";
    protected static final String green = "\u001B[32m";
    protected static final String yellow = "\u001B[33m";
    protected static final String cyan = "\u001B[36m";

    private String _color;
    private String _symbol;
    private boolean _unplayed;
  
    //default constructor
    public Card(){
	_color = "";
	_symbol = "";
	_unplayed = true;
    }

    //overloaded constructor
    public Card( String symbol, String color ){
	_symbol = symbol;
	_color = color;
	_unplayed = true;
    }

    //----------------vv Accessors vv-----------------

    public String getColor(){
	return _color;
    }

    public String getSymbol(){
	return _symbol;
    }

    public boolean getUnplayed(){
	return _unplayed;
    }
    
    //----------------^^ Accessors ^^-----------------

    //----------------vv Mutators vv-----------------

    //Use to stop +4 cards, +2 cards, skip, and reverse
    public void setUnplayed(){
	_unplayed = false;
    }
    
    public void changeColor(String c){
	_color=c;
    }
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
	else if ( _color.equals("black") || other.getColor().equals( "black" )){
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

    public void setColorAI( Player AI ){
	if ( AI.getHandSize() == 1 ){
	    if ( AI.getCard( 0 ).getColor().equals( "black" ) ){
	        int c = (int) Math.random() * 4;
		if ( c == 1 ){ _color = "red"; }
		else if ( c == 2 ){ _color = "yellow"; }
		else if ( c == 3 ){ _color = "blue"; }
		else{ _color = "green"; }
	    }
	    else{
		_color = AI.getCard( 0 ).getColor();
	    }
	}
	else{ 
	    int index = (int) ( Math.random() * AI.getHandSize() );
	    while( AI.getCard( index ).getColor().equals("black") ){
		index = (int) ( Math.random() * AI.getHandSize() );
	    }
	    _color = AI.getCard( index ).getColor();
	}
    }

    public String toString(){
	String ret = "";
	if ( _color.equals( "red" ) ){
	    ret += red;
	}
	else if ( _color.equals( "yellow" ) ){
	    ret += yellow;
	}
	else if ( _color.equals( "green" ) ){
	    ret += green;
	}
	else if ( _color.equals( "blue" ) ){
	    ret += cyan;
	}
	else if ( _color.equals( "black" ) ){
	    ret += black;
	}
	ret += _symbol+ " : " + _color;
	return ret + reset;
    }
}
