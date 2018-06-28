#pragma once
#include <list>
#include "UserString.h"

class Parser
{
public:
	Parser();
	~Parser();
	UserString Parse(std::string rededString,char parseBy);//Split
};