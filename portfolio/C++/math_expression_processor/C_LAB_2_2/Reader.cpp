#include "Reader.h"
#include <iostream>
#include "Validator.h"


Reader::Reader()
{

}

Reader::~Reader()
{

}

std::string Reader::Read()
{
	std::string buf;
	std::cin >> buf;
	return buf;
}