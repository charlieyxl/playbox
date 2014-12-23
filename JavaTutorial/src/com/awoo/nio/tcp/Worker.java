package com.awoo.nio.tcp;

import java.io.IOException;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable
{
	private Queue<Task> tasks = new ConcurrentLinkedQueue<Task>();

	public void addTasks(Task task)
	{
		synchronized (tasks)
		{
			tasks.add(task);
			tasks.notify();
		}
	}

	// TODO put the workers into a threadpool
	@Override
	public void run()
	{
		while (true)
		{
			synchronized (tasks)
			{
				while (tasks.peek() == null)
				{
					try
					{
						tasks.wait();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
			Task task = tasks.poll();

			String msg = task.getMessage();
			String result = null;

			if (msg.equalsIgnoreCase("time"))
			{
				result = new Date().toString();
			}
			else if (msg.equalsIgnoreCase("bye"))
			{
				try
				{
					task.getKey().channel().close();
					task.getKey().cancel();
					System.out.println("客户端请求断开链接");
					continue;
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
			task.getServer().prepareResponse(task.getKey(), result);
			System.out.println(msg);
			System.out.println("处理完毕...");
		}
	}
}
