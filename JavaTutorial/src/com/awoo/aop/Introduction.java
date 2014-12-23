package com.awoo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Introduction
{
	public static void main(String[] args)
	{
		Demo demo = new DemoImpl();
		InvocationHandler handler = new MyInvocationHandler(demo);
		Demo demoProxy = (Demo) Proxy.newProxyInstance(demo.getClass()
				.getClassLoader(), demo.getClass().getInterfaces(), handler);
		String result = demoProxy.doSomething();
		System.out.format("Result is: %s", result);
	}
}
