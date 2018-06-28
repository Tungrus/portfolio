#include "ConsoleWorker.h"
#include "HashMap.h"
#include <iostream>

IConsoleWorker::IConsoleWorker()
{

}

IConsoleWorker::~IConsoleWorker()
{

}

void IConsoleWorker::AddElement(HashMap& hashMap)
{
	std::string name, addictionalData, Links;
	std::cout << "Input identificator name" << std::endl;
	std::cin >> name;
	std::cout << "Input addictional Data" << std::endl;
	std::cin >> addictionalData;
	std::cout << "input Links" << std::endl;
	std::cin >> Links;
	hashMap.AddItem(name, addictionalData, Links);
}

void IConsoleWorker::EditElement(HashMap& hashMap)
{
	std::cout << "Input identificator name" << std::endl;
	std::string name;
	std::cin >> name;
	auto data = hashMap.FindItem(name);
	if (data != NULL)
	{
		char choose;
		
		std::cout << "What do you like to edit" << std::endl;
		std::cout << "Links/Data/No (L/D/N)" << std::endl;
		std::cin >> choose;
		if (choose == 'L')
		{
			int i = 1;
			int nomberOfLink = -1;
			for (auto elementBegin = data->GetIterator_Begin(), elementEnd = data->GetIterator_End(); elementBegin != elementEnd; elementBegin++)
			{
				std::cout << i << "----" << elementBegin->GetLinks() << std::endl;
				i++;
			}

			std::cout << "Choose Links group to Edit" << std::endl;
			std::cin >> nomberOfLink;
			if (i > nomberOfLink)
			{
				return;
			}
			i = 1;
			auto elementBegin = data->GetIterator_Begin();
			for (auto elementEnd = data->GetIterator_End(); i == nomberOfLink; elementBegin++)
			{
				i++;
			}
			std::cout << "Input new Links" << std::endl;
			std::string newString;
			std::cin >> newString;

			elementBegin->EditLinks(newString);
		}
		else if (choose == 'D')
		{

			int i = 1;
			int nomberOfLink = -1;
			for (auto elementBegin = data->GetIterator_Begin(), elementEnd = data->GetIterator_End(); elementBegin != elementEnd; elementBegin++)
			{
				std::cout << i << "----" << elementBegin->GetAddictionData() << std::endl;
				i++;
			}

			std::cout << "Choose Data group to Edit" << std::endl;
			std::cin >> nomberOfLink;
			if (i > nomberOfLink)
			{
				return;
			}
			i = 1;
			auto elementBegin = data->GetIterator_Begin();
			for (auto elementEnd = data->GetIterator_End(); i == nomberOfLink; elementBegin++)
			{
				i++;
			}
			std::cout << "Input new data" << std::endl;
			std::string newString;
			std::cin >> newString;

			elementBegin->EditAddictionData(newString);
		}
		else if (choose == 'N')
		{
			return;
		}
		else
		{
			std::cout << "Error command, try again" << std::endl;
		}
	}
	else
	{
		std::cout << "Entered identificator does not exist" << std::endl;
	}
}

void IConsoleWorker::PrintElement(HashMap& hashMap)
{
	std::cout << "Input identificator name" << std::endl;
	std::string name;
	std::cin >> name;
	auto data = hashMap.FindItem(name);
	if (data != NULL)
	{
		std::cout << "Element has been found " << std::endl;
		for (auto elementBegin = data->GetIterator_Begin(), elementEnd = data->GetIterator_End(); elementBegin != elementEnd; elementBegin++)
		{
			std::cout << *(elementBegin->GetAddictionData()) << std::endl;
			std::cout << *(elementBegin->GetLinks()) << std::endl;
		}
	}
	else
	{
		std::cout << "Entered identificator does not exist" << std::endl;
	}
}

void IConsoleWorker::DelteElement(HashMap& hashMap)
{
	std::cout << "Input identificator name" << std::endl;
	std::string name;
	std::cin >> name;
	if (hashMap.DeleteElement(name))
	{
		std::cout << "ok !" << std::endl;
	}
	else
	{
		std::cout << "Error !" << std::endl;
	}
}

void IConsoleWorker::ChooseToDo(HashMap& hashMap)
{
	bool is_needToWork = true;
	int choose = -1;
	while (is_needToWork)
	{
		std::cout << "This is interface of Hash Map choose option to do" << std::endl;
		std::cout << "To Add ElementTo Map input:------1" << std::endl;
		std::cout << "To Edit element in map input:----2" << std::endl;
		std::cout << "To Print element in map input:---3" << std::endl;
		std::cout << "To Delete element in map input:--4" << std::endl;
		std::cout << "To Exit programm input:----------5" << std::endl;
		std::cin >> choose;

		if (choose == 1)
		{
			AddElement(hashMap);
		}
		else if (choose == 2)
		{
			EditElement(hashMap);
		}
		else if (choose == 3)
		{
			PrintElement(hashMap);
		}
		else if (choose == 4 )
		{
			DelteElement(hashMap);
		}
		else if (choose == 5)
		{
			break;
		}
		else
		{
			std::cout << "Incorrect command try again" << std::endl;
		}
	}
}