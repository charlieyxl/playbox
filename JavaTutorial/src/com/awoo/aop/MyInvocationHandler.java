package com.awoo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler
{
	private Object obj;

	public MyInvocationHandler(Object obj)
	{
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		System.out.println("Before invocation.");
		Object result = method.invoke(obj, args);
		System.out.println("After invocation.");
		return result;
	}
}
