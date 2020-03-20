package com.jnn.expressions;

import java.util.StringTokenizer;

import com.jnn.fractal.MainActivity;
import com.jnn.fractal.RenderJob;

public class MathOperand implements Token {

	MathOperand[] params = new MathOperand[5];
	
	public double a;	
	public double b; 
	
	public double tempA;
	public double tempB;
//	public double ra;
//	public double rb;
	MathOperand result=null;
	//MathOperand tempComplex = new MathOperand('V',0,0);
	//public boolean returnSet=false;
	public char TYPE_ID = 0;
	public Expression iExpression = null;
	String functionName = null;
	//public RenderJob vStack = null;
	
	
	
	public MathOperand(String inExpression){
		
		a = 0;
		b = 0;
		
		
			TYPE_ID='E';
		
		
			iExpression = new Expression(inExpression);
		
		
	}
	
	
	public MathOperand(char id, double real, double imaginary) {
		
		a = real;
		b = imaginary;
		TYPE_ID=id;
		
	}
	
	
	public MathOperand(double real, double imaginary) {
		
		a = real;
		b = imaginary;
	}


	@Override
	public boolean isBoolean() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isOperand() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isOperator() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isWhiteSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
	
		
		switch(TYPE_ID)
		{
		case 'E':
			return "("+iExpression.toString()+")";
			
		case 'D':
			return Double.toString(this.a);
		
		case 'F':			
			String pString="";
			int j = 0;
			while (this.params[j] != null)
			{
				if (pString.length()>0)
					pString +=",";
				
				pString += this.params[j].toString();
				j++;
			}
			
			return "@"+this.functionName+"("+pString+")";
			
			
		default:	
			return String.valueOf(TYPE_ID);
			
		}
		
		
	
	}


	public MathOperand evaluate(Operand leftOperand, Operand rightOperand){
		return null;
	}
	@Override
	public MathOperand evaluate(MathOperand returnComplex_2){
		// TODO Auto-generated method stub
		
		
		switch(this.TYPE_ID) 
		{
		case 'F':
			if (this.functionName.equals("COM"))
			{
				//this.tempComplex = 
				this.a = params[0].evaluate(null).a;
				this.b = params[1].evaluate(null).a;
				return this;
			}
			if (this.functionName.equals("LOG"))
			{
				return ComplexMath.log(params[0].evaluate(null),this); 
				
			}
			
			if (this.functionName.equals("EXP"))
			{
				return ComplexMath.exp(params[0].evaluate(null),this); 
				
			}
			
			if (this.functionName.equals("SIN"))
			{
				this.result =params[0].evaluate(null);
				
			
				
				this.a = Math.sin(result.a) * Math.cosh(result.b);
				this.b =Math.cos(result.a) * Math.sinh(result.b);
								
				return this;
			}
			
			if (this.functionName.equals("COS"))
			{
				this.result =params[0].evaluate(null);
				
					
				
				this.a = Math.cos(result.a) * Math.cosh(result.b);
				this.b =Math.sin(result.a) * Math.sinh(result.b);
								
				return this;
			}
			
			if (this.functionName.equals("TAN"))
			{
				
				return ComplexMath.tan(params[0].evaluate(null),this); 
				
			}
			
			if (this.functionName.equals("ABS"))
			{
				return ComplexMath.abs(params[0].evaluate(null),this); 
				
			}
			if (this.functionName.equals("MIN"))
			{
				this.a=Math.min(params[0].evaluate(null).a, params[1].evaluate(null).a);
				this.b=Math.min(params[0].evaluate(null).b, params[1].evaluate(null).b);
				return this; 
				
			}
			if (this.functionName.equals("MAX"))
			{
				this.a=Math.max(params[0].evaluate(null).a, params[1].evaluate(null).a);
				this.b=Math.max(params[0].evaluate(null).b, params[1].evaluate(null).b);
				return this; 
				
			}
			
			if (this.functionName.equals("BOUND"))
			{
				
				this.a=params[0].evaluate(null).a;
				this.tempA =  params[1].evaluate(null).a;
				this.tempB =  params[2].evaluate(null).a;
				
				if (this.a < this.tempA)
					this.a = this.tempA;
				
				if (this.a > this.tempB)
					this.a = this.tempB;
								
				return this; 
				
			}
			
			if (this.functionName.equals("WRAP"))
			{
				
				this.a=params[0].evaluate(null).a;
				this.tempA =  params[1].evaluate(null).a;
				this.tempB =  params[2].evaluate(null).a;
				
				if(tempA > tempB)
				{this.b=tempA;
				 this.tempA = tempB;
				 this.tempB=this.b;}
				
				this.a-=tempA; //adjust to 0
				this.b = tempB - tempA;
				 
				if(this.b == 0){
					this.a = this.tempB;
					return this;}
				
				this.a = this.a - (this.b * Math.floor(this.a/this.b)) +tempA;//
				
				return this;
				
				/*double boundBetween(double val, double lowerBound, double upperBound){
					   if(lowerBound > upperBound){std::swap(lowerBound, upperBound);}
					   val-=lowerBound; //adjust to 0
					   double rangeSize = upperBound - lowerBound;
					   if(rangeSize == 0){return upperBound;} //avoid dividing by 0
					   return val - (rangeSize * std::floor(val/rangeSize)) + lowerBound;
					}*/
				
				 
				
			}
			
			
			return null;
		case 'E':
			return this.iExpression.evaluate(this); 
			
		case 'V':
			//returnComplex.a=a;
			//returnComplex.b=b;
			return this;
			
		case 'Z':
			a=MainActivity.rj.currentZa;
			b=MainActivity.rj.currentZb;
			return this;
			
		case 'A':
			a=MainActivity.rj.currentZa;
			b=0;
			return this;	
			
			
		case 'B':
			a=MainActivity.rj.currentZb;
			b=0;
			return this;

		case 'X':
			a=MainActivity.rj.currentX;
			b=0;
			return this;	
			
	
			
		case 'Y':
			a=MainActivity.rj.currentY;
			b=0;
			return this;


		case 'C':
			a= MainActivity.rj.currentX;
			b= MainActivity.rj.currentY;
			return this;
			
		case 'N': //Current LOOP
			a= MainActivity.rj.currentIts;
			b= 0;
			return this;
			
		case 'M': //Current LOOP
			a= MainActivity.rj.currentInfinity;
			b= 0;
			return this;
			
		case 'P': //Current LOOP
			a= MainActivity.rj.currentMaxIts;
			b= 0;
			return this;
			
		case 'I': //INFINITY
			a= MainActivity.rj.infinity;
			b= 0;
			return this;
			
		case 'T': //INFINITY
			a= MainActivity.rj.its;
			b= 0;
			return this;
			
		case 'R': //INFINITY
			a= MainActivity.rj.currentDist;
			b= 0;
			return this;
			
		case 'S': //Scale
			a= Math.sqrt(Math.pow(MainActivity.rj.x2-MainActivity.rj.x1, 2) + Math.pow(MainActivity.rj.y2-MainActivity.rj.y1,2));
			
			b= 0;
			return this;
				
			
			
		}
		
		
		return this;
		
		
	}


	@Override
	public MathOperand evaluate(MathOperand result, MathOperand leftOperand,
			MathOperand rightOperand) {
		// TODO Auto-generated method stub
		return null;
	}

		
	
}
