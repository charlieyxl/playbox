package com.awoo.nio.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ObjectTest implements Runnable
{
	private SocketChannel sc;
	private ByteBuffer w_bBuf;
	private ByteBuffer r_bBuf = ByteBuffer.allocate(1024);

	public ObjectTest(String host, int port) throws IOException
	{
		InetSocketAddress remote = new InetSocketAddress(host, port);
		sc = SocketChannel.open();
		sc.connect(remote);
		if (sc.finishConnect())
		{
			System.out.println("已经与服务器成功建立连接...");
		}
	}

	public void run()
	{
		try
		{
			System.out.println("读入一行数据，开始发送...");

			DataBucket data = new DataBucket();
			data.setId(10);
			data.setName("awoo");

			// Write object into BtyeArray stream
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(data);
			oos.flush();

			w_bBuf = ByteBuffer.wrap(bos.toByteArray());

			// 将缓冲区中数据写入通道
			sc.write(w_bBuf);
			oos.close();
			bos.close();
			System.out.println("数据发送成功...");
			System.out.println("接收服务器端响应消息...");

			r_bBuf.clear();
			// 将字节序列从此通道中读入给定的缓冲区r_bBuf
			int bytes = sc.read(r_bBuf);
			if (bytes == -1)
			{
				sc.close();
				System.out.println("与服务器断开链接");
			}

			r_bBuf.flip();
			String msg = Charset.forName("UTF-8").decode(r_bBuf).toString();
			System.out.println(msg);

			sc.close();
			System.out.println("关闭程序中...");
		}
		catch (IOException e)
		{
			System.out.println("IO exception occurred");
		}
	}

	public static void main(String[] args) throws IOException
	{
		new Thread(new ObjectTest("localhost", 1988)).start();
	}
}
