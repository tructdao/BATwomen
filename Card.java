// (one 0 card, two of each number [1-9] cards, two draw2, skip, reverse cards) x each color, 4 wild and 4 wild draw4 cards

public class Card {

    /* COLOR PRINTING STUFF
    protected static final String reset = "\u001B[0m";
    protected static final String black = "\u001B[30m";
    protected static final String red = "\u001B[31m";
    protected static final String green = "\u001B[32m";
    protected static final String yellow = "\u001B[33m";
    protected static final String blue = "\u001B[34m";
    protected static final String purple = "\u001B[35m";
    protected static final String cyan = "\u001B[36m";*/

    private String _color;

    public Card(){
	_color = "";
    }
    
    public String getColor(){
	return _color;
    }

    public String toString(){
	String ret = "printing card";
	return ret;
    }
}
