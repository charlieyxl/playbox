package com.awoo.reflection;

import java.util.List;

import com.awoo.anotation.ClassDocAnotation;

@ClassDocAnotation(author = "Charlie", date = "06/07/2014", currentRevision = 1, lastModified = "06/07/2014", lastModifiedBy = "Charlie", reviewers = { "Jue" })
public class DummyClass<T> extends ParentDummyClass implements Dummy
{
	public enum TYPE
	{
		A("A"), B("B"), C("C");

		private String type;

		TYPE(String type)
		{
			this.type = type;
		}

		public String getType()
		{
			return type;
		}

		public static TYPE getType(String type)
		{
			for (TYPE _type : TYPE.values())
			{
				_type.getType().equalsIgnoreCase(type);
				return _type;
			}
			return null;
		}
	}

	public String label;
	private String name;
	private Integer id;
	private char[][] array;
	private List<Integer> list;
	private T type;

	public final int two = 2;
	public static final int ONE = 1;

	public DummyClass(String name)
	{
		this.name = name;
	}

	public DummyClass()
	{

	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public T getType()
	{
		return type;
	}

	public void setType(T type)
	{
		this.type = type;
	}

	public char[][] getArray()
	{
		return array;
	}

	public List<Integer> getList()
	{
		return list;
	}

	public void setList(List<Integer> list)
	{
		this.list = list;
	}

	public void setArray(char[][] array)
	{
		this.array = array;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String hello()
	{
		return "Hello DummyClass";
	}

	public String getInnerHello()
	{
		return innerHello();
	}

	private String innerHello()
	{
		return "Hello inside DummyClass";
	}

	public static String staticHello()
	{
		return "Hello from static method";
	}

	public static class MemberClass
	{
		public String helloFromMember()
		{
			return "Hello from member class";
		}
	}
}
