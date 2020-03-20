package com.jnn.expressions;


/**
 * Insert the type's description here.
 * Creation date: (1/17/02 8:47:49 AM)
 * @author: John Westbury
 */
 
 import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import com.jnn.fractal.RenderJob;

import android.util.Log;
 
public class Expression implements Operand{
	public Token[]  subExpressions = new Token[50];
	private java.lang.String rawExpression;
	public Boolean expressionSolution;
//	private boolean ResultReady = false;
	MathOperand tempL = new MathOperand('V',0,0);
	MathOperand tempR = new MathOperand('V',0,0);
	
	public String lastError = "";
	public boolean isError;
//	RenderJob vStack=null;
	
/**
 * Expression constructor comment.
 */
public Expression() {
	super();
	//ResultReady=false;
}
/**
 * Expression constructor comment.
 */
public Expression(String iExpression){
	super();
	//ResultReady=false;
	this.lastError="";
	this.isError=!parseExpression(iExpression,null);
	
	
	
}


/**
 * Expression constructor comment.
 */
/**
 * Insert the method's description here.
 * Creation date: (1/23/02 3:00:14 PM)
 * @return boolean
 * @exception java.lang.Exception The exception description.
 */
public boolean booleanValue() throws java.lang.Exception {
	
		return expressionSolution.booleanValue();
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:23:25 AM)
 * @return java.lang.Object
 */
public java.lang.Object getNativeValue() {
	return expressionSolution;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:23:25 AM)
 * @return java.lang.String
 */
public java.lang.String getNativeValueType() {
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (1/24/02 7:10:25 AM)
 * @return boolean
 */
public boolean getPrimativeValue() {
	return expressionSolution.booleanValue(); 
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 11:19:29 AM)
 * @return java.lang.String
 */
public java.lang.String getRawExpression() {
	return rawExpression;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:52:17 AM)
 * @return java.util.Vector
 */

/**
 * Insert the method's description here.
 * Creation date: (1/17/02 1:44:24 PM)
 * @return boolean
 */
public boolean isBoolean() {
	return true;
}
/**
 * Insert the method's description here.
 * Creation date: (1/24/02 7:15:32 AM)
 * @return boolean
 */
public boolean isExpression() {
	return true;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:23:25 AM)
 * @return boolean
 */
public boolean isOperand() {
	return true;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:23:25 AM)
 * @return boolean
 */
public boolean isOperator() {
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (1/24/02 7:07:35 AM)
 * @return boolean
 */
public boolean isResultReady() {
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:23:25 AM)
 * @return boolean
 */
public boolean isWhiteSpace() {
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 11:41:43 AM)
 * @param args java.lang.String[]
 */
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 11:17:54 AM)
 * @return boolean
 * @exception java.lang.Exception The exception description.
 */

public OpReturn getNextOperator(String expression, int j)
{
	OpReturn ret = new OpReturn();
	
	
	while (AtomizerGrammer.isWhiteSpace(expression.charAt(j)) && j < expression.length())
		j++;
	
	Token t =  AtomizerGrammer.createToken(String.valueOf(expression.charAt(j)));
	if (t == null)
	 {
		ret.errorMessage = "Operator Expected: " + expression.substring(0,j);
		return ret;
	 }
	if (!t.isOperator()){
		ret.errorMessage = "Operator Expected: " + t.toString();
		return ret;
	}
	
	ret.operator=(ComplexMathOperator) t;
	ret.nextChar = j+1;
	return ret;
}
public OpReturn getNextOperand(String expression, int j)
{
	OpReturn ret = new OpReturn();
	
	while (AtomizerGrammer.isWhiteSpace(expression.charAt(j)))
		j++;
	
	switch (expression.charAt(j))
	{
	case '@':
		int funcStart=j;
		while (expression.charAt(j) != '(')
			j++;
		int tDepth = 1;
		while (!(expression.charAt(j) == ')' && tDepth == 0)
				&& j < expression.length()
			)
			{
			j++;	
			if (expression.charAt(j) == '(')
				tDepth++;
			if (expression.charAt(j) == ')')
				tDepth--;

			}

		MathOperand func = this.createFunction(expression.substring(funcStart,j+1));
		if (func == null)
		 {
			ret.errorMessage = "function not understood: " + expression.substring(funcStart,j+1);
			return ret;
		 }
		if (this.isError) {
			ret.errorMessage = this.lastError;
			return ret;
		 }
		

		ret.operand = func;
		
		//subExpressions[cExp++] = func; 
	
		ret.nextChar = j+1;
	
		return ret;
		
		case '(':
			int startPos = j;
			tDepth = 1;
			while (
					!(expression.charAt(j) == ')' && tDepth == 0)
					&& j < expression.length()
				)
				{
				j++;
				if (j >= expression.length()){
					ret.errorMessage = "Parenthesis mismatch";
					return ret;
					
				}
				if (expression.charAt(j) == '(')
					tDepth++;
				if (expression.charAt(j) == ')')
					tDepth--;

				
				}
			//sad
			ret.operand = new MathOperand(expression.substring(startPos+1,j));
			if (ret.operand == null)
			{
				ret.errorMessage = "Invalid expression: " + expression.substring(0,j);
				return ret;
			 }
			if (ret.operand.iExpression == null)
			{
				ret.errorMessage = "Invalid expression: " + expression.substring(0,j);
				return ret;
			 }
			
			if (ret.operand.iExpression.lastError.length()>0)
			{
				ret.errorMessage = ret.operand.iExpression.lastError;
				return ret;
			 }
			ret.nextChar=j+1;
			return ret;				
			
		case ')':
			ret.errorMessage = "Unmatched parenthesis at char #" + Integer.toString(j+1);
			return ret;
			
		
	}
	
	StringBuffer buff = new StringBuffer();
	
	buff.append(expression.charAt(j));
	j++;
	while(j<expression.length() && 
			!AtomizerGrammer.isSeparator(expression.charAt(j)))
	{
		buff.append(expression.charAt(j));
		j++;
	}
	
	
	
	Token t = AtomizerGrammer.createToken(buff.toString());
	
	if (t.isOperand())
		{ret.operand=(MathOperand) t;
		 ret.nextChar = j;
		 return ret;
		}
	else{
		ret.errorMessage = "Operand expected: " + t.toString();
		return ret;
	}
	
	//ret.errorMessage = "Operand expected: " + t.toString();
	//return ret;
}
public boolean parseExpression(String inExpression, Hashtable iTuple){
	int cExp = 0;
	int position = 0;
	OpReturn op = getNextOperand(inExpression,position);
	if (op.errorMessage.length()>0)
	{
		this.lastError = "Operand Expected";
		return false;
	}
	subExpressions[cExp++] = op.operand; 
	position = op.nextChar;
	while (position < inExpression.length() && AtomizerGrammer.isWhiteSpace(inExpression.charAt(position)) )
		position++;
	
	while (position < inExpression.length())
	{
		op = getNextOperator(inExpression,position);
		if (op.errorMessage.length()>0)
		{
			this.lastError = "Operator Expected";
			return false;
		}
		subExpressions[cExp++] = op.operator;
		position = op.nextChar;
		
		
		 op = getNextOperand(inExpression,position);
			if (op.errorMessage.length()>0)
			{
				this.lastError = op.errorMessage;
				return false;
			}
			subExpressions[cExp++] = op.operand; 
			position = op.nextChar;
			if (position < inExpression.length())
				while (position < inExpression.length() && AtomizerGrammer.isWhiteSpace(inExpression.charAt(position)))
					position++;
		
		
	}
	return true;
	
}

	
	
	public boolean parseExpression_OLD(String inExpression, Hashtable iTuple){
		
		int cExp = 0;
		
	setRawExpression(inExpression); 
	//Parse grouping parens

	String tempExpression=inExpression;
	
	int depth = 1;
	int c = -1;
	int startChar = 0;
	StringBuffer cOper=new StringBuffer();
	
	try{

		for (int j = 0 ; j< 50 ; j ++)
			subExpressions[j]=null;
		
		
				while (depth < 2 && c++ < tempExpression.length())
				{
					
					if (c >= tempExpression.length())
						break;
					
					while (AtomizerGrammer.isWhiteSpace(tempExpression.charAt(c)))
						{c++;
						
						if (c >= tempExpression.length())
							break;
						}
					
					if (c >= tempExpression.length())
						break;
					
					switch (tempExpression.charAt(c))
					{
					case '@':
						int funcStart=c;
						while (tempExpression.charAt(c) != '(')
							c++;
						int tDepth = 1;
						while (
								!(tempExpression.charAt(c) == ')' && tDepth == 0)
								&& c < tempExpression.length()
							)
							{
							c++;	
							if (tempExpression.charAt(c) == '(')
								tDepth++;
							if (tempExpression.charAt(c) == ')')
								tDepth--;

							}

						MathOperand func = this.createFunction(tempExpression.substring(funcStart,c+1));
						if (func == null)
						 {
							this.lastError = "function not understood: " + tempExpression.substring(funcStart,c+1);
							return false;
						 }
						if (this.isError) return false;
						
							
						subExpressions[cExp++] = func; 
						
						c++;
						if (c >= tempExpression.length())
							break;
						
						//get the next op, if any
						
						
						
						
						
						
						while (AtomizerGrammer.isWhiteSpace(tempExpression.charAt(c)) && c < tempExpression.length())
							c++;
						
						if (c == tempExpression.length()-1)
							break;
						
						Token t =  AtomizerGrammer.createToken(String.valueOf(tempExpression.charAt(c)));
						if (t == null)
						 {
							this.lastError = "Unknown Operator: " + String.valueOf(tempExpression.charAt(c));
							return false;
						 }
						if (!t.isOperator()){
							this.lastError = "Operator Expected: " + t.toString();
							return false;
						}
						
						subExpressions[cExp++] = t;
						
						cOper.setLength(0);
						//c++;
						
						//while (AtomizerGrammer.isWhiteSpace(tempExpression.charAt(c)) && c < tempExpression.length())
//							c++;

						break;
					case '(':
						depth++;
						break;
					case ')':
						depth++;
						break;
						
					default:
						
						
						
						
						
						if (AtomizerGrammer.isSeparator(tempExpression.charAt(c)))
						{
						
						 
							t =  AtomizerGrammer.createToken(cOper.toString());
							if (t == null)
							 {
								this.lastError = "Unknown token: " + cOper.toString();
								return false;
							 }
							
							
								if (!t.isOperand())
								{
									this.lastError = "Operand expected: " + cOper;
									return  false;
								}
								subExpressions[cExp++] = t;
							
							
							
								
								while (AtomizerGrammer.isWhiteSpace(tempExpression.charAt(c)) && c < tempExpression.length())
									c++;
								
								if (c == tempExpression.length())
									break;
								
								t =  AtomizerGrammer.createToken(String.valueOf(tempExpression.charAt(c)));
								if (t == null)
								 {
									this.lastError = "Unknown Operator: " + String.valueOf(tempExpression.charAt(c));
									return false;
								 }
								if (!t.isOperator()){
									this.lastError = "Operator Expected: " + t.toString();
									return false;
								}
								
								subExpressions[cExp++] = t;
								
								cOper.setLength(0);
								
						   
						}
					else
					{
						cOper.append(tempExpression.charAt(c));
						
					}

						
						
						
						
						
						
					}
					
				

				
				if (depth>1)
				{
					cOper.setLength(0);
					//subExpressionRequired=true;
					c++;
					startChar=c;
					while (depth>1 && c < tempExpression.length())
					{
						if (tempExpression.charAt(c) == ')')
							depth--;

						if (tempExpression.charAt(c) == '(')
							depth++;
						
						c++;
					}

					//System.out.println(tempExpression.substring(startChar,c-1)); 
					
					 subExpressions[cExp++] = new MathOperand(tempExpression.substring(startChar,c-1));
					
					 if (c == tempExpression.length())
							break;
					 
					 while (AtomizerGrammer.isWhiteSpace(tempExpression.charAt(c)) && c < tempExpression.length())
							c++;
						
						if (c == tempExpression.length())
							break;
						
						Token t =  AtomizerGrammer.createToken(String.valueOf(tempExpression.charAt(c)));
						if (t == null)
						 {
							this.lastError = "Unknown Operator: " + String.valueOf(tempExpression.charAt(c));
							return false;
						 }
						if (!t.isOperator()){
							this.lastError = "Operator Expected: " + t.toString();
							return false;
						}
						
						subExpressions[cExp++] = t;
						
//					 c++;
				}
			
		} 
	 
				if (cOper.length() > 0)
				{
					Token t = AtomizerGrammer.createToken(cOper.toString());
				
					if (t.isOperand())
						subExpressions[cExp++] = t;
					else{
						this.lastError = "Operand expected: " + t.toString();
						return false;
					}
				}
					
				if (cExp == 0)
				{
					this.lastError = "Operand expected: null";
					return false;
				}
				if (!subExpressions[cExp -1].isOperand())
				{
					this.lastError = "Operand expected: " + subExpressions[cExp -1].toString();
					return false;
				}
			
				
	}
	
	
	
	

	
	catch (Exception e)
	{

		lastError = "The expression caused an error.";
		return false;
	}
	
	return true;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:23:25 AM)
 * @return java.lang.Object
 * @param param java.lang.Class
 * @exception java.lang.Exception The exception description.
 */
public java.lang.Object safeCast(java.lang.Class param) throws java.lang.Exception {
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 11:19:29 AM)
 * @param newRawExpression java.lang.String
 */
public void setRawExpression(java.lang.String newRawExpression) {
	rawExpression = newRawExpression;
}
/**
 * Insert the method's description here.
 * Creation date: (1/24/02 7:11:32 AM)
 * @param param boolean
 */
public void setSolution(boolean param) {
	//expressionSolution = new Boolean(param);
	//ResultReady=true;
	}

public String toString() {
	
	int j=0;
	String o="";
	
	while (subExpressions[j] != null)
		o += subExpressions[j++].toString()+" ";
		
	return o + "";		
}
@Override
public MathOperand evaluate(MathOperand result, MathOperand leftOperand, MathOperand rightOperand) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public MathOperand evaluate(MathOperand result) {
	// TODO Auto-generated method stub
	//try{
	
	//tempL = (MathOperand) subExpressions[0].evaluate(tempL);
	//tempR = (MathOperand) subExpressions[0].evaluate(tempR);
	
//	Log.d("FRAC", "Expressing " + this.toString());
		
	this.tempL =subExpressions[0].evaluate(result);  
	int j=0;
	while (subExpressions[j + 1] != null)
	{
	//	Log.d("FRAC", "Working " + o.toString()+"     "+((ComplexMathOperator) subExpressions[j]).toString() +"     "+ ((MathOperand)subExpressions[j + 1]).toString());
		result =((ComplexMathOperator) subExpressions[j+1]).evaluate(result, tempL, subExpressions[j+2].evaluate(tempR));
		tempL=result;
	//	Log.d("FRAC", "Done " + o.toString()+"  =======   "+((ComplexMathOperator) subExpressions[j]).toString() +"     "+ ((MathOperand)subExpressions[j + 1]).toString());
			
		j += 2;
		                    
	}
	return  tempL;//subExpressions[j-3];
	}
/*	catch (Exception e){
	
		e.toString();
		
		//debug.
		return null;
	}
	//return null;
	 * 
	 * 
	 * 
	 * 
	 * 
*/


public MathOperand createFunction(String inExpression){
	

	MathOperand ret = new MathOperand('F',0,0);
	ret.TYPE_ID = 'F';
	
	
	if (inExpression.startsWith("@"))
	{
		//find the function name
		int funcStart=1;
		int c = 2;
		while (inExpression.charAt(c) != '(')
			c++;
		
		ret.functionName = inExpression.substring(funcStart, c).trim().toUpperCase();
		int paramStart = c + 1;
		int tDepth = 1;
		while (
				!(inExpression.charAt(c) == ')' && tDepth == 0)
				&& c < inExpression.length()
			)
			{
			c++;	
			if (inExpression.charAt(c) == '(')
				tDepth++;
			if (inExpression.charAt(c) == ')')
				tDepth--;

			}
		int paramEnd = c ;
		//StringTokenizer s = new StringTokenizer(inExpression.substring(paramStart,paramEnd), ",");
		ArrayList<String> p = this.parseParams(inExpression.substring(paramStart,paramEnd));
		
		int pCount = 0;
		while (pCount < p.size())
		{
			Token temp =  AtomizerGrammer.createToken(p.get(pCount));
						
			if (temp != null)
			{
				if (temp.isOperand())
				{
					ret.params[pCount]=(MathOperand)temp;
					
				}
				else
				{
					this.isError = true;
					this.lastError = "Parameter is not an operand: " + p.get(pCount);
					
				}
					
			}	
				else
				{
					Expression tExp = new Expression(p.get(pCount));
					if (!tExp.isError)
					{
						MathOperand top= new MathOperand(p.get(pCount));
					//	top.iExpression=tExp;
					//	top.iExpression.rawExpression = p.get(pCount);
						ret.params[pCount]=(MathOperand)top;
					}
					else
					{
						this.isError = true;
						this.lastError = "Error in parameter: " + p.get(pCount);
						
					}
						
				}
			pCount++;
			
		}
		
		
	}
	
	

	return ret;
}


public ArrayList<String> parseParams(String param)
{
	int c=0;
	int level =0;
	StringBuffer cOper=new StringBuffer();
	ArrayList<String> ret = new ArrayList<String>();
	while (c< param.length())
	{
		switch (param.charAt(c))
		{
		case '(':
			while (param.charAt(c) != ')')
				{
					if (c >= param.length() )
					{
						this.isError=true;
						this.lastError = ") expected.";
						return null;
					}
					cOper.append(param.charAt(c));
					c++;
				}
			cOper.append(param.charAt(c));
			break;
		case ')':
			this.isError=true;
			this.lastError = "Unmatched ).";
			return null;
			
		case ',':
			ret.add(cOper.toString());
			cOper.setLength(0);
			break;
		default:
			cOper.append(param.charAt(c));
			
			
		}
		c++;
	}
	
	if (cOper.length()> 0)
		ret.add(cOper.toString());
	
	return ret;
}



}



