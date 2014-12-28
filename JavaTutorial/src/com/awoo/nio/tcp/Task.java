package com.awoo.nio.tcp;

import java.nio.channels.SelectionKey;

public class Task
{
	private Object message;
	private SelectionKey key;
	private MyServer server;

	public Task(Object message, SelectionKey key, MyServer server)
	{
		this.message = message;
		this.key = key;
		this.server = server;
	}

	public Object getMessage()
	{
		return message;
	}

	public SelectionKey getKey()
	{
		return key;
	}

	public MyServer getServer()
	{
		return server;
	}
}
