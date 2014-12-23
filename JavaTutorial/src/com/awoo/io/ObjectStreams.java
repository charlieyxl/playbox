package com.awoo.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class ObjectStreams
{
	static final String dataFile = "invoicedata";

	static final BigDecimal[] prices = { new BigDecimal("19.99"),
			new BigDecimal("9.99"), new BigDecimal("15.99"),
			new BigDecimal("3.99"), new BigDecimal("4.99") };
	static final int[] units = { 12, 8, 13, 29, 50 };
	static final String[] descs = { "Java T-shirt", "Java Mug",
			"Duke Juggling Dolls", "Java Pin", "Java Key Chain" };

	public static void mainExample() throws ClassNotFoundException, IOException
	{
		ObjectOutputStream out = null;
		try
		{
			out = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(dataFile)));

			out.writeObject(Calendar.getInstance());
			for (int i = 0; i < prices.length; i++)
			{
				out.writeObject(prices[i]);
				out.writeInt(units[i]);
				out.writeUTF(descs[i]);
			}
		}
		finally
		{
			out.close();
		}

		ObjectInputStream in = null;
		try
		{
			in = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(dataFile)));

			Calendar date = null;
			BigDecimal price;
			int unit;
			String desc;
			BigDecimal total = new BigDecimal(0);

			date = (Calendar) in.readObject();

			System.out.format("On %tA, %<tB %<te, %<tY:%n", date);

			try
			{
				while (true)
				{
					price = (BigDecimal) in.readObject();
					unit = in.readInt();
					desc = in.readUTF();
					System.out.format("You ordered %d units of %s at $%.2f%n",
							unit, desc, price);
					total = total.add(price.multiply(new BigDecimal(unit)));
				}
			}
			catch (EOFException e)
			{
			}
			System.out.format("For a TOTAL of: $%.2f%n", total);
		}
		finally
		{
			in.close();
		}
	}

	public static void writeSingleOjectTwiceIntoOneStream() throws IOException
	{
		ObjectOutputStream out = null;

		try
		{
			out = new ObjectOutputStream(new FileOutputStream(dataFile));
			Test obj1 = new Test(1);
			Test obj2 = new Test(2);
			out.writeObject(obj1);
			out.writeObject(obj1);
			out.writeObject(obj2);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			out.close();
		}

		ObjectInputStream in = null;

		try
		{
			in = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(dataFile)));

			Test obj1 = (Test) in.readObject();
			Test obj2 = (Test) in.readObject();
			Test obj3 = (Test) in.readObject();

			System.out.println("obj1: " + obj1.id);
			System.out.println("obj2: " + obj2.id);
			System.out.println("obj3: " + obj3.id);
			System.out.println("obj1 is same as obj2: " + obj1.equals(obj2));
			System.out.println("obj1 is same as obj3: " + obj1.equals(obj3));
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			in.close();
		}
	}

	static class Test implements Serializable
	{
		private static final long serialVersionUID = -903895304889510258L;
		int id;

		public Test(int id)
		{
			this.id = id;
		}
	}

	public static void main(String[] args) throws IOException,
			ClassNotFoundException
	{
		// mainExample();
		writeSingleOjectTwiceIntoOneStream();
	}
}