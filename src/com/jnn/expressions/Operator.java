package com.jnn.expressions;

/**
 * Insert the type's description here.
 * Creation date: (1/17/02 8:56:12 AM)
 * @author: John Westbury
 */
public interface Operator extends Token {
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:21:25 AM)
 * @return com.ffic.per.expressions.atomizer.Token
 * @param leftOperand com.ffic.per.expressions.atomizer.Operand
 * @param rightOperand com.ffic.per.expressions.atomizer.Operand
 * @exception java.lang.Exception The exception description.
 */
//Operand evaluate(Operand leftOperand, Operand rightOperand) throws java.lang.Exception;
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 8:58:23 AM)
 * @return java.lang.String
 */
String getOperatorType();
}
