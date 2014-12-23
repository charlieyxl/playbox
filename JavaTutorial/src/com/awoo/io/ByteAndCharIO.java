package com.awoo.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ByteAndCharIO
{
	/**
	 * Read and write the data from file per byte/byte[]
	 * 
	 * @throws IOException
	 */
	public static void byteStreamIO() throws IOException
	{
		InputStream iStream = null;
		OutputStream oStream = null;

		try
		{
			iStream = ClassLoader.getSystemResourceAsStream("xanadu.txt");
			oStream = new FileOutputStream("resources/byteOutput.txt");
			byte[] buffer = new byte[1024];
			while (iStream.read(buffer) > 0)
			{
				oStream.write(buffer);
			}
		}
		finally
		{
			if (iStream != null)
			{
				iStream.close();
			}
			if (oStream != null)
			{
				oStream.close();
			}
		}
	}

	/**
	 * Buffered read and write the data from file via bytes
	 * 
	 * @throws IOException
	 */
	public static void bufferedByteStreamIO() throws IOException
	{
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try
		{
			bis = new BufferedInputStream(
					ClassLoader.getSystemResourceAsStream("xanadu.txt"));
			bos = new BufferedOutputStream(new FileOutputStream(
					"resources/bufferedByteOutput.txt"));

			int bits;

			while ((bits = bis.read()) > 0)
			{
				bos.write(bits);
			}
		}
		finally
		{
			if (bis != null)
			{
				bis.close();
			}
			if (bos != null)
			{
				bos.close();
			}
		}
	}

	/**
	 * Buffered read and write the data from file via chars
	 * 
	 * @throws IOException
	 */
	public static void bufferedCharStreamIO() throws IOException
	{
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;

		try
		{
			inputStream = new BufferedReader(new FileReader(
					"resources/xanadu.txt"));
			outputStream = new PrintWriter(new FileWriter(
					"resources/bufferedCharoutput.txt"));

			String l;
			while ((l = inputStream.readLine()) != null)
			{
				outputStream.println(l);
			}
		}
		finally
		{
			if (inputStream != null)
			{
				inputStream.close();
			}
			if (outputStream != null)
			{
				outputStream.close();
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		byteStreamIO();
		bufferedByteStreamIO();
		bufferedCharStreamIO();
	}
}
