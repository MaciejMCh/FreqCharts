package Aplication;

import TransferFunctionLogic.ComplexFunction;

public class SimpleFuncProvider implements FunctionProvider {

	private ComplexFunction mFunction;
	
	public SimpleFuncProvider(ComplexFunction aFunction){
		mFunction = aFunction;
	}
	@Override
	public ComplexFunction getFunction() {
		return mFunction;
	}

}
