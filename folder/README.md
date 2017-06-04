# BatWomen
#### Bayan Berri, Alison Lee, Truc Dao

APCS2 pd4

## UNO Description

Our project allows the player to participate in an exciting game of UNO against as many players as the person wishes. The player is dealt a hand of seven cards and can play them as they see fit or draw more cards from the deck. The objective is to run out of cards first, thus winning the game of UNO.

### What the User sees
Upon running Uno.java, the user is first asked what they would like to do and have the choice of playing the classic game, tournament version, or viewing the rules. (Tournament is not functioning yet).
If the user chooses classic they are prompted to enter the number of players for this round and their names.
The players are then all dealt seven cards and the game begins.
Throughout the game the user is asked if they would like to draw or play a card. If they choose to draw they are asked if they want to play their card or pass their turn. If they choose play card they are asked to enter the index of the card that they would like to play. This loop goes on throughout the rest of the game. 

### How it Works

Uno utilizes a variety of object types to organize the game. 

- The players are organized using a Doubly Linked List, allowing for traversal in both directions in the event of a reverse card.

- The player's hands of cards are organized using an ArrayList of Cards, allowing for easy removal and addition.

- The discard pile of cards is a stack, allowing for cards to be pushed on while still allowing for access to the top card, or last card played.

- The deck of cards is a LinkedList of Cards. 

Each Player can see the last card played on the discard pile and draw cards from the deck. 

<h1>Launch Instructions</h1>

To launch the game:
 1.  `$ javac Uno.java`
 2.  `$ java Uno`
 3. Play

