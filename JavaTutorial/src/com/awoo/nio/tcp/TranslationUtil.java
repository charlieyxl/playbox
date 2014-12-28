package com.awoo.nio.tcp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.google.common.base.Function;

public class TranslationUtil
{
	public static Function<ByteBuffer, String> bytesToString = new Function<ByteBuffer, String>()
	{
		public String apply(ByteBuffer buffer)
		{
			return Charset.forName("UTF-8").decode(buffer).toString();
		}
	};

	public static Function<ByteBuffer, Object> bytesToObject = new Function<ByteBuffer, Object>()
	{
		public Object apply(ByteBuffer buffer)
		{
			// read object, or we can use Gson to read object from json String
			Object result = null;
			ByteArrayInputStream input = null;
			ObjectInputStream objInput = null;
			try
			{
				input = new ByteArrayInputStream(buffer.array());
				objInput = new ObjectInputStream(input);
				result = objInput.readObject();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if (objInput != null) objInput.close();
					if (input != null) input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

			}
			return result;
		}
	};
}
