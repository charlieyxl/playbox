package com.awoo.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo
{
	public static void main(String[] args)
	{
		try
		{
			RandomAccessFile aFile = new RandomAccessFile(
					"resources/xanadu.txt", "rw");
			FileChannel inChannel = aFile.getChannel();

			ByteBuffer buf = ByteBuffer.allocate(48);

			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1)
			{

				System.out.println("\nRead " + bytesRead);
				buf.flip();

				while (buf.hasRemaining())
				{
					System.out.print((char) buf.get());
				}

				buf.clear();
				bytesRead = inChannel.read(buf);
			}
			aFile.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
