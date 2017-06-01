interface Stack<P> {
 //Return true if this stack is empty, otherwise false.
    boolean isEmpty();

    //Return top element of stack without popping it.
    P peek();

    //Pop and return top element of stack.
    P pop();

    //Push an element onto top of this stack.
    void  push( P x );
}