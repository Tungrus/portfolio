#include "Element.h"

void Element::SetLeftCh(Element *LeftCh)
{
	this->mLeft = LeftCh;
}

void Element::SetRigthCh(Element *RightCh)
{
	this->mRigth = RightCh;
}

void Element::SetParent(Element *parrent)
{
	this->mParent = parrent;
}

void Element::SetElement(std::string& str)
{
	this->mElement = new std::string(str);
}

Element::Element()
{
	SetLeftCh(NULL);
	SetRigthCh(NULL);
	SetParent(NULL);
	this->mElement = NULL;
	this->mOpenScale = 0;
	this->mCloseScale = 0;
}

Element::Element(Element* parrent, std::string& str)
{
	SetLeftCh(NULL);
	SetRigthCh(NULL);
	SetParent(parrent);
	this->mElement = new std::string(str);
	this->mOpenScale = 1;
	this->mCloseScale = 1;
}

Element* Element::GetRigthCh()
{
	return this->mRigth;
}

Element* Element::GetParrent()
{
	return this->mParent;
}

Element* Element::GetLeftCh()
{
	return this->mLeft;
}

std::string Element::GetElement()
{
	return *this->mElement;
}

std::string* Element::GetPointerToElement()
{
	return this->mElement;
}

Element::~Element()
{
	delete(this->mElement);
	delete(this->mLeft);
	delete(this->mRigth);
}

void Element::AddOpenScale()
{
	this->mOpenScale++;
}

int Element::GetOpenScale()
{
	return this->mOpenScale;
}

void Element::AddCloseScale()
{
	this->mCloseScale++;
}

int Element::GetCloseScale()
{
	return this->mCloseScale;
}

std::string* Element::GetElementPointer()
{
	return this->mElement;
}