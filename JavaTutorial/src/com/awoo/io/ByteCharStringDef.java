package com.awoo.io;

import java.util.Locale;

public class ByteCharStringDef
{
	public static void main(String[] args)
	{
		// 直接指定单个字符作为字符常量
		char aChar = 'a';
		System.out.println(aChar);
		byte b = 'b';
		System.out.println(b);
		// 使用转义字符作为字符常量.
		char enterChar = '\r';
		System.out.println(enterChar);
		byte enterByte = '\r';
		System.out.println(enterByte);
		// 使用Unicode编码值来作为字符常量
		char ch = '\u9999';
		System.out.println(ch);
		// 定义一个中字符常量
		char zhong = '中';
		System.out.println(zhong);
		// 直接将一个char变量当成int变量类型使用
		int zhongValue = zhong;
		System.out.println(zhongValue);
		// 直接将一个0--65535的int型整数赋值给一个char变量
		char c = 99;
		System.out.println(c);
		String zhongStr = "中";
		System.out.println(zhongStr + " / bytes:" + zhongStr.getBytes().length);

		System.out.println(Locale.getDefault());
	}
}