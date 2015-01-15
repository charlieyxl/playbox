package com.awoo.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersonNormalSerialization implements Serializable
{
	private static final long serialVersionUID = -3667261580007474801L;

	public enum Gender
	{
		MALE, FEMALE
	}

	private String name;
	// transient field would not be serialized by default, so its value would be
	// lost after de-serialization and always be default in value 0
	private transient int age;
	private Gender gender;
	private Job job;

	public PersonNormalSerialization()
	{
		System.out
				.println("PersonNormalSerialization Constructor without args");
	}

	public PersonNormalSerialization(String name, int age, Gender gender,
			Job job)
	{
		System.out.println("PersonNormalSerialization Constructor with args");
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.job = job;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	public Job getJob()
	{
		return job;
	}

	public void setJob(Job job)
	{
		this.job = job;
	}

	@Override
	public String toString()
	{
		return "[" + name + ", " + age + ", " + gender + ", " + job + "]";
	}

	private void writeObject(ObjectOutputStream out) throws IOException
	{
		System.out
				.println("Executing writeObject in PersonNormalSerialization");
		// execute default serialization
		out.defaultWriteObject();
		// Write age into the object OutputStream explicitly, if multiple fields
		// here, should be the same order with readObject()
		out.writeInt(age);
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException
	{
		System.out.println("Executing readObject in PersonNormalSerialization");
		// execute default de-serialization
		in.defaultReadObject();
		// Read age from the object OutputStream explicitly
		age = in.readInt();
	}
}
