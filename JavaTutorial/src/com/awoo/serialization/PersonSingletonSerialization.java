package com.awoo.serialization;

import java.io.ObjectStreamException;
import java.io.Serializable;

import com.awoo.serialization.PersonNormalSerialization.Gender;

/**
 * Make sure serialization do not break the singleton rule
 * 
 * @author charlie
 */
public class PersonSingletonSerialization implements Serializable
{
	private static final long serialVersionUID = -1782240018431339775L;

	private static PersonSingletonSerialization instance;

	private String name;
	// transient field would not be serialized by default, so its value would be
	// lost after de-serialization and always be default in value 0
	private transient int age;
	private Gender gender;

	public static PersonSingletonSerialization getInstance()
	{
		if (instance == null)
		{
			synchronized (PersonSingletonSerialization.class)
			{
				if (instance == null)
				{
					instance = new PersonSingletonSerialization("Charlie", 26,
							Gender.MALE);
				}
			}
		}
		return instance;
	}

	private PersonSingletonSerialization()
	{
		System.out.println("PersonSingletonSerialization None-arg constructor");
	}

	private PersonSingletonSerialization(String name, int age, Gender gender)
	{
		System.out
				.println("PersonSingletonSerialization constructor with args");
		this.name = name;
		this.age = age;
		this.gender = gender;
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

	@Override
	public String toString()
	{
		return "[" + name + ", " + age + ", " + gender + "]";
	}

	/**
	 * The object returned would be used to replace the instance that is created
	 * during de-serialization
	 * 
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException
	{
		System.out.println("Executing readResolve()");
		return instance;
	}
}
