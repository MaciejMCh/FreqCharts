package com.example.freqcharts;

import android.util.Log;
import TransferFunctionLogic.ComplexConstant;
import TransferFunctionLogic.ComplexDiff;
import TransferFunctionLogic.ComplexDiv;
import TransferFunctionLogic.ComplexES;
import TransferFunctionLogic.ComplexFunction;
import TransferFunctionLogic.ComplexMult;
import TransferFunctionLogic.ComplexRound;
import TransferFunctionLogic.ComplexSum;

public class ComplexFunctionParser {
	public static ComplexFunction parseFunction(String aFunc){
		
		if(aFunc.startsWith("es")){
			String arg = aFunc.substring(3, aFunc.indexOf(")"));
			return new ComplexES(Float.parseFloat(arg));
		}else if(aFunc.startsWith("const")){
			String arg = aFunc.substring(6, aFunc.indexOf(")"));
			return new ComplexConstant(Float.parseFloat(arg));
		}else if(aFunc.startsWith("round")){
			String arg = aFunc.substring(6, aFunc.length()-1);
			ComplexRound res = new ComplexRound();
			if(!arg.startsWith("null"))res.setLHS(ComplexFunctionParser.parseFunction(arg));
			return res;
		}else if(aFunc.startsWith("sum")){
			String arg = aFunc.substring(4, aFunc.length()-1);
			int inx = arg.indexOf("(")+1;
			int depth = 1;			
			while(depth!=0){
				if(arg.charAt(inx)=='(') depth++;
				if(arg.charAt(inx)==')') depth--;
				inx++;				
			}
			String arg1 = arg.substring(0,inx);
			String arg2 = arg.substring(inx+1,arg.length());
			ComplexSum res = new ComplexSum();
			if(!arg1.startsWith("null"))res.setLHS(ComplexFunctionParser.parseFunction(arg1));
			if(!arg2.startsWith("null"))res.setRHS(ComplexFunctionParser.parseFunction(arg2));
			return res;
		}else if(aFunc.startsWith("diff")){
			String arg = aFunc.substring(5, aFunc.length()-1);
			int inx = arg.indexOf("(")+1;
			int depth = 1;			
			while(depth!=0){
				if(arg.charAt(inx)=='(') depth++;
				if(arg.charAt(inx)==')') depth--;
				inx++;				
			}
			String arg1 = arg.substring(0,inx);
			String arg2 = arg.substring(inx+1,arg.length());
			ComplexDiff res = new ComplexDiff();
			if(!arg1.startsWith("null"))res.setLHS(ComplexFunctionParser.parseFunction(arg1));
			if(!arg2.startsWith("null"))res.setRHS(ComplexFunctionParser.parseFunction(arg2));
			return res;
		}else if(aFunc.startsWith("mult")){
			String arg = aFunc.substring(5, aFunc.length()-1);
			int inx = arg.indexOf("(")+1;
			int depth = 1;			
			while(depth!=0){
				if(arg.charAt(inx)=='(') depth++;
				if(arg.charAt(inx)==')') depth--;
				inx++;				
			}
			String arg1 = arg.substring(0,inx);
			String arg2 = arg.substring(inx+1,arg.length());
			ComplexMult res = new ComplexMult();
			if(!arg1.startsWith("null"))res.setLHS(ComplexFunctionParser.parseFunction(arg1));
			if(!arg2.startsWith("null"))res.setRHS(ComplexFunctionParser.parseFunction(arg2));
			return res;
		}else if(aFunc.startsWith("div")){
			String arg = aFunc.substring(4, aFunc.length()-1);
			int inx = arg.indexOf("(")+1;
			int depth = 1;			
			while(depth!=0){
				if(arg.charAt(inx)=='(') depth++;
				if(arg.charAt(inx)==')') depth--;
				inx++;				
			}
			String arg1 = arg.substring(0,inx);
			String arg2 = arg.substring(inx+1,arg.length());
			ComplexDiv res = new ComplexDiv();
			if(!arg1.startsWith("null"))res.setLHS(ComplexFunctionParser.parseFunction(arg1));
			if(!arg2.startsWith("null"))res.setRHS(ComplexFunctionParser.parseFunction(arg2));
			return res;
		}
		
		
		return new ComplexConstant(666);	
	}
}
