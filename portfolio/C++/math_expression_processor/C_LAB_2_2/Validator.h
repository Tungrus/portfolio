#pragma once
#include "UserString.h"

class Validator
{
public:
	Validator();
	~Validator();
	bool validate(UserString object);
};