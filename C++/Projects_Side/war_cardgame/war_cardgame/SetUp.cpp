#include "War.h"

const int ASIZE = 80;
const string NONE = "0";
const string MENU_ERROR = "Seriously? The instructions are not that difficult...";
const string MAIN_MENU = "Please choose a number of players (2-4): ";
const string RQST_CONTINUE = "Would you like to play again? (yes or no): ";


void main()
{
	SetUp();
	system("PAUSE");
}
//**************************************************************************************
//SetUp(): Initial setup.
//Calls: BuildDeck(), Menu(), ShuffleStack(), DealCards(), Game(), Continue()
void SetUp()
{
	int numPlayers = 0;
	Stack* deck = new Stack();
	BuildDeck(deck);
	do
	{
		numPlayers = Menu();
		vector<Hand*>* player;
		for (int i = 0; i < numPlayers; i++)
		{
			player->push_back(new Hand(i));
		}
		ShuffleStack(deck);
		DealCards(player, deck);
		Game(player);
	}while(Continue());
	delete deck;
}
//**************************************************************************************
//Swap(): Swaps two pointers.
//Calls: BuildDeck(), Menu(), ShuffleStack(), DealCards(), Game(), Continue()
void Swap(Card* a, Card* b)
{
	Card* temp = a;
	*a = *b;
	*b = *temp;
}
//**************************************************************************************
//DealCards(): divide up the cards into each players hands
void DealCards(vector<Hand*>* player, Stack* deck)
{
	int numPlayers = player->size();
	int numCards = 52 / numPlayers;
	int numRemain = 52 % numPlayers;
	for(int i = 0; i < numCards; i++)
		for(int p = 0; p < numPlayers; p++)
		{
			(*player)[p]->Push_Bot(deck->Pop_Top(), p);
		}
	for(int p = 0; p < numRemain; p++)
	{
		(*player)[p]->Push_Bot(deck->Pop_Top(), p);
	}
}


//**************************************************************************************
//Menu(): Ask user how many players they would like
int Menu()
{
	string input = "";
	char cinput[ASIZE+1];
	int temp = 0;
	int state = 0;

	do
	{
		cout << MAIN_MENU;
		cin.getline(cinput, ASIZE);
		state = cin.rdstate();
		if(state != 0) // this will test for the error codes incase someone hit's ^Z or another ctrl code.
		{
			cin.clear();
			if(cin.rdbuf() -> in_avail() > 0)
				getline(cin, input);
			cout << MENU_ERROR;
			system("PAUSE");
			system("CLS");
			continue;
		}
		else if(cinput == "")
		{
			cout << MENU_ERROR;
			system("PAUSE");
			system("CLS");
			continue;
		}
		else if(cinput == NONE)
		{
			cout << MENU_ERROR;
			system("PAUSE");
			system("CLS");
			continue;
		}
		else if(temp = atoi(cinput))
		{
			if(temp < 0)
			{
				cout << MENU_ERROR;
				system("PAUSE");
				system("CLS");
				continue;
			}
			return temp;

		}
		cout << MENU_ERROR;
		system("PAUSE");
		system("CLS");
		continue;
	}while (true);
}
//**************************************************************************************
//Continue(): Check if the player wants to continue
//Returns: bool
bool Continue()
{

	string input = "";
	char cinput[ASIZE+1];
	double temp = 0.0;
	int state = 0;

	do
	{
		cout << RQST_CONTINUE;
		cin.getline(cinput, ASIZE);
		state = cin.rdstate();
		if(state != 0) // this will test for the error codes incase someone hit's ^Z or another ctrl code.
		{
			cin.clear();
			if(cin.rdbuf() -> in_avail() > 0)
				getline(cin, input);
			cout << MENU_ERROR;
			system("PAUSE");
			system("CLS");
			continue;
		}
		else if(toupper(cinput[0]) == 'Y')
			return true;
		else if(toupper(cinput[0]) == 'N')
			return false;
		else
		{
			MENU_ERROR;
			system("PAUSE");
			system("CLS");
			continue;
		}
	}while (true);
}
//**************************************************************************************
//ShuffleStack():
// Pop all Card* out of the deck, puts them in an array, shuffles the pointers
// then puts them back into the deck shuffled.
void ShuffleStack(Stack* d)
{
	Card** shuf = new Card*[d->GetCount()];
	int temp = d->GetCount();
	for (int i = 0; i < temp; ++i)
	{
		shuf[i] = d->Pop_Top();
	}
	// Shuffles the array values around
	unsigned seed = chrono::system_clock::now().time_since_epoch().count();
	shuffle (shuf[0], shuf[temp+1], default_random_engine(seed));

	for (int i = 0; i < temp; ++i)
	{
		d->Push_Top(shuf[i]);
	}
}
//**************************************************************************************
//BuildDeck(): Create card objects and push them onto the deck Llist.
void BuildDeck(Stack* d)
{
	d->Push_Top((new Card( "hearts", "red", 2 )));
	d->Push_Top((new Card( "diamonds", "red", 2 )));
	d->Push_Top((new Card( "clubs", "black", 2 )));
	d->Push_Top((new Card( "spades", "black", 2 )));
	d->Push_Top((new Card( "hearts", "red", 3 )));
	d->Push_Top((new Card( "diamonds", "red", 3 )));
	d->Push_Top((new Card( "clubs", "blacks", 3 )));
	d->Push_Top((new Card( "spades", "blacks", 3 )));
	d->Push_Top((new Card( "hearts", "red", 4 )));
	d->Push_Top((new Card( "diamonds", "red", 4 )));
	d->Push_Top((new Card( "clubs", "blacks", 4 )));
	d->Push_Top((new Card( "spades", "blacks", 4 )));
	d->Push_Top((new Card( "hearts", "red", 5 )));
	d->Push_Top((new Card( "diamonds", "red", 5 )));
	d->Push_Top((new Card( "clubs", "black", 5 )));
	d->Push_Top((new Card( "spades", "black", 5 )));
	d->Push_Top((new Card( "hearts", "red", 6 )));
	d->Push_Top((new Card( "diamonds", "red", 6 )));
	d->Push_Top((new Card( "clubs", "black", 6 )));
	d->Push_Top((new Card( "spades", "black", 6 )));
	d->Push_Top((new Card( "hearts", "red", 7 )));
	d->Push_Top((new Card( "diamonds", "red", 7 )));
	d->Push_Top((new Card( "clubs", "blacks", 7 )));
	d->Push_Top((new Card( "spades", "blacks", 7 )));
	d->Push_Top((new Card( "hearts", "red", 8 )));
	d->Push_Top((new Card( "diamonds", "red", 8 )));
	d->Push_Top((new Card( "clubs", "black", 8 )));
	d->Push_Top((new Card( "spades", "black", 8 )));
	d->Push_Top((new Card( "hearts", "red", 9 )));
	d->Push_Top((new Card( "diamonds", "red", 9 )));
	d->Push_Top((new Card( "clubs", "black", 9 )));
	d->Push_Top((new Card( "spades", "black", 9 )));
	d->Push_Top((new Card( "hearts", "red", 10 )));
	d->Push_Top((new Card( "diamonds", "red", 10 )));
	d->Push_Top((new Card( "clubs", "black", 10 )));
	d->Push_Top((new Card( "spades", "black", 10 )));
	d->Push_Top((new Card( "hearts", "red", 11 )));
	d->Push_Top((new Card( "diamonds", "red", 11 )));
	d->Push_Top((new Card( "clubs", "black", 11 )));
	d->Push_Top((new Card( "spades", "black", 11 )));
	d->Push_Top((new Card( "hearts", "red", 12 )));
	d->Push_Top((new Card( "diamonds", "red", 12 )));
	d->Push_Top((new Card( "clubs", "black", 12 )));
	d->Push_Top((new Card( "spades", "black", 12 )));
	d->Push_Top((new Card( "hearts", "red", 13 )));
	d->Push_Top((new Card( "diamonds", "red", 13 )));
	d->Push_Top((new Card( "clubs", "black", 13 )));
	d->Push_Top((new Card( "spades", "black", 13 )));
	d->Push_Top((new Card( "hearts", "red", 14 )));
	d->Push_Top((new Card( "diamonds", "red", 14 )));
	d->Push_Top((new Card( "clubs", "black", 14 )));
	d->Push_Top((new Card( "spades", "black", 14 )));
}