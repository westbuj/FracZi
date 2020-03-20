package com.jnn.expressions;

/**
 * Insert the type's description here.
 * Creation date: (1/17/02 8:48:27 AM)
 * @author: John Westbury
 */

 import java.util.Date;
import java.util.Hashtable;
public class AtomizerGrammer {

/**
 * Insert the method's description here.
 * Creation date: (1/17/02 11:25:40 AM)
 * @return com.ffic.per.expressions.atomizer.Token
 * @exception java.lang.Exception The exception description.
 */
public static Token createToken(){
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 11:25:40 AM)
 * @return com.ffic.per.expressions.atomizer.Token
 * @exception java.lang.Exception The exception description.
 */
public static Token createToken(String param){


	if (isWhiteSpace(param))
		return new Whitespace(); 
		
	/*if (param.toUpperCase().equals("AND") || param.toUpperCase().equals("OR") || param.toUpperCase().equals("NOT"))
		return new BooleanOperator(param);

	
	if (param.toUpperCase().equals("=")||
		param.toUpperCase().equals("!=")||
		param.toUpperCase().equals("<=")||
		param.toUpperCase().equals(">=")||
		param.toUpperCase().equals(">")||
		param.toUpperCase().equals("<")||
		param.toUpperCase().equals("<>"))
		return new ComparisonOperator(param);*/
	
	
	if (param.toUpperCase().equals("*")||
			param.toUpperCase().equals("+")||
			param.toUpperCase().equals("-")||
			param.toUpperCase().equals("^")||
			param.toUpperCase().equals("/"))			
			return new ComplexMathOperator(param);
	
	if (param.toUpperCase().equals("Z"))
			return new MathOperand('Z',0,0); 


	if (param.toUpperCase().equals("C"))
			
			return new MathOperand('C',0,0);
	
	if (param.toUpperCase().equals("A"))
		
		return new MathOperand('A',0,0);
	
if (param.toUpperCase().equals("B"))
		
		return new MathOperand('B',0,0);

if (param.toUpperCase().equals("X"))
	
	return new MathOperand('X',0,0);

if (param.toUpperCase().equals("Y"))
	
	return new MathOperand('Y',0,0);

if (param.toUpperCase().equals("N"))
	
	return new MathOperand('N',0,0);

if (param.toUpperCase().equals("P"))
	
	return new MathOperand('P',0,0);

if (param.toUpperCase().equals("M"))
	
	return new MathOperand('M',0,0);

if (param.toUpperCase().equals("S"))
	
	return new MathOperand('S',0,0);

if (param.toUpperCase().equals("I"))
	
	return new MathOperand('I',0,0);

if (param.toUpperCase().equals("T"))
	
	return new MathOperand('T',0,0);

if (param.toUpperCase().equals("R"))
	
	return new MathOperand('R',0,0);

int c=0;
if (param.charAt(c) == '@')
{
	int funcStart=c;
	while (param.charAt(c) != '(')
		c++;
	int tDepth = 1;
	while (
			!(param.charAt(c) == ')' && tDepth == 0)
			&& c < param.length()
		)
		{
		c++;	
		if (param.charAt(c) == '(')
			tDepth++;
		if (param.charAt(c) == ')')
			tDepth--;

		}

	return new MathOperand(param.substring(funcStart,c+1));
	
}
		

	/*if (param.startsWith("'") && param.endsWith("'"))
			return new QuantumValue(param.substring(1,param.length()-1));
*/

	try{ 

		Double tValue=new Double(param);
		return new MathOperand('D',tValue,0);
	
	}
	catch (Exception e)
	{//not a number
	} 

	return null;
}
public static boolean isSeparator(char Token) {


	return (Token==' ' ||  Token==',')
			|| isOperator(Token);
}
	public static boolean isWhiteSpace(char param) {
 



return (param == ' ' ||  param == '\t' ||  param == '\n');// ||  param == '(' ||  param == ')' )  ;


}
	
	public static  boolean isOperator(char i){
		
		return (i == '+' ||  i == '-' ||  i == '/' ||  i == '*' ||  i == '^')  ;
		
	}
	public static boolean isWhiteSpace(String param) {

	if (param.length()==0)
		return true;
	for (int c=0;c<param.length();c++)
		{
			if (!(isWhiteSpace(param.charAt(c))))
					return false;

		}
			
	return true;
}
}
