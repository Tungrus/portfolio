#pragma once 



class ConsoleWorker
{
private:
	void Calculate();
	void DomainParser();
	void RGBParser();
public:
	ConsoleWorker();
	~ConsoleWorker();
	void Work();
};