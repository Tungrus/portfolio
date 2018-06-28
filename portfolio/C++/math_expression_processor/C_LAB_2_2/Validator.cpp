#include <string>
#include "Validator.h"
#include <iostream>
#include "UserString.h"

Validator::~Validator()
{

}

Validator::Validator()
{

}

bool isOperation(std::string test)
{
	if (test == "+" || test == "/" || test == "*" || test == "-")
	{
		return true;
	}
	else
	{
		return false;
	}
}

bool isNumber(std::string test)
{
	if (test == "+" || test == "/" || test == "*" || test == "-")
	{
		return false;
	}
	else
	{
		return true;
	}
}


bool Validator::validate(UserString object)
{
	bool isNeedOperation = true;
	bool isNeedNumber = true;
	auto i = object.GetList();
	int k = 0;
	for (auto name : *i)
	{
		k++;
		if (isNeedNumber)
		{
			if (isNumber(name))
			{
				isNeedNumber = false;
				isNeedOperation = true;
			}
			else
			{
				std::cout << "Error at char " << k << std::endl;
				return false;
			}
		}
		else
		{
			if (isOperation(name))
			{
				isNeedNumber = true;
				isNeedOperation = false;
			}
			else
			{
				std::cout << "Error at char " << k << std::endl;
				return false;
			}
		}
	}
	if (isNeedOperation)
	{
		return true;
	}
	return false;
}