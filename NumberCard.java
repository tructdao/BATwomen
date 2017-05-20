public class NumberCard extends Card {
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
	ret+= this._number+ " : "+ this._color+"\n";
	return ret;
    }
}
