/*****************************************************
 * interface List
 * Specifies actions a List must be able to perform.
 * New in Version 6: Iterability via FOREACH
 * (The Iterable interface is a superinterface to interface List, 
 * in the actual Java library. Interface Iterable is actually what allows
 * a for-each loop to be run on a collection class.)
 *****************************************************/

import java.util.Iterator;

   interface List<T> extends Iterable<T> 
{ 
    //add element T to end of list
    //always return true
       boolean add( T x ); 

    //insert element T at index i
       void add( int i, T newVal );

    //remove element at index i
       T remove( int i );

    //return element at index i
       T get( int i ); 

    //overwrite element at index i, return old element at index i
       T set( int i, T x ); 

    //return number of meaningful elements in list
       int size(); 

    //return an Iterator over the elements in list
    Iterator<T> iterator();

}//end interface List