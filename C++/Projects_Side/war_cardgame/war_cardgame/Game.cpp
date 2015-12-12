#include "War.h"

//**************************************************************************************
//Game(): Run the game after the set-up is complete
void Game(vector<Hand*> player, Stack* deck)
{
	vector<Hand*>* temp = new vector<Hand*>;
	vector<Hand*>* discard = new vector<Hand*>;
	int playerCount = player.size();

	while(true)
	{
		for(int i = 0;i < player.size(); i++)
		{
			(*temp)[i] = player[i];
		}

		while(true)
		{
			for(int i = 0; i< playerCount; i++)
			{
				system("PAUSE");
				player[i] ->Pop_Top();
				cout << "Player " << i+1 << " plays " << player[i]->GetPlayArea()->GetCardNum() << " of " << player[i]->GetPlayArea()->GetSuit() << endl;
			}

			for(int i = 0; i < playerCount; i++)
				temp->push_back(player[i]);
			for(int j = 0; j < playerCount; j++)
				for(int i = 0; i < playerCount; i++)
					if((*temp)[i]->GetPlayArea()->GetCardNum() > (*temp)[j]->GetPlayArea()->GetCardNum())
						Swap((*temp)[i], (*temp)[j]);
			
			if((*temp)[0]->GetPlayArea()->GetCardNum() == (*temp)[1]->GetPlayArea()->GetCardNum() && (*temp)[0]->GetPlayArea()->GetCardNum() == (*temp)[4]->GetPlayArea()->GetCardNum())
			{ // This will be run if all 4 players match cards

			}
			else if((*temp)[0]->GetPlayArea()->GetCardNum() == (*temp)[1]->GetPlayArea()->GetCardNum() && (*temp)[1]->GetPlayArea()->GetCardNum() == (*temp)[3]->GetPlayArea()->GetCardNum())
			{ // This will run if 3 of the players match

			}
			else if((*temp)[0]->GetPlayArea()->GetCardNum() == (*temp)[1]->GetPlayArea()->GetCardNum() && (*temp)[0]->GetPlayArea()->GetCardNum() == (*temp)[2]->GetPlayArea()->GetCardNum())
			{ // this will run if 2 players match

			}
			else
			{ // this will run if noone matches. It will pop out the discard pile and push them into the player's Llist.

			}








//			for(int j = 0; j < playerCount; j++)
//				for(int i = table.size() - playerCount; i < table.size(); i++)
//					if(i > j)
//						Swap(table[i], table[j]);
//			if(table[table.size()-playerCount] == table[table.size()-playerCount + 1] && table[table.size()-playerCount] == table[table.size()-playerCount + 2]
//			&& table[table.size()-playerCount] == table[table.size()-playerCount + 3])
//			{
//				int warPlayers = 4;
//				for(int i = 0; i < 3; i++)
//				{
//					for(int p = 0; p < warPlayers; p++)
//					{
//						table.push_back(player[ table[ table.size()-playerCount ] ->GetCardOwner() ]->Pop_Top());
//						cout << "Player " << player[ table[ table.size()-playerCount ] ->GetCardOwner() ] 
//							<< " plays one card face down." << endl;
//					}
//					if(i = 2)
//					{
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//						for(int p = 0; p < warPlayers; p++)
//						{
//							table.push_back(player[ table[ table.size()-playerCount ] ->GetCardOwner() ]->Pop_Top());
//							cout << "Player " << player[ table[ table.size()-playerCount ] ->GetCardOwner() ] 
//								<< " plays " << table[ table.size()-playerCount -1]->GetCardNum << " of " 
//								<< table[ table.size()-playerCount -1]->GetSuit() << endl;
//						}
//				}
//			}
//			else if(table[table.size()-playerCount] == table[table.size()-playerCount + 1] && table[table.size()-playerCount] == table[table.size()-playerCount + 2])
//			
//			else if(table[table.size()-playerCount] == table[table.size()-playerCount + 1])
//
//			else
		}
		while(playerCount == 3)
		{

		}
		while(playerCount == 2)
		{

		}
	}

}
/// Test.