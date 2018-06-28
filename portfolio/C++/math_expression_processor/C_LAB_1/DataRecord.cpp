#include "DataRecord.h"


DataRecord::DataRecord(std::string mName, std::string mAddictionalData, std::string mLinks)
{
	this->mName = mName;
	this->mLinks = mLinks;
	this->mAddictionalData = mAddictionalData;
}

DataRecord::~DataRecord()
{

}

std::string* DataRecord::GetLinks()
{
	return &(this->mLinks);
}

std::string* DataRecord::GetAddictionData()
{
	return &(this->mAddictionalData);
}

void DataRecord::EditAddictionData(std::string newData)
{
	this->mAddictionalData = newData;
}
void DataRecord::EditLinks(std::string newLinks)
{
	this->mLinks = newLinks;
}