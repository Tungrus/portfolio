#pragma once


#include <string>
#include <list>

#define END_OF_LIST_EXC 1

class UserString
{
private:
	std::list<std::string> mUserData;
public:
	UserString();
	~UserString();

	void AddInFront(std::string token);
	void AddInBack(std::string token);

	std::string GetFirtst();
	std::string GetLast();

	std::list<std::string>::iterator GetBeginIterator();
	std::list<std::string>* GetList();
	void PrintIfHttp();
};