package com.awoo.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.awoo.serialization.Person.Gender;

public class SerializationDemo
{
	public static void main(String[] args) throws Exception
	{
		File file = new File("resources/person.out");
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(
				file));
		Job job = new Job("Company", 10000);
		Person person = new Person("Charlie", 26, Gender.MALE, job);

		// Would use reflection to invoke writeObject() in Person
		oout.writeObject(person);
		oout.close();

		ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
		Object newPerson = oin.readObject();
		oin.close();
		System.out.println(newPerson);
	}
}
