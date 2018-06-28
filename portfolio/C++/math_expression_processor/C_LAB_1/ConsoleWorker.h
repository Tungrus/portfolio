#pragma once

#include "DataRecordsCollection.h"
#include "HashMap.h"

class IConsoleWorker
{
private:
	void AddElement(HashMap& hashMap);
	void EditElement(HashMap& hashMap);
	void PrintElement(HashMap& hashMap);
	void DelteElement(HashMap& hashMap);
public:
	~IConsoleWorker();
	IConsoleWorker();
	void ChooseToDo(HashMap& hashMap);
};