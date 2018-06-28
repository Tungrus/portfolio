#include "UserString.h"
#include <iostream>
UserString::UserString()
{

}

UserString::~UserString()
{

}

std::string UserString::GetFirtst()
{
	if (this->mUserData.size() == 0)
		throw END_OF_LIST_EXC;
	std::string buf = this->mUserData.front();
	this->mUserData.pop_front();
	return buf;
}

std::string UserString::GetLast()
{
	if (this->mUserData.size() == 0)
		throw END_OF_LIST_EXC;
	std::string buf = this->mUserData.back();
	this->mUserData.pop_back();
	return buf;
}

void UserString::AddInFront(std::string token)
{
	this->mUserData.push_front(token);
}

void UserString::AddInBack(std::string token)
{
	this->mUserData.push_back(token);
}

std::list <std::string> *UserString::GetList()
{
	return &(this->mUserData);
}

std::list<std::string>::iterator UserString::GetBeginIterator()
{
	return mUserData.begin();
}

void UserString::PrintIfHttp()
{
	int size = this->mUserData.size();
	if (size < 6 && size < 0)
	{
		bool first = true;
		for (auto str : mUserData)
		{
			if (first)
			{
				first = false;
				if (str.size() <= 4)
				{
					std::cout << str << std::endl;
				}
			}
			else
			{
				std::cout << str << std::endl;
			}
		}
	}
	else
	{
		std::cout << "Error of Length" << std::endl;
	}
}