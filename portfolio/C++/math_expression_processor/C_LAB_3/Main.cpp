#include <stdio.h>
#include <iostream>
#include <string>
#include <regex>
#include <list>
#include "../C_LAB_2_2/Parser.h"
#include "Expression.h"
#include "../C_LAB_1/HashMap.h"

void InserDataFromHashMap(HashMap& hashMap, UserString& userString)//
{
	Parser parser;
	std::list<std::string>::iterator it;
	std::list<std::string> list;

	for (auto beginIt = userString.GetList()->begin(), endIt = userString.GetList()->end(); beginIt != endIt; beginIt++)
	{
		auto ss = hashMap.FindItem(beginIt._Ptr->_Myval);
		if (ss != NULL)
		{
			auto processedData = parser.Parse(*(ss->GetIterator_Begin()->GetAddictionData()), ' ');
			processedData.GetFirtst();
			userString.GetList()->erase(beginIt);
			userString.GetList()->insert(beginIt, processedData.GetList()->begin(), processedData.GetList()->end());
		}
	}
}

void main()
{
	std::regex funkRegex("^[a-z*]=");
	HashMap hashMap;
	std::cout << "LABA 3 " << std::endl;
	while (true)
	{
		std::cout << "avaible funks to use write a-z leters and =  , example funk= x + x'x" << std::endl;
		std::cout << "+ - * / ^(nonVariable) ( ) arcsin( arccos( " << std::endl;
		std::cout << "exapmples :: x + x'x , arcsin( x + x )" << std::endl;
		std::string income;
		Parser parser;
		std::getline(std::cin, income);
		auto preProcessedData = parser.Parse(income, '\'');
		auto processedData = parser.Parse(preProcessedData.GetList()->front(), ' ');
		auto variable = preProcessedData.GetList()->back();
		//variable = variable.substr(1, variable.size());
		if (std::regex_search(income, funkRegex))
		{
			auto idFunk = processedData.GetFirtst();
			idFunk.pop_back();
			std::string empty;
			hashMap.AddItem(idFunk, income, empty);
		}
		Expression exp;
		exp.BuildTreeFromString(processedData);
		std::cout << "your expression geted as ::";
		exp.Print();
		exp.GetPROIZVODNAIA(variable);
		std::cout << "your proizvodnaia geted as ::";
		exp.Print();
	}
}