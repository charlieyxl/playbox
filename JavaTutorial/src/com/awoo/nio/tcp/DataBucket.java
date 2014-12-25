package com.awoo.nio.tcp;

import java.io.Serializable;

public class DataBucket implements Serializable
{
	private static final long serialVersionUID = -2968091130928469475L;
	private String name;
	private int id;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
