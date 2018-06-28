#pragma once

#include <string>

struct Nigga
{
	int type;
	void *someData;
};

class AddictionalData
{

	
protected:
	AddictionalData() : mLeter(NULL){};
public:
	AddictionalData* mLeter;
	AddictionalData();
	~AddictionalData();
};

class Leter : AddictionalData
{
private:

public:
	Leter();
	~Leter();
};

