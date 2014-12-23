package com.awoo.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Formatting
{
	public static void main(String[] args) throws IOException
	{
		int i = 2;
		double r = Math.sqrt(i);

		System.out.format("The square root of %d is %f.%n", i, r);

		// Can also write the formatted data into a file
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(out, true);
		writer.format("%f, %1$+020.10f %n", Math.PI);
		System.out.println(out.toString());
	}
}
