#pragma once

#include <string>
#include <list>

class DataRecord
{
private:
	std::string mName;
	std::string mAddictionalData;
	std::string mLinks;
public:
	DataRecord(std::string mName, std::string mAddictionalData, std::string mLinks);
	
	void EditAddictionData(std::string newData);
	void EditLinks(std::string newData);

	std::string* GetAddictionData();
	std::string* GetLinks();

	~DataRecord();
};
