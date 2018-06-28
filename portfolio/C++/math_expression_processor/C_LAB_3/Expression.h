#pragma once

#include "Element.h"
#include <list>
#include "../C_LAB_2_2/UserString.h"

class Expression
{
private:
	Element* root;
	void makePro(std::string* str);
public:
	Expression();
	void GetPROIZVODNAIA(std::string& var);
	void BuildTreeFromString(UserString & userString);
	void Print();

	~Expression();
};