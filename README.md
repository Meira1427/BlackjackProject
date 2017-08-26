## Blackjack Project

### Meira Pentermann 08-18-2017

### Overview
This project is a command-line blackjack game

### Topics Covered
Inheritance

Object-Oriented Programming

JUnit Testing

Building Functionality in Stages

### Classes

### Classes - Cards & Decks

#### Suit 
enum which includes displayText & displayChar

#### Rank
enum which includes displayText and displayShort

#### Card
includes a value but can be changed depending upon card game

basic Getters & Setters and toString()

#### Deck
deck can be initialized with blackjack values or an array of integers can be passed with different values

createDeck() or createDeck(int[] values) is used by constructors to create blackjack deck or alternate deck

shuffleDeck() shuffles the Collection

dealCard() returns a Card while removing it from deck

cardSLeft() returns an integer; number of cards left in deck

basic Getters & Setters and toString()

### Classes - Participants
#### Participant
Abstract Class which forces each subclass to define a displayHand() method that takes a boolean

Fields: String name, Hand hand, Hand split

updateHand() and updateHandSplit() add a card to either main hand or split hand

getHandList() and getSplitList() return a List<Card>

getHandHand() and getSplitHand() return a Hand

handValue() and splitHandValue() return an int, the value of the cards 

#### Dealer
Dealer is set with name as "Dealer"

boolean in displayHand() method tells whether or not to show full hand

false at beginning, true at end or if Dealer draws a natural blackjack in first deal

#### Player
Player uses boolean in displayHand() method to choose whether or not to print a split hand or normal hand

player has hand and aSplit Hand initialized 

wallet is feature in progress, not developed yet

### Classes - BlackJack
#### BetManager
this is feature in progress, not developed yet

#### UserInput
this class handles getting proper answer from user in the form of (h)it or (s)tay or (y)es or (n)o

requires a Scanner object to be passed when creating a new instance of the class

#### Hand
addCard(Card c) very flexible, adds a card to a hand

removeCard() used only in the case of moving a card to a split hand; removed card at index 0

getHandList() returns a List<Card> rather than a Hand object. This was useful throughout program

acesInHandCount() - counts and returns number of Aces in Hand

acesInHandLocation() - returns a List<Integer>, the indexes where you can find Aces

dealWithAcesMainHand() handles Aces if necessary

also includes handSize() and handValue()

#### BlackjackGame
All the game logic is in here, including changing Aces from 11 to 1 if necessary, and split hand

The split hand became so complicated that I created some duplicate-like methods to handle the split hand cases

##### Private Fields
Player player, Dealer dealer, Deck deck

UserInput input - just to handle getting input from player

booleans: gameOver, playerBust, splitBust, dealerBust, split

Strings: playAgain, answer (hit/stay), splitAnswer (hit/stay)

##### Methods
fullGame() - runs the methods in order, loops in while loop

initialSetUp() - deals two cards each to player & dealer, displays hands

determineNextSteps() - looks for cases of natural blackjacks & possibility of splitting hand

expandPlayerHand() - most complicated, loops through hit/stay process, handles split hand, busting process

expandDealerHand() - loops while dealer has less than 17; deals with Aces if necessary

calculateWin() - deals with printing message to user, explaining the win

tearDown() - resets and re-shuffles deck, clears out hands, resets booleans and strings

printSuitBar() - to print ♥   ♠   ♣   ♦   ♥   ♠   ♣   ♦   ♥   ♠   ♣   ♦   ♥   ♠   ♣   ♦   ♥   ♠   ♣   ♦ 


#### PlayBlackjack
initiates an instance of BlackjackGame, passes it a scanner, runs fullGame(), then closes scanner




