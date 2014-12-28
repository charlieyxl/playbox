package com.awoo.nio.tcp;

import java.nio.channels.SelectionKey;

public class DataBucketWorker implements Runnable
{
	private Task task;

	public DataBucketWorker(Task task)
	{
		this.task = task;
	}

	public void run()
	{
		SelectionKey key = task.getKey();
		DataBucket databucket = (DataBucket) task.getMessage();
		int id = databucket.getId();
		String name = databucket.getName();

		key.attach(id + " / " + name);
		key.interestOps(SelectionKey.OP_WRITE);
		task.getServer().wakeupSelector();

		System.out.println(databucket.toString());
		System.out.println("处理完毕...");
	}
}
