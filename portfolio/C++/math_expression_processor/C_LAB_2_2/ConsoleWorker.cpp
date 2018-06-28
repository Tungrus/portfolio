#include "Console.h"
#include "UserString.h"
#include "Reader.h"
#include "Parser.h"
#include "Validator.h"
#include "DataToCalc.h"
#include "Calc.h"
#include "../C_LAB_1/HashMap.h"

#include <iostream>
#include <string>
#include <regex>

ConsoleWorker::ConsoleWorker()
{

}

ConsoleWorker::~ConsoleWorker()
{

}


void ConsoleWorker::Work()
{
	Parser parser;
	Validator validate;
	DataToCalc dataToCalculate;
	Calc calc;
	HashMap hashMap;
	std::regex MathOperatoin("^[(A-z)*] =");
	std::regex RGBParser("[0-9]{3},[0-9]{3},[0-9]{3}$");
	std::regex Http("^Comment-<H>~");
	while (true)
	{
		std::cout << "Your command to programm and it may give you answer" << std::endl;
		std::string userString;
		std::getline(std::cin, userString);
		if (std::regex_search(userString, MathOperatoin))
		{
			if (dataToCalculate.GetPointer()->size() != 0)
			{
				dataToCalculate.GetPointer()->pop_back();
			}
			
			UserString procesedData = parser.Parse(userString, ' ');
			std::string identificator = procesedData.GetFirtst();
			procesedData.GetFirtst();
			if (validate.validate(procesedData))
			{
				dataToCalculate.SetData(procesedData,hashMap);
				std::string str;
				hashMap.AddItem(identificator, std::to_string(calc.Calculate(dataToCalculate)), str);
			}
		}
		else if (std::regex_match(userString, RGBParser))
		{
			std::string substr;
			int i = 0;
			for (auto iter_begin = userString.begin(), iter_end = userString.end(); iter_begin != iter_end;iter_begin+ i)
			{
				i = 0;
				bool isFound = false;
				while (*(iter_begin._Ptr) >= '0' && *(iter_begin._Ptr) <= '9')
				{
					substr.push_back(*(iter_begin._Ptr));
					iter_begin++;
					i++;
					if (atoi(substr.c_str()) >= 255)
					{
						std::cout << "Error" << std::endl;
						return;
					}
					isFound = true;
				}
				if (isFound)
				{
					if (std::distance(iter_begin, userString.end()) > 1)
					{
						iter_begin++;
					}
					else
					{
						i = 0;
					}
						
					std::cout << substr << " " << std::endl;
					substr.clear();
				}
			}
		}
		else if (std::regex_search(userString, Http))
		{
			auto iter = std::find(userString.begin(),userString.end(), '~');
			iter++;
			size_t lengthOfWord = std::distance(userString.end(), iter);
			size_t positionOfWord = std::distance(userString.begin(), iter);
			auto substr = userString.substr(positionOfWord, lengthOfWord);
			UserString procesedData = parser.Parse(substr, '.');
			for (auto dom : *procesedData.GetList())
			{
				std::cout << dom << " ";
			}
			std::cout << std::endl;
		}
		else
		{
			std::cout << "Command was not accept" << std::endl;
		}
	}
}

void ConsoleWorker::Calculate()
{

}