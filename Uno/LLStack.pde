/*****************************************************
 * class LLStack
 * Implements ADT stack as specified in local Stack.java
 * Generically typed
 * Uses a LinkedList as underlying container
 *****************************************************/

import java.util.LinkedList;

class LLStack<T> implements Stack<T> {

LinkedList<T> _stack;

    //constructor 
    LLStack() { 
      _stack = new LinkedList<T>();
    }


    //means of insertion
    void push( T s ) {
      _stack.add(s);  
    }


    //means of viewing top element without removing
    T peek( ) { 
      T retVal = null;
      if ( isEmpty() )  return null;
      retVal = _stack.get( _stack.size()-1 );
      return retVal;
    }


    //means of removal
    T pop( ) { 
      T retVal = null;
      if ( isEmpty() )  return null;
      retVal = _stack.remove( _stack.size()-1 );
      return retVal;
    }


    //chk for emptiness
    boolean isEmpty() {
      return _stack.size() == 0;  
    }
}