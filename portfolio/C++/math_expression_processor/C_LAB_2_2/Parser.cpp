#include "Parser.h"
#include <string>
#include "UserString.h"

Parser::Parser()
{

}

Parser::~Parser()
{

}

UserString Parser::Parse(std::string string,char parseBy)
{
	UserString stack;
	size_t placeInString = 0;
	auto headIterator = string.begin();
	auto preHeadIterator = headIterator;
	std::string substr;
	while (headIterator != string.end())
	{
		headIterator = std::find(headIterator, string.end(), parseBy);
		size_t lengthOfWord = std::distance(preHeadIterator, headIterator);
		size_t positionOfWord = std::distance(string.begin(), preHeadIterator);
		substr = string.substr(positionOfWord, lengthOfWord);
		stack.AddInBack(substr);
		if (headIterator != string.end())
			headIterator++;
		preHeadIterator = headIterator;
	}
	return stack;
}