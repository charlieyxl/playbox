package com.awoo.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class StressTest implements Runnable
{
	public static final String[] REQUESTS = { "test", "time", "bye" };

	private SocketChannel sc;
	private ByteBuffer w_bBuf;
	private ByteBuffer r_bBuf = ByteBuffer.allocate(1024);

	public StressTest(String host, int port) throws IOException
	{
		InetSocketAddress remote = new InetSocketAddress(host, port);
		sc = SocketChannel.open();
		sc.connect(remote);
		if (sc.finishConnect())
		{
			System.out.println("已经与服务器成功建立连接...");
		}
	}

	public static void main(String[] args) throws IOException
	{
		for (int i = 0; i < 1000; i++)
		{
			new Thread(new StressTest("localhost", 1988)).start();
		}
	}

	public void run()
	{
		try
		{
			int i = 0;
			while (true)
			{
				String str = REQUESTS[i++];
				System.out.println("读入一行数据，开始发送...");

				w_bBuf = ByteBuffer.wrap(str.getBytes("UTF-8"));
				// 将缓冲区中数据写入通道
				sc.write(w_bBuf);
				System.out.println("数据发送成功...");
				System.out.println("接收服务器端响应消息...");

				r_bBuf.clear();
				// 将字节序列从此通道中读入给定的缓冲区r_bBuf
				int bytes = sc.read(r_bBuf);
				if (bytes == -1)
				{
					sc.close();
					System.out.println("与服务器断开链接");
					break;
				}

				r_bBuf.flip();
				String msg = Charset.forName("UTF-8").decode(r_bBuf).toString();
				System.out.println(msg);
			}
			System.out.println("关闭程序中...");
		}
		catch (IOException e)
		{
			System.out.println("IO exception occurred");
		}
	}
}
