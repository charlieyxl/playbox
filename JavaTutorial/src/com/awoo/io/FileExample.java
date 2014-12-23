package com.awoo.io;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;

public class FileExample
{
	public static void main(String[] args)
	{
		Path p3 = Paths.get("resources/xanadu.txt").toAbsolutePath();
		// Files.exists() can have option: LinkOption.NOFOLLOW_LINKS
		System.out.println("File xanadu.txt existence: " + Files.exists(p3));
		System.out.println("Readable: " + Files.isReadable(p3));
		System.out.println("Writable: " + Files.isWritable(p3));
		System.out.println("Executable: " + Files.isExecutable(p3));

		try
		{
			System.out.format("Size: %d%n", Files.size(p3));
			System.out.format("Size attribute: %d%n",
					Files.getAttribute(p3, "size"));
			System.out.println("Size attributes: "
					+ Files.readAttributes(p3, "size"));

			// Linux file attributes
			PosixFileAttributes attr = Files.readAttributes(p3,
					PosixFileAttributes.class);
			System.out.println("creationTime: " + attr.creationTime());
			System.out.println("lastAccessTime: " + attr.lastAccessTime());
			System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
			System.out.format("%s %s %s%n", attr.owner().getName(), attr
					.group().getName(), PosixFilePermissions.toString(attr
					.permissions()));

			// Set file owner
			UserPrincipal owner = p3.getFileSystem()
					.getUserPrincipalLookupService()
					.lookupPrincipalByName("charlie");
			Files.setOwner(p3, owner);

			// Get the file store, e.g. how much space is available
			FileStore store = Files.getFileStore(p3);
			long total = store.getTotalSpace() / (1024 * 1024 * 1024);
			long used = (store.getTotalSpace() - store.getUnallocatedSpace())
					/ (1024 * 1024 * 1024);
			long avail = store.getUsableSpace() / (1024 * 1024 * 1024);
			System.out.format("Total %d GB%n", total);
			System.out.format("Used %d GB%n", used);
			System.out.format("Available %d GB%n", avail);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
