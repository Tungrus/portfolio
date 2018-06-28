#include "DataRecordsCollection.h"

DataRecordsCollection::DataRecordsCollection()
{

}

DataRecordsCollection::~DataRecordsCollection()
{

}

void DataRecordsCollection::AddItem(DataRecord data)
{
	this->mRecords.push_back(data);
}

std::list<DataRecord>::iterator DataRecordsCollection::GetIterator_Begin()
{
	return this->mRecords.begin();
}

std::list<DataRecord>::iterator DataRecordsCollection::GetIterator_End()
{
	return this->mRecords.end();
}