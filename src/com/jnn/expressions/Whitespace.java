package com.jnn.expressions;

public class Whitespace implements Token {

	@Override
	public MathOperand evaluate(MathOperand result, MathOperand leftOperand,
			MathOperand rightOperand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MathOperand evaluate(MathOperand result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBoolean() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOperand() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOperator() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWhiteSpace() {
		// TODO Auto-generated method stub
		return true;
	}

}
