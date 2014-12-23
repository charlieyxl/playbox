package com.awoo.aop;

public class DemoImpl implements Demo
{
	@Override
	public String doSomething()
	{
		System.out.println("Really doing something...");
		return "result";
	}
}
