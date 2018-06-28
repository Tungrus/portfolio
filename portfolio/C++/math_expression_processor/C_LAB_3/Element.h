#pragma once
#include <string>

class Element
{
private:
	std::string* mElement;

	Element* mParent;
	Element* mLeft;
	Element* mRigth;
	int mOpenScale;
	int mCloseScale;
public:
	Element();
	Element(Element* Element,std::string &element);

	void SetLeftCh(Element* LeftCh);
	void SetRigthCh(Element* RigthCh);
	void SetParent(Element* Parrent);
	void SetElement(std::string &str);
	void AddOpenScale();
	void AddCloseScale();

	Element* GetLeftCh();
	Element* GetRigthCh();
	Element* GetParrent();
	std::string GetElement();
	std::string* GetElementPointer();

	std::string* Element::GetPointerToElement();

	int GetOpenScale();
	int GetCloseScale();


	~Element();
};