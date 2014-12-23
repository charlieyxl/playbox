package com.awoo.anotation;

@ClassDocAnotation(author = "Charlie", date = "3/17/2002", currentRevision = 6, lastModified = "4/12/2004", lastModifiedBy = "Jane Doe", reviewers = {
		"Alice", "Bob", "Cindy" })
public class AnotationUsage
{
	public String sayHello()
	{
		return "hello";
	}

	public static void main(String[] args)
	{
		AnotationUsage example = new AnotationUsage();
		example.sayHello();
	}
}
