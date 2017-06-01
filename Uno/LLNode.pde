/*****************************************************
 * class LLNode
 * Implements a node, for use in lists and other container classes.
 *****************************************************/

 class LLNode<T> {

      T _cargo;    //cargo may only be of type T
      LLNode<T> _nextNode; //pointer to next LLNode


    // constructor -- initializes instance vars
       LLNode( T value, LLNode<T> next ) {
  _cargo = value;
  _nextNode = next;
    }


    //--------------v  ACCESSORS  v--------------
       T getValue() { return _cargo; }

       LLNode<T> getNext() { return _nextNode; }
    //--------------^  ACCESSORS  ^--------------


    //--------------v  MUTATORS  v--------------
       T setValue( T newCargo ) {
  T foo = getValue();
  _cargo = newCargo;
  return foo;
    }

       LLNode<T> setNext( LLNode<T> newNext ) {
  LLNode<T> foo = getNext();
  _nextNode = newNext;
  return foo;
    }
    //--------------^  MUTATORS  ^--------------


    // override inherited toString
       String toString() { return _cargo.toString(); }


    //main method for testing
       static void main( String[] args ) {

  //Below is an exercise in creating a linked list...

  //Create a node
  LLNode<String> first = new LLNode<String>( "cat", null );

  //Create a new node after the first
  first.setNext( new LLNode<String>( "dog", null ) );

  //Create a third node after the second
  first.getNext().setNext( new LLNode<String>( "cow", null ) );

  LLNode temp = first; 
  while( temp != null ) {
      System.out.println( temp );
      temp = temp.getNext();
  }

    }//end main

}//end class LLNode