#include "DataToCalc.h"
#include "UserString.h"
#include <vector>
#include <list>

#include <regex>
#include <iostream>


#define ErrorToken 10;

DataToCalc::~DataToCalc()
{

}

DataToCalc::DataToCalc()
{

}

bool DataToCalc::SetData(UserString &userString,HashMap &hashMap)
{
	auto list = userString.GetList();
	std::regex intRegex("[0-9]+");
	std::list<std::string> preStack;
	for (std::string token : *list)
	{
		auto hashMapResponse = hashMap.FindItem(token);
		if (token == "+" || token == "-")
		{
			if (preStack.size() == 0)
			{
				preStack.push_back(token);
			}
			else
			{
				if (preStack.back() == "+" || preStack.back() == "-")
				{
					mOperations.push_back(token);
				}
				else
				{
					preStack.push_back(token);
				}
			}
		}
		else if (token == "/" || token == "*")
		{
			if (preStack.size() == 0)
			{
				preStack.push_back(token);
			}
			else
			{
				if (preStack.back() == "*" || preStack.back() == "/")
				{
					mOperations.push_back(token);
				}
				else
				{
					preStack.push_back(token);
				}
			}
		}
		else if (std::regex_match(token, intRegex))
		{
			this->mOperations.push_back(token);
		}
		else if (hashMapResponse != NULL)
		{
			auto resevedString = hashMapResponse->GetIterator_Begin()->GetAddictionData();
			if (std::regex_match(*resevedString, intRegex))
			{
				this->mOperations.push_back(*resevedString);
			}
			else
			{
				std::cout << "Error this wariable is not avaible to math operation" << *resevedString << std::endl;
				return false;
			}
		}
		else
		{
			std::cout << "Error -- UdefinedToken" << token << std::endl;
			return false;
		}
	}
	for (auto op : preStack)
	{
		mOperations.push_back(op);
	}
	return true;
}

DataToCalc::DataToCalc(UserString &userString,HashMap& hashMap)
{
	SetData(userString, hashMap);
}

std::vector<std::string>* DataToCalc::GetPointer()
{
	return &(mOperations);
}