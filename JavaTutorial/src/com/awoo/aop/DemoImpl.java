package com.awoo.aop;

public class DemoImpl implements Demo
{
	public String doSomething()
	{
		System.out.println("Really doing something...");
		return "result";
	}
}
