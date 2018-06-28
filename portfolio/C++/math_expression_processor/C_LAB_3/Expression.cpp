#include "../C_LAB_2_2/UserString.h"
#include "Expression.h"
#include <regex>
#include <iostream>

void ReqursFunk(UserString &userString,Element* root);
void ConnetcTree(Element* element, Element* leftTree, Element* rigthTree);
void MultSet(bool& isLeftSubtree, bool& isRigthSubtree, Element* element, Element* root, Element* leftSubtree, Element* rigthSubtree);
void ReqFunkDiv(Element* root, Element* subtree, bool& isSubtreeFoundVariable, std::string& var);
void ReqFunkMult(Element* root, Element* subtree, bool& isSubtreeFoundVariable, std::string& var);
void ReqFunkPSumm_Sub(Element* root, Element* subtree, bool& isTreeFoundVariable, std::string& var);
void Set_Sub_Summ_Tree(Element* element, Element* leftTree, Element* rigthTree, bool isLeftVar, bool isRigthVar);
void ReqFunkPow(Element* root, Element* subtree, bool& isTreeFoundVariable, std::string& var);
void ReqFunkDiv(Element* root, Element* subtree, bool& isSubtreeFoundVariable, std::string& var);
void Set_Pow(Element* element, Element* leftTree, Element* root, bool isLeftVariable);
void Set_Div_Tree(Element* element, Element* leftTree, Element* rigthTree, Element* root, bool isLeftSubtreeVar, bool isRigthSubTreeVar);
void Set_Arcsin(Element* element, Element* leftTree, Element* root, bool isVar);
void Set_Arccos(Element* element, Element* leftTree, Element* root, bool isVar);
void ReqFunk_Arcsin_Arccos(Element* root, Element* subtree, bool& isTreeFoundVariable, std::string& var);
void SummTree(Element* root, Element* addictionalTree);
void ReqursivPrint(Element* expression, bool & needEnd, bool& isFunk);
void isPriorityLower(std::string& first, std::string& second, bool& isNeedCloseScale);


Expression::Expression()
{

}

void Expression::BuildTreeFromString(UserString& userString)
{
	root = new Element();

	while (userString.GetList()->size() != 0)
	{
		std::string token = userString.GetFirtst();
		Element* addictionalTree = NULL;
		if (token == "(")//TODO
		{
			addictionalTree = new Element();
			ReqursFunk(userString, addictionalTree);
		}
		else if (token == "arcsin(" || token == "arccos(") // любая фукнция начинается отсюда.
		{
			addictionalTree = new Element(NULL, token);
			auto ss1_left = new Element();
			ReqursFunk(userString, ss1_left);
			while (ss1_left->GetParrent() != NULL)
			{
				ss1_left = ss1_left->GetParrent();
			}
			ConnetcTree(addictionalTree, ss1_left, NULL);
		}
		else if (token == ")")
		{
		}
		else
		{
			addictionalTree = new Element(NULL, token);
			ReqursFunk(userString, addictionalTree);
		}
		if (addictionalTree != NULL)
		{
			while (addictionalTree->GetParrent() != NULL)
			{
				addictionalTree = addictionalTree->GetParrent();
			}
			SummTree(root, addictionalTree);
			while (root->GetParrent() != NULL)
			{
				root = root->GetParrent();
			}
		}
	}//странный break point FIX ME при нескольких скобках ( ( x + x ) )'x
}

void ReqursFunk(UserString & userString, Element* root)
{
	std::string token;
	try
	{
		token = userString.GetFirtst();
	}
	catch (int)
	{
		return;
	}

	if (token == "+" || token == "-")
	{
		auto rootTokenPointer = root->GetPointerToElement();

		if (rootTokenPointer == NULL)
		{
			root->SetElement(token);
			ReqursFunk(userString, root);
		}
		else
		{
			auto rootToken = root->GetElement();
			if (rootToken == "*" || rootToken == "/" || rootToken == "^")
			{
				while (root->GetParrent() != NULL)
				{
					root = root->GetParrent();
				}
			}
			auto parent = new Element(NULL, token);
			root->SetParent(parent);
			parent->SetLeftCh(root);
			ReqursFunk(userString, root->GetParrent());
		}
	}
	else if (token == "*" || token == "/" || token == "^")
	{
		auto rootTokenPointer = root->GetPointerToElement();
		if (rootTokenPointer == NULL)
		{
			auto rightCh = root->GetRigthCh();
			auto newElem = new Element(root, token);
			newElem->SetLeftCh(rightCh);
			root->SetRigthCh(newElem);
			ReqursFunk(userString, root->GetRigthCh());
		}
		else
		{
			auto rootToken = root->GetElement();

			if (rootToken == "+" || rootToken == "-")
			{
				auto rightCh = root->GetRigthCh();
				auto newElem = new Element(root, token);
				newElem->SetLeftCh(rightCh);
				root->SetRigthCh(newElem);
				ReqursFunk(userString, root->GetRigthCh());
			}
			else
			{
				auto ch = new Element(NULL, token);
				ch->SetLeftCh(root);
				root->SetParent(ch);
				ReqursFunk(userString, root->GetParrent());
			}
		}
	}
	else if (token == "(")
	{
		auto el = new Element();
		el->AddOpenScale();
		ReqursFunk(userString, el);
		while (el->GetParrent() != NULL)
		{
			el = el->GetParrent();
		}
		if (userString.GetList()->size() != 0)
		{
			if (userString.GetList()->front() == ")")
			{
				delete root;
				*root = *el;
				return;
			}
			else
			{
				token = userString.GetFirtst();
				auto el1 = new Element();
				el1->SetLeftCh(el);
				el->SetParent(el1);
				el1->SetElement(token);
				if (root->GetLeftCh() == NULL)
				{
					root->SetLeftCh(el1);
				}
				else
				{
					root->SetRigthCh(el1);
				}
				el1->SetParent(root);
				ReqursFunk(userString, el1);
			}
		}
		else
		{
			if (root->GetLeftCh() == NULL)
			{
				root->SetLeftCh(el);
			}
			else
			{
				root->SetRigthCh(el);
			}
			el->SetParent(root);
			return;
		}
	}
	else if (token == ")")
	{
		root->AddCloseScale();
		return;
	}
	else if (token == "arcsin(" || token == "arccos(") // all funks type f(g(x))
	{
		root->SetElement(token);
		auto subtree = new Element();
		ReqursFunk(userString, subtree);
		root->SetLeftCh(subtree);
		subtree->SetParent(root);
	}
	else
	{
		if (root->GetElementPointer() == NULL)
		{
			root->SetElement(token);
			ReqursFunk(userString, root);
		}
		else
		{
			if (root->GetLeftCh() == NULL)
			{
				root->SetLeftCh(new Element(root, token));
				ReqursFunk(userString, root);
			}
			else
			{
				root->SetRigthCh(new Element(root, token));
				ReqursFunk(userString, root);
			}
		}
	}
}

/*void Subscale(UserString& userString, Element* root)
{
	auto token = userString.GetFirtst();
	if (token == "+" || token == "-")
	{
		auto rootToken = root->GetElement();
		if (rootToken == "*" || rootToken == "/")
		{
			while (root->GetParrent() != NULL)
			{
				root = root->GetParrent();
			}
		}
		root->SetParent(new Element(NULL, token));
		ReqursFunk(userString, root->GetParrent());
	}
	else if (token == "^" || token == "*")
	{
		auto rootToken = root->GetElement();
		if (rootToken == "+" || rootToken == "-")
		{
			auto rightCh = root->GetRigthCh();
			auto newElem = new Element(root, token);
			newElem->SetLeftCh(rightCh);
			root->SetRigthCh(newElem);
			ReqursFunk(userString, root->GetRigthCh());
		}
		else
		{
			root->SetParent(new Element(NULL, token));
			ReqursFunk(userString, root->GetParrent());
		}
	}
	else if (token == "(")
	{
		auto el = new Element();
		token = userString.GetFirtst();
		el->SetElement(token);
	}
	else if (token == ")")
	{
		return;
	}
	else
	{
		if (root->GetLeftCh() == NULL)
		{
			root->SetLeftCh(new Element(root, token));
			ReqursFunk(userString, root);
		}
		else
		{
			root->SetRigthCh(new Element(root, token));
			ReqursFunk(userString, root);
		}
	}
}*/

Expression::~Expression()
{
}

void Expression::Print()
{
	bool rul = true;
	bool isFunk = false;
	ReqursivPrint(this->root, rul, isFunk);
	std::cout << std::endl;
}

void ReqursivPrint(Element* expression, bool & needEnd, bool& isFunk)
{
	bool isHasBeenPrinted = false;
	bool isNeedCloseScale = false;
	if (expression->GetElement() == "arcsin(" || expression->GetElement() == "arccos(")
	{
		isHasBeenPrinted = true;
		std::cout << expression->GetElement();
		isNeedCloseScale = true;
	}
	if (expression->GetLeftCh() != NULL)
	{
		if (expression->GetParrent() != NULL)
		{
			isPriorityLower(expression->GetParrent()->GetElement(), expression->GetElement(), isNeedCloseScale);
		}
		ReqursivPrint(expression->GetLeftCh(), needEnd, isFunk);
	}
	if (!isHasBeenPrinted)
	{
		isHasBeenPrinted = true;
		std::cout << expression->GetElement();
	}
	if (expression->GetRigthCh() != NULL)
	{
		ReqursivPrint(expression->GetRigthCh(), needEnd, isFunk);
	}
	if (!isHasBeenPrinted)
	{
		isHasBeenPrinted = true;
		std::cout << expression->GetElement();
	}
	if (isNeedCloseScale)
	{
		std::cout << ")";
	}
}

void Expression :: GetPROIZVODNAIA(std::string& var)
{
	Element* element = new Element;

	auto leftSubTree = new Element();
	auto rigthSubTree = new Element();

	auto token = this->root->GetElement();
	if (token == "+" || token == "-")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkPSumm_Sub(root->GetLeftCh(), leftSubTree, isLeftSubtree, var);
		ReqFunkPSumm_Sub(root->GetRigthCh(), rigthSubTree, isRigthSubtree, var);

		Set_Sub_Summ_Tree(element, leftSubTree, rigthSubTree, isLeftSubtree, isRigthSubtree);
	}
	else if (token == "*")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkMult(root->GetLeftCh(), leftSubTree, isLeftSubtree, var);
		ReqFunkMult(root->GetRigthCh(), rigthSubTree, isRigthSubtree, var);
		MultSet(isLeftSubtree, isRigthSubtree, element, root, leftSubTree, rigthSubTree);
	}
	else if (token == "/")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkDiv(root->GetLeftCh(), leftSubTree, isLeftSubtree, var);
		ReqFunkDiv(root->GetRigthCh(), rigthSubTree, isRigthSubtree, var);
		Set_Div_Tree(element, leftSubTree, rigthSubTree, root, isLeftSubtree, isRigthSubtree);
	}
	else if (token == "^")//обработка случаев f(x)^g(x) запрещенна
	{
		bool isLeftSubtree = false;

		ReqFunkPow(root->GetLeftCh(), leftSubTree, isLeftSubtree, var);

		Set_Pow(element, leftSubTree, root, isLeftSubtree);
	}
	else if (token == "arcsin(")
	{
		bool isVariableInFunk = false;
		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), leftSubTree, isVariableInFunk, var);
		Set_Arcsin(element, leftSubTree, root->GetLeftCh(), isVariableInFunk);
	}
	else if (token == "arccos(")
	{
		bool isVariableInFunk = false;
		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), leftSubTree, isVariableInFunk, var);
		Set_Arccos(element, leftSubTree, root->GetLeftCh(), isVariableInFunk);
	}
	else
	{
		if (token != var)
		{
			std::string str("0");
			element->SetElement(token);
		}
		else
		{
			std::string str("1");
			element->SetElement(str);
		}
	}
	root = element;
}

void ReqFunkDiv(Element* root, Element* subtree, bool& isSubtreeFoundVariable, std::string& var)
{
	auto token = root->GetElement();

	auto leftSubtree = new Element();
	auto rigthSubtree = new Element();

	if (token == "+" || token == "-")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkPSumm_Sub(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkPSumm_Sub(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		Set_Sub_Summ_Tree(subtree, leftSubtree, rigthSubtree, isLeftSubtree, isRigthSubtree);

		if (isLeftSubtree || isRigthSubtree)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else if (token == "*")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkMult(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkMult(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		if (isLeftSubtree || isRigthSubtree)
			isSubtreeFoundVariable = true;
		MultSet(isLeftSubtree, isRigthSubtree, subtree, root, leftSubtree, rigthSubtree);
	}
	else if (token == "/")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkDiv(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkDiv(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		Set_Div_Tree(subtree, leftSubtree, rigthSubtree, root, isLeftSubtree, isRigthSubtree);

		if (isLeftSubtree || isRigthSubtree)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else if (token == "^")
	{
		bool isLeftSubtree = false;

		ReqFunkPow(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);

		Set_Pow(subtree, leftSubtree, root, isLeftSubtree);
		if (isLeftSubtree)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else if (token == "arcsin(")
	{
		bool isVariInIt = false;

		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arcsin(subtree, leftSubtree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else if (token == "arccos(")
	{
		bool isVariInIt = false;
		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arccos(subtree, leftSubtree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else
	{
		if (var != token)
		{
			subtree->SetElement(token);
		}
		else
		{
			isSubtreeFoundVariable = true;
			std::string str("1");
			subtree->SetElement(str);
		}
	}
}

void ReqFunkMult(Element* root, Element* subtree, bool& isSubtreeFoundVariable, std::string& var)
{
	auto token = root->GetElement();

	auto leftSubtree = new Element();
	auto rigthSubtree = new Element();

	if (token == "+" || token == "-")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkPSumm_Sub(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkPSumm_Sub(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);
		Set_Sub_Summ_Tree(subtree, leftSubtree, rigthSubtree, isLeftSubtree, isRigthSubtree);

		if (isLeftSubtree || isRigthSubtree)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else if (token == "*")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkMult(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkMult(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		if (isLeftSubtree || isRigthSubtree)
			isSubtreeFoundVariable = true;
		MultSet(isLeftSubtree, isRigthSubtree, subtree, root, leftSubtree, rigthSubtree);
	}
	else if (token == "/")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkDiv(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkDiv(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);
		Set_Div_Tree(subtree, leftSubtree, rigthSubtree, root, isLeftSubtree, isRigthSubtree);
		if (isLeftSubtree || isRigthSubtree)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else if (token == "^")
	{
		bool isLeftSubtree = false;

		ReqFunkPow(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);

		Set_Pow(subtree, leftSubtree, root, isLeftSubtree);
		if (isLeftSubtree)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else if (token == "arcsin(")
	{
		bool isVariInIt = false;

		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arcsin(subtree, leftSubtree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else if (token == "arccos(")
	{
		bool isVariInIt = false;
		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arccos(subtree, leftSubtree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isSubtreeFoundVariable = true;
		}
	}
	else
	{
		if (token != var)
		{
			subtree->SetElement(token);
		}
		else
		{
			isSubtreeFoundVariable = true;
			std::string str("1");
			subtree->SetElement(str);
		}
	}
}

void ReqFunkPSumm_Sub(Element* root, Element* subtree, bool& isTreeFoundVariable, std::string& var)
{
	auto token = root->GetElement();

	if (token == "+" || token == "-")
	{
		subtree->SetElement(token);
		auto leftSubTree = new Element();
		auto rigthSubTree = new Element();
		bool isLeftVar = false;
		bool isRigthVar = false;
		
		ReqFunkPSumm_Sub(root->GetLeftCh(), leftSubTree, isLeftVar, var);
		ReqFunkPSumm_Sub(root->GetRigthCh(), rigthSubTree, isRigthVar, var);

		Set_Sub_Summ_Tree(subtree, leftSubTree, rigthSubTree, isLeftVar, isRigthVar);
		if (isLeftVar || isRigthVar)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "*")
	{
		bool isLeftVar = false;
		bool isRigthVar = false;

		auto leftSubTree = new Element();
		auto rigthSubTree = new Element();

		ReqFunkMult(root->GetLeftCh(), leftSubTree, isLeftVar, var);
		ReqFunkMult(root->GetRigthCh(), rigthSubTree, isRigthVar, var);

		MultSet(isLeftVar, isRigthVar, subtree, root, leftSubTree, rigthSubTree);
		if (isLeftVar || isRigthVar)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "/")
	{
		auto leftSubTree = new Element();
		auto rigthSubTree = new Element();

		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkDiv(root->GetLeftCh(), leftSubTree, isLeftSubtree, var);
		ReqFunkDiv(root->GetRigthCh(), rigthSubTree, isRigthSubtree, var);

		Set_Div_Tree(subtree, leftSubTree, rigthSubTree, root, isLeftSubtree, isRigthSubtree);

		if (isLeftSubtree || isRigthSubtree)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "^")
	{
		bool isLeftSubtree = false;
		auto leftSubTree = new Element();

		ReqFunkPow(root->GetLeftCh(), leftSubTree, isLeftSubtree, var);
		Set_Pow(subtree, leftSubTree, root, isLeftSubtree);
		if (isLeftSubtree)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "arcsin(")
	{
		bool isVariInIt = false;
		auto leftSubTree = new Element();

		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arcsin(subtree, leftSubTree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "arccos(")
	{
		auto leftSubTree = new Element();
		bool isVariInIt = false;
		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arccos(subtree, leftSubTree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isTreeFoundVariable = true;
		}
	}
	else
	{
		if (token!= var)
		{
			std::string ss("0");
			subtree->SetElement(ss);
		}
		else
		{
			std::string ss("1");
			isTreeFoundVariable = true;
			subtree->SetElement(ss);
		}
	}
}

void ReqFunkPow(Element* root, Element* subtree, bool& isTreeFoundVariable, std::string& var)
{
	auto token = root->GetElement();

	auto leftSubtree = new Element();
	auto rigthSubtree = new Element();

	if (token == "+" || token == "-")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkPSumm_Sub(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkPSumm_Sub(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		Set_Sub_Summ_Tree(subtree, leftSubtree, rigthSubtree, isLeftSubtree, isRigthSubtree);

		if (isLeftSubtree || isRigthSubtree)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "*")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkMult(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkMult(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		if (isLeftSubtree || isRigthSubtree)
			isTreeFoundVariable = true;
		MultSet(isLeftSubtree, isRigthSubtree, subtree, root, leftSubtree, rigthSubtree);
	}
	else if (token == "/")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkDiv(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkDiv(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		Set_Div_Tree(subtree, leftSubtree, rigthSubtree, root, isLeftSubtree, isRigthSubtree);

		if (isLeftSubtree || isRigthSubtree)
			isTreeFoundVariable = true;
	}
	else if (token == "^")
	{
		bool isLeftSubtree = false;

		ReqFunkPow(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);

		Set_Pow(subtree, leftSubtree, root, isLeftSubtree);
		if (isLeftSubtree)
			isTreeFoundVariable = true;
	}
	else if (token == "arcsin(")
	{
		bool isVariInIt = false;

		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arcsin(subtree, leftSubtree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "arccos(")
	{
		bool isVariInIt = false;
		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arccos(subtree, leftSubtree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isTreeFoundVariable = true;
		}
	}
	else
	{
		if (token != var)
		{
			subtree->SetElement(token);
		}
		else
		{
			isTreeFoundVariable = true;
			std::string str("1");
			subtree->SetElement(str);
		}
	}
}

void ReqFunk_Arcsin_Arccos(Element* root, Element* subtree, bool& isTreeFoundVariable, std::string& var)
{
	auto token = root->GetElement();

	auto leftSubtree = new Element();
	auto rigthSubtree = new Element();

	if (token == "+" || token == "-")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkPSumm_Sub(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkPSumm_Sub(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		Set_Sub_Summ_Tree(subtree, leftSubtree, rigthSubtree, isLeftSubtree, isRigthSubtree);

		if (isLeftSubtree || isRigthSubtree)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "*")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkMult(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkMult(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		if (isLeftSubtree || isRigthSubtree)
			isTreeFoundVariable = true;
		MultSet(isLeftSubtree, isRigthSubtree, subtree, root, leftSubtree, rigthSubtree);
	}
	else if (token == "/")
	{
		bool isLeftSubtree = false;
		bool isRigthSubtree = false;

		ReqFunkDiv(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);
		ReqFunkDiv(root->GetRigthCh(), rigthSubtree, isRigthSubtree, var);

		Set_Div_Tree(subtree, leftSubtree, rigthSubtree, root, isLeftSubtree, isRigthSubtree);

		if (isLeftSubtree || isRigthSubtree)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "^")
	{
		bool isLeftSubtree = false;

		ReqFunkPow(root->GetLeftCh(), leftSubtree, isLeftSubtree, var);

		Set_Pow(subtree, leftSubtree, root, isLeftSubtree);
		if (isLeftSubtree)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "arcsin(")
	{
		bool isVariInIt = false;

		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arcsin(subtree, leftSubtree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isTreeFoundVariable = true;
		}
	}
	else if (token == "arccos(")
	{
		bool isVariInIt = false;
		ReqFunk_Arcsin_Arccos(root->GetLeftCh(), subtree, isVariInIt, var);
		Set_Arccos(subtree, leftSubtree, root->GetLeftCh(), isVariInIt);
		if (isVariInIt)
		{
			isTreeFoundVariable = true;
		}
	}
	else
	{
	
		if (token != var)
		{
			subtree->SetElement(token);
		}
		else
		{
			std::string str("1");
			isTreeFoundVariable = true;
			subtree->SetElement(str);
		}
	}
}

void MultSet(bool& isLeftSubtree, bool& isRigthSubtree, Element* element, Element* root, Element* leftSubtree, Element* rigthSubtree)
{
	if (isLeftSubtree && isRigthSubtree)
	{
		std::string mult("*");
		std::string plus("+");

		auto ss1_left = new Element(NULL, mult);
		auto ss1_rigth = new Element(NULL, mult);

		element->SetElement(plus);

		ConnetcTree(element, ss1_left, ss1_rigth);
		ConnetcTree(ss1_left, leftSubtree, root->GetRigthCh());
		ConnetcTree(ss1_rigth, rigthSubtree, root->GetLeftCh());
	}
	else if (isLeftSubtree)
	{
		std::string mult("*");
		element->SetElement(mult);
		element->SetLeftCh(leftSubtree);
		leftSubtree->SetParent(root);
		element->SetRigthCh(new Element(*root->GetRigthCh()));
	}
	else if (isRigthSubtree)
	{
		std::string mult("*");
		element->SetElement(mult);
		ConnetcTree(element, root->GetLeftCh(), rigthSubtree);
	}
	else
	{
		std::string result("0");
		element->SetElement(result);
	}
}

void ConnetcTree(Element* element,Element* leftTree, Element* rigthTree)
{
	element->SetLeftCh(leftTree);
	element->SetRigthCh(rigthTree);

	if (leftTree != NULL)
		leftTree->SetParent(element);

	if (rigthTree != NULL)
		rigthTree->SetParent(element);
}

void Set_Div_Tree(Element* element, Element* leftTree, Element* rigthTree, Element* root, bool isLeftSubtreeVar, bool isRigthSubTreeVar)
{
	std::string div("/");
	std::string sub("-");
	std::string pow("^");
	std::string mult("*");

	if (isLeftSubtreeVar && isRigthSubTreeVar)
	{
		std::string two("2");

		auto ss1_left = new Element(NULL, sub);
		auto ss1_rigth = new Element(NULL, pow);
		auto ss2_left_left = new Element(NULL, mult);
		auto ss2_left_rigth = new Element(NULL, mult);
		auto nn = new Element(NULL, two);
		element->SetElement(div);

		ConnetcTree(element, ss1_left, ss1_rigth);
		ConnetcTree(ss1_left, ss2_left_left, ss2_left_rigth);
		ConnetcTree(ss2_left_left, leftTree, root->GetLeftCh());
		ConnetcTree(ss2_left_rigth, rigthTree, root->GetRigthCh());
		ConnetcTree(ss1_rigth, root->GetRigthCh(), nn);
	}
	else if (isLeftSubtreeVar)
	{
		element->SetElement(div);
		ConnetcTree(element, leftTree, root->GetRigthCh());
	}
	else if (isRigthSubTreeVar)
	{
		std::string two("2");
		auto El = new Element();
		El->SetElement(two);
		auto ss1_left = new Element();
		ss1_left->SetElement(mult);
		auto ss1_rigth = new Element();
		ss1_rigth->SetElement(pow);//проблемма с отрицатеьными числами.
		element->SetElement(div);
		ConnetcTree(element, ss1_left, ss1_rigth);
		ConnetcTree(ss1_left, root->GetLeftCh(), leftTree);
		ConnetcTree(ss1_rigth, root->GetLeftCh(), El);
	}
	else
	{
		std::string nul("0");
		element->SetElement(nul);
	}
}

void Set_Sub_Summ_Tree(Element* element, Element* leftTree, Element* rigthTree, bool isLeftVar,bool isRigthVar)
{
	if (isLeftVar && isRigthVar)
	{
		std::string sum("+");
		element->SetElement(sum);
		ConnetcTree(element, leftTree, rigthTree);
	}
	else if (isLeftVar)
	{
		*element = *leftTree;
	}
	else if (isRigthVar)
	{
		*element = *rigthTree;
	}
	else
	{
		std::string null("0");
		element->SetElement(null);
	}
	
}

void Set_Pow(Element* element, Element* leftTree, Element* root, bool isLeftVariable)
{
	if (isLeftVariable)
	{
		std::string mult("*");
		std::string pow("^");
		std::string sub("-");
		std::string one("1");

		auto ss1_left = new Element(NULL, mult);
		auto ss2_left_left = new Element(NULL, pow);
		auto ss3_left_left_rigth = new Element(NULL, sub);
		auto ss4_ll_rr = new Element(NULL, one);
		
		element->SetElement(mult);
		ConnetcTree(element, ss1_left, root->GetRigthCh());
		ConnetcTree(ss1_left, ss2_left_left, leftTree);
		ConnetcTree(ss2_left_left, root->GetLeftCh(), ss3_left_left_rigth);
		ConnetcTree(ss3_left_left_rigth, root->GetRigthCh(), ss4_ll_rr);
	}
	else
	{
		std::string null("0");
		element->SetElement(null);
	}
}

void Set_Arcsin(Element* element, Element* leftTree, Element* root, bool isVar)
{
	if (isVar)
	{
		std::string mult("*");
		std::string pow("^");
		std::string div("/");
		std::string half("1/2");
		std::string two("2");
		std::string sub("-");
		std::string one("1");

		element->SetElement(div);

		auto ss1_left = new Element(NULL, mult);
		auto ss1_rigth = new Element(NULL, pow);
		auto ss2_rigth_rigth = new Element(NULL, half);
		auto ss2_rigth_left = new Element(NULL, sub);
		auto ss3_rigth_left_left = new Element(NULL, one);
		auto ss3_rigth_left_rigth = new Element(NULL, pow);
		auto ss4_rigth_left_rigth_rigth = new Element(NULL, two);

		ConnetcTree(element, leftTree, ss1_rigth);
		ConnetcTree(ss1_rigth, ss2_rigth_left, ss2_rigth_rigth);
		ConnetcTree(ss2_rigth_left, ss3_rigth_left_left, ss3_rigth_left_rigth);
		ConnetcTree(ss3_rigth_left_rigth, root, ss4_rigth_left_rigth_rigth);
	}
	else
	{
		std::string null("0");
		element->SetElement(null);
	}
}

void Set_Arccos(Element* element, Element* leftTree, Element* root, bool isVar)
{
	if (isVar)
	{
		std::string negOne("(-1)");
		std::string mult("*");
		std::string pow("^");
		std::string div("/");
		std::string half("1/2");
		std::string two("2");
		std::string sub("-");
		std::string one("1");

		element->SetElement(div);

		auto ss1_left = new Element(NULL, mult);
		auto ss1_rigth = new Element(NULL, pow);
		auto ss2_left_left = new Element(NULL, negOne);
		auto ss2_rigth_rigth = new Element(NULL, half);
		auto ss2_rigth_left = new Element(NULL, sub);
		auto ss3_rigth_left_left = new Element(NULL, one);
		auto ss3_rigth_left_rigth = new Element(NULL, pow);
		auto ss4_rigth_left_rigth_rigth = new Element(NULL, two);
		
		ConnetcTree(element, ss1_left, ss1_rigth);
		ConnetcTree(ss1_left, leftTree, ss2_left_left);
		ConnetcTree(ss1_rigth, ss2_rigth_left, ss2_rigth_rigth);
		ConnetcTree(ss2_rigth_left, ss3_rigth_left_left, ss3_rigth_left_rigth);
		ConnetcTree(ss3_rigth_left_rigth, root, ss4_rigth_left_rigth_rigth);
	}
	else
	{
		std::string null("0");
		element->SetElement(null);
	}
}

void SummTree(Element* root, Element* addictionalTree)
{
	if (root->GetElementPointer() == NULL)
	{
		*root = *addictionalTree;
	}
	else
	{
		addictionalTree->SetRigthCh(root);
		root->SetParent(addictionalTree);
	}
}

void isPriorityLower(std::string& first, std::string& second, bool& isNeedCloseScale)
{
	if (first == "*" || first == "/")
	{
		if (second == "+" || second == "-")
		{
			isNeedCloseScale = true;
			std::cout << "(";
		}
	}
	else if (first == "^")
	{
		if (second == "*" || second == "/" || second == "+" || second == "-")
		{
			isNeedCloseScale = true;
			std::cout << "(";
		}
	}
	else if (second == "arccos(" || second == "arcsin(")
	{
		isNeedCloseScale = true;
	}
}