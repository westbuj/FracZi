package com.jnn.expressions;

/**
 * Insert the type's description here.
 * Creation date: (1/17/02 8:57:01 AM)
 * @author: John Westbury
 */ 

public interface Operand extends Token {
/**
 * Insert the method's description here.
 * Creation date: (1/23/02 3:00:01 PM)
 * @return boolean
 * @exception java.lang.Exception The exception description.
 */
	//public double a=0;
//	public double b=0;
boolean booleanValue() throws java.lang.Exception;
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 8:58:50 AM)
 * @return java.lang.Object
 */

Object getNativeValue();
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 8:59:29 AM)
 * @return java.lang.String
 */
String getNativeValueType();
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 1:42:31 PM)
 * @return boolean
 */

boolean isBoolean();


/**
 * Insert the method's description here.
 * Creation date: (1/24/02 7:15:15 AM)
 * @return boolean
 */
boolean isExpression();
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:00:36 AM)
 * @return java.lang.Object
 * @param param java.lang.Class
 * @exception java.lang.Exception The exception description.
 */
Object safeCast(Class param) throws java.lang.Exception;
}
