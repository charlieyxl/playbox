package com.awoo.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.awoo.serialization.PersonNormalSerialization.Gender;

/**
 * In ObjectOutputStream.writeOrdinaryObject(), it would prefer to serialize an
 * object with interface Externalizable rather than Serializable. When
 * serializing with Externalizable to read an object, its non-arg constructor
 * would be used to create a new instance, then the values of its fields would
 * be stored into the new instance. So a class implements Externalizable must
 * have a public non-arg constructor.
 * 
 * @author charlie
 */
public class PersonExternalization implements Externalizable
{
	private String name;
	// transient field would not be serialized by default, so its value would be
	// lost after de-serialization and always be default in value 0
	private transient int age;
	private Gender gender;
	private Job job;

	public PersonExternalization()
	{
		System.out.println("PersonExternalization Constructor without args");
	}

	public PersonExternalization(String name, int age, Gender gender, Job job)
	{
		System.out.println("PersonExternalization Constructor with args");
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
		System.out.println("Executing writeObject in PersonExternalization");
		// execute default serialization
		out.defaultWriteObject();
		// Write age into the object OutputStream explicitly, if multiple fields
		// here, should be the same order with readObject()
		out.writeInt(age);
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException
	{
		System.out.println("Executing readObject in PersonExternalization");
		// execute default de-serialization
		in.defaultReadObject();
		// Read age from the object OutputStream explicitly
		age = in.readInt();
	}

	public void writeExternal(ObjectOutput out) throws IOException
	{
		System.out.println("Executing writeExternal in PersonExternalization");
		out.writeObject(name);
		out.writeInt(age);
		out.writeObject(gender);
		out.writeObject(job);
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException
	{
		System.out.println("Executing readExternal in PersonExternalization");
		name = (String) in.readObject();
		age = in.readInt();
		gender = (Gender) in.readObject();
		job = (Job) in.readObject();
	}
}
