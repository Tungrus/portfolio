#pragma once

#include <list>
#include "DataRecord.h"

class DataRecordsCollection
{
private:
	std::list<DataRecord> mRecords;
public:
	DataRecordsCollection();

	void AddItem(DataRecord data);

	std::list<DataRecord>::iterator GetIterator_Begin();
	std::list<DataRecord>::iterator GetIterator_End();

	~DataRecordsCollection();
};