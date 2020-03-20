package com.jnn.expressions;

import com.jnn.fractal.MainActivity;

public class ComplexMath {
	
	public static MathOperand log(MathOperand complex, MathOperand result)
	{
		
		
		result.a =StrictMath.log(StrictMath.sqrt(complex.a*complex.a+complex.b*complex.b)); 
		
		if (result.a == Double.NEGATIVE_INFINITY)
			result.a = -1 * (MainActivity.rj.currentInfinity -1);
		
		if (result.a == Double.POSITIVE_INFINITY)
			result.a = MainActivity.rj.currentInfinity +1 ;
		
		result.b =StrictMath.atan2(complex.b,complex.a);
				
//		result.a=Math.log(abs(complex));
//		result.b =StrictMath.atan2(complex.b,complex.a);
		
	    if(result.b >StrictMath.PI) 
	    	result.b = result.b - 2.0 * StrictMath.PI;
	    
	    return result;
	}
	
	public static MathOperand exp(MathOperand complex, MathOperand result)
	{

		/*
		// compute e to the power of the complex number 
		  public Complex exp(){double exp_x=StrictMath.exp(this.x);
		                       return new Complex
		                       (exp_x*StrictMath.cos(this.y),
		                        exp_x*StrictMath.sin(this.y));}
		 
			 */
		
		
		result.a = StrictMath.exp(complex.a);
		result.tempA = result.a * StrictMath.cos(complex.b);
		result.b = result.a * StrictMath.sin(complex.b);
		result.a=result.tempA;

		return result;
	}
	
	public static MathOperand abs(MathOperand complex, MathOperand result)
	{

		/*
		// compute e to the power of the complex number 
		  public Complex exp(){double exp_x=StrictMath.exp(this.x);
		                       return new Complex
		                       (exp_x*StrictMath.cos(this.y),
		                        exp_x*StrictMath.sin(this.y));}
		 
			 */
		
		result.a = StrictMath.sqrt(complex.a*complex.a+complex.b*complex.b);
		result.b = 0;
		return result;
	}

	public static MathOperand tan(MathOperand c, MathOperand result)
	 {
        
        if (c.b > 20.0) {
        	result.a=0;
        	result.b=1;
        	return result;
        }
        if (c.b < -20.0) {
        	result.a=0;
        	result.b=-1;
        	return result;            
        }

        c.a = 2.0 * c.a;
        c.b = 2.0 * c.b;
        c.tempA = Math.cos(c.a) + Math.cosh(c.b);

        result.a=Math.sin(c.a) / c.tempA;
        result.b=Math.sinh(c.b) / c.tempA;
        return result;
        
    }

}
