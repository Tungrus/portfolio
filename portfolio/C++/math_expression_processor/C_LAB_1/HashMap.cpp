#include <map>
#include "HashMap.h"
#include "DataRecord.h"


HashMap::HashMap()
{

}

HashMap::~HashMap()
{

}

void HashMap::AddItem(std::string name, std::string addictionalData, std::string links)
{
	auto iter = mHashMap.find(name);
	if (iter == mHashMap.end())
	{
		DataRecord data(name, addictionalData, links);
		DataRecordsCollection newDataCollection;
		newDataCollection.AddItem(data);
		mHashMap[name] = newDataCollection;
	}
	else
	{
		DataRecord data(name, addictionalData, links);
		iter->second.AddItem(data);
	}
}

DataRecordsCollection* HashMap::FindItem(std::string name)
{
	auto iter = mHashMap.find(name);
	if (iter == mHashMap.end())
	{
		return NULL;
	}
	else
	{
		return &iter->second;
	}
}

bool HashMap::DeleteElement(std::string name)
{
	auto iter = mHashMap.find(name);
	if (iter != mHashMap.end())
	{
		this->mHashMap.erase(iter);
		return true;
	}
	else
	{
		return false;
	}
}