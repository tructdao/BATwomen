BatWomen -- Bayan Berri, Alison Lee, Truc Dao

APCS2 pd4

README.md

<h1>UNO</h1>

Uno allows the player to participate in an exciting game of UNO against as many players as the person wishes. The player is dealt a hand of cards and can play them as they see fit or draw more cards from the deck of cards. The objective is to run out of cards first, thus winning the game of UNO.

<h1>How it works</h1>

Uno utilizes a variety of object types to organize the game. 

The players are organized using a Doubly Linked List, allowing for traversal in both directions in the event of a reverse card.

The player's hands of cards are organized using an ArrayList of Cards, allowing for easy removal and addition.

The discard pile of cards is a stack, allowing for cards to be pushed on while still allowing for access to the top card, or last card played.

The deck of cards is a LinkedList of Cards. 

<h1>Launch Instructions</h1>

To launch the game:
  $ javac Uno.java
  $ java Uno
sdf
