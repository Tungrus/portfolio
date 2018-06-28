#pragma once

#include <string>
#include <list>
#include <map>
#include "DataRecord.h"
#include "DataRecordsCollection.h"


class HashMap
{
private:
	std::map<std::string, DataRecordsCollection> mHashMap;

	DataRecordsCollection* Is_AlreadyExist(std::string name);
public:
	HashMap();

	void AddItem(std::string name, std::string addictionalData, std::string links);

	DataRecord GetItem(std::string name);

	void EditItem(std::string name);

	DataRecordsCollection* FindItem(std::string name);

	bool DeleteElement(std::string name);

	~HashMap();
};
