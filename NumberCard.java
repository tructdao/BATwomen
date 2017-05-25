public class NumberCard implements Card {
    int _number ;
    String _color ;

    public NumberCard() {
	_number=0;
	_color= "";
    }

    public NumberCard(int n, String c){
	_number=n;
	_color=c;
    }	

    public String getColor() {
	return _color ;
    }

    public int getNumber(){
	return _number;
    }
    public String toString(){
	String ret= "";
	ret+= _number+ " : "+ _color+"\n";
	return ret;
    }

    public boolean isNumberCard(){ return true; }
    public boolean isActionCard(){ return false; }
    public boolean isWildCard(){ return false; }
}
