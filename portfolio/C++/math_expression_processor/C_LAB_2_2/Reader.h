#pragma once
#include "UserString.h"

#define INVALID_STRING 2

class Reader
{
public:
	Reader();
	std::string Read();
	~Reader();
};