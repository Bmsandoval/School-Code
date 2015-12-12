#include "War.h"

Card::Card()
{
	_nodeNum = 0;
	_suit = "spade";
	_color = "red";
	_cardNum = 1;
}
// _suit, _color, _cardNum
Card::Card(string suit, string color, unsigned int cNum)
{
	_cardOwner = 0;
	_nodeNum = 0;
	_suit = suit;
	_color = color;
	_cardNum = cNum;
}
Card::~Card()
{
	delete _nextCard;
	_nextCard = NULL;
	_prevCard = NULL;
}

// Getters and Setters

Card* Card::GetNext()
{
	return _nextCard;
}
void Card::SetNext(Card* next)
{
	_nextCard = next;
}
Card* Card::GetPrev()
{
	return _prevCard;
}
void Card::SetPrev(Card* prev)
{
	_prevCard = prev;
}
unsigned int Card::GetNodeNum()
{
	return _nodeNum;
}
void Card::SetNodeNum(unsigned int nNum)
{
	_nodeNum = nNum;
}
string Card::GetSuit()
{
	return _suit;
}
void Card::SetSuit(string suit)
{
	_suit = suit;
}
string Card::GetColor()
{
	return _color;
}
void Card::SetColor(string color)
{
	_color = color;
}
unsigned int Card::GetCardNum()
{
	return _cardNum;
}
void Card::SetCardNum(unsigned int cNum)
{
	_cardNum = cNum;
}
int Card::GetCardOwner()
{
	return _cardOwner;
}
void Card::SetCardOwner(int owner)
{
	_cardOwner = owner;
}