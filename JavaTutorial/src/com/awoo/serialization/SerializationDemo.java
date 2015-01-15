package com.awoo.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.awoo.serialization.PersonNormalSerialization.Gender;

public class SerializationDemo
{
	public static void main(String[] args) throws Exception
	{
		File file = new File("resources/person.out");
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(
				file));
		Job job = new Job("Company", 10000);

		// Object person =
		// SerializationDemo.getNormalSerializationInstance(job);

		// Object person = SerializationDemo.getExternalizationInstance(job);
		Object person = PersonSingletonSerialization.getInstance();
		System.out.println(person);

		System.out.println("Start serialization");
		// Would use reflection to invoke writeObject() in Person
		oout.writeObject(person);
		oout.close();

		System.out.println("Start de-serialization");
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
		Object newPerson = oin.readObject();
		oin.close();
		System.out.println(newPerson);
		System.out.format("Same object: %b", person == newPerson);
	}

	public static PersonNormalSerialization getNormalSerializationInstance(
			Job job)
	{
		return new PersonNormalSerialization("Charlie", 26, Gender.MALE, job);
	}

	public static PersonExternalization getExternalizationInstance(Job job)
	{
		return new PersonExternalization("Charlie", 26, Gender.MALE, job);
	}
}
