package com.jnn.expressions;

import com.jnn.fractal.RenderJob;

/**
 * Insert the type's description here.
 * Creation date: (1/17/02 8:49:27 AM)
 * @author: John Westbury
 */
public interface Token extends java.io.Serializable {
	
	
	MathOperand evaluate(MathOperand result,MathOperand leftOperand, MathOperand rightOperand);
	MathOperand evaluate(MathOperand result);

/**
 * Insert the method's description here.
 * Creation date: (1/17/02 1:51:24 PM)
 * @return boolean
 */
boolean isBoolean();
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 8:54:05 AM)
 * @return boolean
 */
boolean isOperand();
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 8:53:43 AM)
 * @return boolean
 */
boolean isOperator();
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 8:55:10 AM)
 * @return boolean
 */
boolean isWhiteSpace();
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 8:50:24 AM)
 * @return java.lang.String
 */
String toString();
}
