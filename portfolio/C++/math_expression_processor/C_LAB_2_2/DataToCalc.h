#pragma once
#include <vector>
#include "UserString.h"
#include "../C_LAB_1/HashMap.h"

/*
#define SUMM 1
#define SUB 2
#define EX 3
#define DIVISION 4
#define EQUAL 5
*/

class DataToCalc
{
private:
	std::vector<std::string> mOperations;
public:
	DataToCalc();
	DataToCalc(UserString &UserExp,HashMap &hashMap);

	bool SetData(UserString &UserExp,HashMap &hashMap);
	
	std::vector<std::string>* GetPointer();

	~DataToCalc();
};