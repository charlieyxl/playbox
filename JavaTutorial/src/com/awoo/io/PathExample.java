package com.awoo.io;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample
{
	public static void main(String[] args) throws IOException
	{
		Path p1 = Paths.get("/home/charlie/targets");
		System.out.format("To String: %s%nURI: %s%n", p1, p1.toUri());

		Path p2 = Paths.get("resources/./xanadu.txt");
		System.out.format("Relative path: %s%nFull path: %s%n", p2,
				p2.toAbsolutePath());
		System.out.format("Real path: %s%n", p2.toRealPath());

		// However, Passing an absolute path to the resolve method returns the
		// passed-in path
		Path p3 = Paths.get("resources");
		System.out.format("Resolve resources: %s%n", p3.toAbsolutePath()
				.resolve("xanadu.txt"));

		Path p4 = Paths.get("src");
		System.out.format("p3 to p4: %s%n", p3.relativize(p4));
		System.out.println("p4 full path starts with /home: "
				+ p4.toAbsolutePath().startsWith(Paths.get("/home")));

		for (Path name : p4.toAbsolutePath())
		{
			System.out.println(name);
		}
	}
}
