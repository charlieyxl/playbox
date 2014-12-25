package com.awoo.nio.tcp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyServer implements Runnable
{
	private Executor executor = Executors.newFixedThreadPool(5);

	// 要监听的端口号
	private int port;
	// TODO Add other selectors for workers in order to increase throughput
	// 生成一个信号监视器
	private Selector selector;
	// 读缓冲区
	private ByteBuffer r_buffer = ByteBuffer.allocate(1024);

	public MyServer(int port)
	{
		this.port = port;
		try
		{
			selector = Selector.open();
			initServer();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void initServer() throws IOException
	{
		// 生成一个ServerScoket通道的实例对象，用于侦听可能发生的IO事件
		ServerSocketChannel ssc = ServerSocketChannel.open();
		// 将该通道设置为异步方式
		ssc.configureBlocking(false);
		// 绑定到一个指定的端口
		ssc.socket().bind(new InetSocketAddress(port));
		// 注册特定类型的事件到信号监视器上
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("The server has been launched...");
	}

	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				// 将会阻塞执行，直到有事件发生
				int events = selector.select(2000);

				if (events == 0) continue;

				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext())
				{
					SelectionKey key = it.next();
					// key定义了四种不同形式的操作
					switch (key.readyOps())
					{
						case SelectionKey.OP_ACCEPT:
							dealAccept(key);
							break;
						case SelectionKey.OP_CONNECT:
							break;
						case SelectionKey.OP_READ:
							dealRead(key);
							break;
						case SelectionKey.OP_WRITE:
							dealWrite(key);
							break;
					}
					// 处理结束后移除当前事件，以免重复处理
					it.remove();
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// 处理接收连接的事件
	private void dealAccept(SelectionKey key)
	{
		try
		{
			System.out.println("新的客户端请求连接...");
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel sc = server.accept();
			sc.configureBlocking(false);
			// 注册读事件
			sc.register(selector, SelectionKey.OP_READ);
			System.out.println("客户端连接成功...");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// 处理客户端发来的消息，处理读事件
	private void dealRead(SelectionKey key)
	{
		try
		{
			SocketChannel sc = (SocketChannel) key.channel();
			System.out.println("读入数据");
			r_buffer.clear();

			int bytes = sc.read(r_buffer);
			if (bytes == -1)
			{
				sc.close();
				key.cancel();
				System.out.println("连接被中断");
				return;
			}

			r_buffer.flip();
			String msg = Charset.forName("UTF-8").decode(r_buffer).toString();

			// read object, or we can use Gson to read object from json String
			// ByteArrayInputStream input = new
			// ByteArrayInputStream(buffer.array());
			// ObjectInputStream objInput = new ObjectInputStream(input);
			// DataBucket databucket = (DataBucket) objInput.readObject();

			System.out.println("加入处理线程池中");
			executor.execute(new Worker(new Task(msg, key, this)));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void dealWrite(SelectionKey key)
	{
		SocketChannel sc = (SocketChannel) key.channel();
		System.out.println("写入数据");
		String result = (String) key.attachment();

		if (result != null)
		{
			try
			{
				sc.write(ByteBuffer.wrap(result.getBytes("UTF-8")));
				key.interestOps(SelectionKey.OP_READ);
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void wakeupSelector()
	{
		// 由于注册了新的感兴趣事件，需要唤醒已经在阻塞监听的selector
		selector.wakeup();
	}

	public static void main(String[] args)
	{
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new MyServer(1988));
	}
}
