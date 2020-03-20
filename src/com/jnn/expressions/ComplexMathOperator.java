package com.jnn.expressions;

import com.jnn.fractal.MainActivity;

/**
 * Insert the type's description here.
 * Creation date: (1/17/02 9:03:24 AM)
 * @author: John Westbury
 */
public class ComplexMathOperator implements Token{
	String rawOperator = new String();
//	MathOperand rightOperand=null;
//	MathOperand leftOperand=null;
	char actualOperator;
	private MathOperand tempOperand = new MathOperand('V',0,0);
	private MathOperand tO1 = new MathOperand('V',0,0);
	private MathOperand tO2 = new MathOperand('V',0,0);
	private MathOperand tO3 = new MathOperand('V',0,0);
	private MathOperand tO4 = new MathOperand('V',0,0);
	private MathOperand tO5 = new MathOperand('V',0,0);
	private MathOperand tO6 = new MathOperand('V',0,0);
	private MathOperand tO7 = new MathOperand('V',0,0);
	
	public double tempHolder;
	public double tempHolder2;
	public double a;
	public double b;
	
/**public double a=0;
	public double b=0;
 * ComparisonOperator constructor comment.
 */
public ComplexMathOperator() {
	super();
	
	
	
}
/**
 * ComparisonOperator constructor comment.
 */
public ComplexMathOperator(String operator) {
	super();
	actualOperator = operator.charAt(0);
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:24:31 AM)
 * @return com.ffic.per.expressions.atomizer.Token
 * @param leftOperand com.ffic.per.expressions.atomizer.Operand
 * @param rightOperand com.ffic.per.expressions.atomizer.Operand
 * @exception java.lang.Exception The exception description.
 */
public MathOperand evaluate(MathOperand result_in_2, MathOperand leftOperand, MathOperand rightOperand) {
	

	//leftOperand.evaluate(result);
	//rightOperand.evaluate(result);
		
		if (actualOperator == '*') 
		{		 
		 
			
			this.tempHolder = (leftOperand.a * rightOperand.a) - (leftOperand.b * rightOperand.b);    	
			tO1.b = (leftOperand.a * rightOperand.b) + (leftOperand.b * rightOperand.a);
			tO1.a=this.tempHolder ;
		 	  
	     			 
		 return tO1; 
		}
		
		if (actualOperator == '/') 
		{			
			
		//	double den=Math.pow(w.mod(),2);
	     //   return new Complex((x*w.real()+y*w.imag())/den,(y*w.real()-x*w.imag())/den);

			//TempHolder is MOD of exponent
	/*		tempHolder = StrictMath.sqrt(rightOperand.a*rightOperand.a+rightOperand.b*rightOperand.b);
			
			//Tempholder is  pow(tempHolder, 2)
			
			tempHolder = Math.pow(tempHolder, 2);
			
			
			tempHolder= (rightOperand.a * rightOperand.a) + (rightOperand.b * rightOperand.b);
			tO1.a = ((leftOperand.a * rightOperand.a) + (leftOperand.b * rightOperand.b))/tempHolder;
			tO1.b = ((leftOperand.b * rightOperand.a) - (leftOperand.a * rightOperand.b))/tempHolder;
*/
			
			
			//rightOperand=divisor
		//	final double c = divisor.getReal(); 
	    //    final double d = divisor.getImaginary();
	        if (rightOperand.a == 0.0 && rightOperand.b == 0.0) {
	        	tO1.a=Double.POSITIVE_INFINITY; 
	        	tO1.b=Double.POSITIVE_INFINITY;
	            return tO1;
	        }

	        /*if (divisor.isInfinite() && !isInfinite()) {
	            return ZERO;
	        }*/

	        if (Math.abs(rightOperand.a) < Math.abs(rightOperand.b)) {
	            double q = rightOperand.a / rightOperand.b;
	            double denominator = rightOperand.a * q + rightOperand.a;
	            
	            tO1.a = (leftOperand.a * q + leftOperand.b) / denominator;
	            tO1.b =(leftOperand.b * q - leftOperand.a) / denominator;
	            
	            return tO1;
	            
	        } else {
	            double q = rightOperand.b / rightOperand.a;
	            double denominator = rightOperand.b * q + rightOperand.a;
	            
	            tO1.a = (leftOperand.b * q + leftOperand.a) / denominator;
	            tO1.b = (leftOperand.b - leftOperand.a * q) / denominator;
	            return tO1; 
	        }

				
				
				
				/*
///** divide the complex number by z 
  public Complex divide(Complex z){double r=z.x*z.x+z.y*z.y;
                                   return new Complex
                                   ((this.x*z.x+this.y*z.y)/r,
                                    (this.y*z.x-this.x*z.y)/r);}

				 */
				
			   /* tempOperand.a = leftOperand.a; 
			    tempOperand.b = leftOperand.b * -1;
			    
			    tempHolder = (rightOperand.a * rightOperand.a) +(rightOperand.b * rightOperand.b);
			   
			    result.a = tempOperand.a / tempHolder;
			    result.b = tempOperand.b /tempHolder;
			    return result;*/
		}
		
		if (actualOperator == '^') 
		{		 
		 
			 /** compute the leftOperand raised to the power rightOperand */
			 /* public Complex pow(Complex z){Complex a=z.multiply(this.log());
			                                return a.exp();}
			 */                               
			                         
			 	
			
		tO2 = log(leftOperand, tO2);  
		
		this.tempHolder = (rightOperand.a * tO2.a) - (rightOperand.b * tO2.b);    	
	 	tO3.b = (rightOperand.a * tO2.b) + (rightOperand.b * tO2.a);
	 	tO3.a=this.tempHolder ;
	 	  
	 	return exp(tO3, tO4);
			
		
		}
		
		if (actualOperator == '+') 
		{		 
		 
		 
			tO1.a = leftOperand.a+rightOperand.a;
			tO1.b = leftOperand.b+rightOperand.b;			
			
			
		 return tO1; 
		}
		
		if (actualOperator == '-') 
		{		 
		
			tO1.a = leftOperand.a - rightOperand.a;
			tO1.b = leftOperand.b - rightOperand.b;
		 
		 return tO1; 
		}
		
		
		return null;


	
}

/** compute the natural logarithm of the complex number */
public MathOperand log(MathOperand complex, MathOperand result_1)
{
	
	
	tO4.a =StrictMath.log(StrictMath.sqrt(complex.a*complex.a+complex.b*complex.b)); 
	
	if (tO4.a == Double.NEGATIVE_INFINITY)
		tO4.a = -1 * (MainActivity.rj.currentInfinity -1);
	
	if (tO4.a == Double.POSITIVE_INFINITY)
		tO4.a = MainActivity.rj.currentInfinity +1 ;
	
	tO4.b =StrictMath.atan2(complex.b,complex.a);
			
//	tO4.a=Math.log(abs(complex));
//	tO4.b =StrictMath.atan2(complex.b,complex.a);
	
    if(tO4.b >StrictMath.PI) 
    	tO4.b = tO4.b - 2.0 * StrictMath.PI;
    
    return tO4;
}

public MathOperand exp(MathOperand complex, MathOperand resul)
{

	/*
	// compute e to the power of the complex number 
	  public Complex exp(){double exp_x=StrictMath.exp(this.x);
	                       return new Complex
	                       (exp_x*StrictMath.cos(this.y),
	                        exp_x*StrictMath.sin(this.y));}
	 
		 */
	
	
	tO5.a = StrictMath.exp(complex.a);
	tempHolder2 = tO5.a * StrictMath.cos(complex.b);
	tO5.b = tO5.a * StrictMath.sin(complex.b);
	tO5.a=tempHolder2;

	return tO5;
}



/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:03:24 AM)
 * @return java.lang.String
 */

/**
 * Insert the method's description here.
 * Creation date: (1/17/02 1:51:59 PM)
 * @return boolean
 */


/**
 * Return the absolute value of this complex number.
 * Returns {@code NaN} if either real or imaginary part is {@code NaN}
 * and {@code Double.POSITIVE_INFINITY} if neither part is {@code NaN},
 * but at least one part is infinite.
 *
 * @return the absolute value.
 */
public static  double abs2(MathOperand complex) {
  
    if (Math.abs(complex.a) < Math.abs(complex.b)) {
        if (complex.b == 0.0) {
            return Math.abs(complex.a);
        }
        double q = complex.a / complex.b;
        
        return Math.abs(complex.b) * Math.sqrt(1 + q * q);
        
    } else {
        if (complex.a == 0.0) {
            return Math.abs(complex.b);
        }
        double q = complex.b / complex.a;
        return Math.abs(complex.a) * Math.sqrt(1 + q * q);
    }
}

public boolean isBoolean() {
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:03:24 AM)
 * @return boolean
 */
public boolean isOperand() {
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:03:24 AM)
 * @return boolean
 */
public boolean isOperator() {
	return true;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:03:24 AM)
 * @return boolean
 */
public boolean isWhiteSpace() {
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (1/17/02 9:07:47 AM)
 * @param param java.lang.String
 */

public String toString() {
	// Insert code to print the receiver here.
	// This implementation forwards the message to super. You may replace or supplement this.

	return String.valueOf(actualOperator);
	
}
@Override
public MathOperand evaluate(MathOperand result) {
	// TODO Auto-generated method stub
	return null;
}
}
