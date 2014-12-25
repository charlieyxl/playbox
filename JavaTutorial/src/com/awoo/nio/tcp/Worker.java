package com.awoo.nio.tcp;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Date;

public class Worker implements Runnable
{
	private Task task;

	public Worker(Task task)
	{
		this.task = task;
	}

	@Override
	public void run()
	{
		String msg = task.getMessage();
		SelectionKey key = task.getKey();
		String result = null;

		if (msg.equalsIgnoreCase("time"))
		{
			result = new Date().toString();
		}
		else if (msg.equalsIgnoreCase("bye"))
		{
			try
			{
				key.channel().close();
				key.cancel();
				System.out.println("客户端请求断开链接");
				// 断开连接以后要返回
				return;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		if (result == null)
		{
			result = msg;
		}

		key.attach(result);
		key.interestOps(SelectionKey.OP_WRITE);
		task.getServer().wakeupSelector();

		System.out.println(msg);
		System.out.println("处理完毕...");
	}
}
