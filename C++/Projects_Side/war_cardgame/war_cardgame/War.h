#include <string>
#include <iostream>
#include <sstream>
#include <typeinfo.h>
#include <algorithm>
#include <array>
#include <random>
#include <chrono>
#include <cctype>
using namespace std;

//***************************************************
// Name: Card Class
// Purpose: to hold information about each playing card as well as pointers to the next and previous
//***************************************************
class Card
{
private:
	// Node Data
	Card* _nextCard;
	Card* _prevCard;
	unsigned int _nodeNum;

	// Card Data
	string _suit;
	string _color;
	unsigned int _cardNum;

	// Owner Data
	int _cardOwner;

public:
	Card();
	// _suit, _color, _cardNum
	Card(string, string, unsigned int);
	~Card();

	// Getters and Setters

	Card* GetNext();
	void SetNext(Card*);
	Card* GetPrev();
	void SetPrev(Card*);
	unsigned int GetNodeNum();
	void SetNodeNum(unsigned int);
	string GetSuit();
	void SetSuit(string);
	string GetColor();
	void SetColor(string);
	unsigned int GetCardNum();
	void SetCardNum(unsigned int);
	int GetCardOwner();
	void SetCardOwner(int);
};

//***************************************************
// Name: Stack
// Purpose: to hold a pointer to the top card in the stack and the number of cards in the stack
//			-Will be used for the Deck object and the Discard object.
//***************************************************
class Stack
{
private:
	Card* _topCard; 
	Card* _botCard;
	unsigned int _cardCount;
public:
	Stack();
	// _topNode, _nodeCount
	//Stack(Card*);
	~Stack();

	// Getters and Setters
	Card* GetTop();
	void SetTop(Card*);
	Card* GetBot();
	void SetBot(Card*);
	unsigned int GetCount();
	void SetCount(unsigned int);

	// Decouple the top card from the stack and return a pointer to it.
	Card* Pop_Top();
	// Add a card to the top of the stack.
	void Push_Top(Card*);
	virtual void Push_Bot(Card*);


};

//***************************************************
// Name: Stack
// Purpose: to hold a pointer to the top card in the player's hand and the number of cards
//***************************************************
class Hand : public Stack
{
private:
	unsigned int _playerNumber;
public:
	Hand();
	Hand(unsigned int);
	~Hand();

	unsigned int GetPlayerNum();
	void SetPlayerNum(unsigned int);
	// Find a card in the player's hand, decouple it, and return a pointer to it.
	Card* RemoveCard(unsigned int);
	// Add a card to the top of the stack.
	virtual void Push_Bot(Card*, int);

};

void Game(vector<Hand*>*);
void ShuffleStack(Stack*);
void BuildDeck(Stack*);
bool Continue();
void SetUp();
void DealCards(vector<Hand*>*, Stack*);
int Menu();
void Swap(Card*, Card*);