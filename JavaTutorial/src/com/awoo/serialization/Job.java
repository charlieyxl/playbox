package com.awoo.serialization;

import java.io.Serializable;

public class Job implements Serializable
{
	private static final long serialVersionUID = -8443012378251957117L;
	private String company;
	private int salary;

	public Job(String company, int salary)
	{
		this.company = company;
		this.salary = salary;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public int getSalary()
	{
		return salary;
	}

	public void setSalary(int salary)
	{
		this.salary = salary;
	}

	@Override
	public String toString()
	{
		return "[" + company + ", " + salary + "]";
	}
}
