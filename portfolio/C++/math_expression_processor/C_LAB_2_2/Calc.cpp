#include "Calc.h"
#include "DataToCalc.h"
#include <iostream>


Calc::Calc()
{

}

Calc::~Calc()
{

}

int Calc::Calculate(DataToCalc &data)
{
	int number = 0;
	try
	{
		auto list = data.GetPointer();
		for (int i = 0, size = list->size(); i != size; i++)
		{
			number = 0;
			if (list[0][i] == "+")
			{
				number = atoi(list[0][i - 1].c_str()) + atoi(list[0][i-2].c_str());
				list->erase(list->begin() + i--);
				list->erase(list->begin() + (i--));
				list[0][i] = std::to_string(number);
			}
			else if (list[0][i] == "-")
			{
				number = atoi(list[0][i - 1].c_str()) + atoi(list[0][i - 2].c_str());
				list->erase(list->begin() + i);
				list->erase(list->begin() + (i - 1));
				list[0][i] = std::to_string(number);
			}
			else if (list[0][i] == "*")
			{

				number = atoi(list[0][i - 1].c_str()) + atoi(list[0][i - 2].c_str());
				list->erase(list->begin() + i);
				list->erase(list->begin() + (i - 1));
				list[0][i] = std::to_string(number);
			}
			else if (list[0][i] == "/")
			{
				int k = atoi(list[0][i - 1].c_str());
				if (k == 0)
				{
					std::cout << "Devision on 0 detected" << std::endl;
				}
				number = atoi(list[0][i - 2].c_str()) + k;
				list->erase(list->begin() + i);
				list->erase(list->begin() + (i - 1));
				list[0][i] = std::to_string(number);
			}
			size = list->size();
		}
	}
	catch (int i)
	{
		return number;
	}
	return number;
}