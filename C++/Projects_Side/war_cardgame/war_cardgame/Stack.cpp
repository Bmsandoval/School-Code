#include "War.h"

Stack::Stack()
{
	_botCard = NULL;
	_topCard = NULL;
	_cardCount = 0;
}
// _topNode, _nodeCount
//Stack::Stack(Card* top)
//{
//
//}
Stack::~Stack()
{
	delete _topCard;
	_topCard = NULL;
	_botCard = NULL;
}
// Returns a Card* to the top card
Card* Stack::GetTop()
{
	return _topCard;
}
// Takes a Card* as a parameter
// Sets this to the new top card.
void Stack::SetTop(Card* newCard)
{
	_topCard = newCard;
}
Card* Stack::GetBot()
{
	return _botCard;
}
void Stack::SetBot(Card* bot)
{
	_botCard = bot;
}
// Returns the number of cards in the stack
unsigned int Stack::GetCount()
{
	return _cardCount;
}
// Takes an unsigned int as a parameter
// Sets this as the number of cards in the stack
void Stack::SetCount(unsigned int cCount)
{
	_cardCount = cCount;
}
// Decouple the top card from the stack and return a pointer to it.
Card* Stack::Pop_Top()
{
	Card* tPtr = _topCard;
	tPtr -> GetPrev() -> SetNext(NULL);
	if(tPtr->GetPrev() == NULL)
	{
		_topCard = NULL;
		_botCard = NULL;
	}
	_topCard = tPtr -> GetPrev();
	tPtr -> SetPrev(NULL);
	tPtr -> SetNext(NULL);
	_cardCount--;
	return tPtr;
}
// Add a card to the top of the stack.
void Stack::Push_Top(Card* add)
{
	if(_topCard == NULL && _botCard == NULL)
	{
		_topCard = add;
		_botCard = add;
		_cardCount++;
	}
	Card* tPtr = add;
	_cardCount++;
	while(tPtr -> GetNext() != NULL)
	{
		tPtr = tPtr -> GetNext();
		_cardCount++;
		tPtr->SetCardNum(_cardCount);
	}
	_topCard -> SetNext(add);
	add -> SetPrev(_topCard);
	_topCard = tPtr;
}
void Stack::Push_Bot(Card* add)
{
	if(_topCard == NULL && _botCard == NULL)
	{
		_topCard = add;
		_botCard = add;
		_cardCount++;
	}
	else
	{
		Card* tPtr = add;
		int temp = 1;
		while(tPtr -> GetNext() != NULL)
		{
			tPtr = tPtr -> GetPrev();
			temp++;
		}
		_botCard -> SetPrev(add);
		add -> SetPrev(_botCard);
		_botCard = tPtr;
	}
}
Hand::Hand(): Stack()
{
}
Hand::Hand(unsigned int pNum): Stack()
{
	_playerNumber = pNum;
}
Hand::~Hand()
{
}
Card* Hand::GetPlayArea()
{
	return _playArea;
}
void Hand::SetPlayArea(Card* play)
{
	_playArea = play;
}
unsigned int Hand::GetPlayerNum()
{
	return _playerNumber;
}
void Hand::SetPlayerNum(unsigned int pNum)
{
	_playerNumber = pNum;
}
// Pass in the card number you want to remove.
// If found, returns pointer to the removed card.
// Returns NULL if not found.
void Hand::PlayCard(unsigned int cPtr)
{
	Card* tempOne = GetTop();

	while(tempOne->GetCardNum() != cPtr)
	{
		if(tempOne->GetPrev() == NULL)
			_playArea = NULL;
		else
			tempOne = tempOne->GetPrev();
	}
	SetCount(GetCount()-1);
	Card* tempTwo = tempOne;
	while(tempTwo->GetNext() != NULL)
	{
		tempTwo->SetCardNum(tempTwo->GetCardNum() - 1);
		tempTwo = tempTwo->GetNext();
	}
	while(tempOne->GetPrev() != NULL)
		tempOne->GetPrev()->SetNext(tempOne->GetNext());
	while(tempOne->GetNext() != NULL)
		tempOne->GetNext()->SetPrev(tempOne->GetPrev());
	if(tempOne->GetNext() == NULL)
		SetTop(tempOne->GetPrev());
	if(tempOne->GetPrev() == NULL)
		SetBot(tempOne->GetNext());
	tempOne->SetNext(NULL);
	tempOne->SetPrev(NULL);
	_playArea = tempOne;
}
Card* Hand::RemoveCard()
{
	Card* tPtr = _playArea;
	_playArea = NULL;
	return tPtr;
}
void Hand::Pop_Top()
{
	Card* tPtr = GetTop();
	tPtr -> GetPrev() -> SetNext(NULL);
	if(tPtr->GetPrev() == NULL)
	{
		SetTop(NULL);
		SetBot(NULL);
	}
	SetTop(tPtr -> GetPrev());
	tPtr -> SetPrev(NULL);
	tPtr -> SetNext(NULL);
	SetCount(GetCount() -1);
	SetPlayArea(tPtr);
}
void Hand::Push_Bot(Card* add, int i)
{
	if(GetTop() == NULL && GetBot() == NULL)
	{
		SetTop(add);
		SetBot(add);
		SetCount(GetCount()+1);
		add->SetCardOwner(i);
	}
	else
	{
		Card* tPtr = add;
		int temp = 1;
		tPtr->SetCardOwner(i);
		while(tPtr -> GetPrev() != NULL)
		{
			
			tPtr = tPtr -> GetPrev();
			tPtr->SetCardOwner(i);
			temp++;
		}
		GetBot() -> SetPrev(add);
		add -> SetPrev(GetBot());
		SetBot(tPtr);
	}
////////////////////////////////////////////////////////////////// make sure to set it so that it sets the player owner.
}