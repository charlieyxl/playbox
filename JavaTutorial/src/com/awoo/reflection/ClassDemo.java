package com.awoo.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.awoo.reflection.DummyClass.MemberClass;

public class ClassDemo
{
	static Object anonymousClass = new Object()
	{
		@SuppressWarnings("unused")
		public void m()
		{
		}
	};

	private static void printAncestor(Class<?> c, List<Class<?>> l)
	{
		Class<?> ancestor = c.getSuperclass();
		if (ancestor != null)
		{
			l.add(ancestor);
			printAncestor(ancestor, l);
		}
	}

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException
	{
		// Retrieving Class Objects
		DummyClass<String> dummy = new DummyClass<String>();
		System.out.println("DummyClass: " + dummy.getClass());
		System.out.println("DummyClass: " + DummyClass.class);
		System.out.println("DummyClass: "
				+ Class.forName("com.awoo.reflection.DummyClass"));

		// Instantiate new DummyClass by Class.newInstance(), it is equivalent
		// to new keyword, and requires a public default constructor
		DummyClass<?> dummy2 = (DummyClass<?>) Class.forName(
				"com.awoo.reflection.DummyClass").newInstance();
		System.out
				.println("Hello from the instance created by Class.newInstance(): "
						+ dummy2.hello());

		System.out.println("int: " + int.class);
		System.out.println("Integer.TYPE: " + Integer.TYPE);

		byte[] bytes = new byte[1024];
		System.out.println("byte array: " + bytes.getClass());

		Set<String> set = new HashSet<String>();
		System.out.println("HashSet: " + set.getClass());

		Class<?> parent = DummyClass.class.getSuperclass();
		System.out.println("Parent: " + parent);

		Class<?>[] classes = DummyClass.class.getClasses();
		// Class<?>[] c = Character.class.getClasses();
		for (int i = 0; i < classes.length; i++)
		{
			System.out.println(i + " getClasses element: "
					+ classes[i].getName());
		}

		// Get all classes declared in DummyClass
		Class<?>[] declaredClasses = DummyClass.class.getDeclaredClasses();
		// c = Character.class.getClasses();
		for (int i = 0; i < declaredClasses.length; i++)
		{
			System.out.println(i + " getDeclaredClasses element: "
					+ declaredClasses[i].getName());
		}

		// Get the outer class that declaring this inner class
		Class<?> delaringClass = MemberClass.class.getDeclaringClass();
		System.out.println("getDeclaringClasses element of MemberClass: "
				+ delaringClass.getName());

		// Anonymous class only has enclosing class, no declaring class
		System.out.println("Anonymous class reflection:\n\tEnclosingClass - "
				+ anonymousClass.getClass().getEnclosingClass()
				+ "\n\tDeclaringClass - "
				+ anonymousClass.getClass().getDeclaringClass()
				+ "\n\tDeclaredClasses size - "
				+ anonymousClass.getClass().getDeclaredClasses().length);

		// Examining Class modifiers and types
		// Get modifiers
		Class<?> dummy3 = Class
				.forName("com.awoo.reflection.DummyClass$MemberClass");
		System.out.format("Modifiers: %d/%s%n", dummy3.getModifiers(),
				Modifier.toString(dummy3.getModifiers()));

		// Get type parameters
		TypeVariable<?>[] tv = dummy.getClass().getTypeParameters();
		dummy.setType("String");
		System.out.println("dummy type: " + dummy.getType());
		for (TypeVariable<?> t : tv)
			System.out.format("  %s/%s%n", t.getName(), t.getTypeName());

		// Get interfaces
		Type[] intfs = dummy.getClass().getGenericInterfaces();
		System.out.println("Interfaces:");
		for (Type intf : intfs)
			System.out.format("  %s%n", intf.toString());

		// Recursively get all super classes
		List<Class<?>> l = new ArrayList<Class<?>>();
		printAncestor(dummy.getClass(), l);
		System.out.println("Ancestors:");
		for (Class<?> cl : l)
			System.out.format("  %s%n", cl.getCanonicalName());

		// Can get RetentionPolicy.RUNTIME annotations only
		Annotation[] ann = dummy.getClass().getAnnotations();
		System.out.println("Anotations:");
		for (Annotation a : ann)
			System.out.format("  %s%n", a.toString());

		// Discovering Class Members
		// declaredField is for all private, protected and public field
		Field field = dummy.getClass().getDeclaredField("name");
		System.out.format("Field name: %s, field's class: %s%n",
				field.getName(), field.getDeclaringClass().getName());

		// getField is for public field only
		field = dummy.getClass().getField("label");
		System.out
				.format("Field name: %s, field's class: %s%n", field
						.toGenericString(), field.getDeclaringClass()
						.toGenericString());

		// getDeclaredFields is for all public and private fields
		Field[] fields = dummy.getClass().getDeclaredFields();
		System.out.format("Dummy class Fields size: %d%n", fields.length);
		for (Field f : fields)
		{
			System.out
					.format("  Field element: %s, Type: %s, Generic Type: %s, Modifier: %s%n",
							f.getName(), f.getType(), f.getGenericType(),
							Modifier.toString(f.getModifiers()));
		}

		// Enum class fields
		// Compilation generated ENUM$VALUES, DummyClass$MemberClass would be
		// synthetic
		Class<?> type = Class.forName("com.awoo.reflection.DummyClass$TYPE");
		Field[] enumFields = type.getDeclaredFields();
		System.out.format("%d Enum fields:%n", enumFields.length);
		for (Field f : enumFields)
		{
			System.out.format(
					"	Field name: %s, synthetic=%b, enum_constant=%b%n",
					f.getName(), f.isSynthetic(), f.isEnumConstant());
		}

		// getDeclaredMethod is for all private, protected and public method
		Method method = dummy.getClass().getDeclaredMethod("innerHello");
		System.out.format("Method innerHello(): %s%n", method.getName());

		// getMethod is for public method only
		method = dummy.getClass().getMethod("setName", String.class);
		System.out.format("Method setName(): %s%n", method.toGenericString());

		// Field, method, constructor all implements Member interface
		Member member = dummy.getClass().getConstructor(String.class);
		System.out.format("Constructor: %s%n", member.getName());

		try
		{
			Constructor c = dummy.getClass().getConstructor(String.class);
			DummyClass obj = (DummyClass) c.newInstance("test");
			System.out
					.format("Create a DummyClass via reflected construnctor with param %s%n",
							obj.getName());
		}
		catch (IllegalArgumentException e1)
		{
			e1.printStackTrace();
		}
		catch (InvocationTargetException e1)
		{
			e1.printStackTrace();
		}

		// Get package
		Package p = dummy.getClass().getPackage();
		System.out.format("Package of current class: %s%n", p.getName());

		// Get and set fields
		Field name = dummy.getClass().getDeclaredField("name");
		try
		{
			name.set(dummy, "Tutorial");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		// non-public field can be set in reflection
		name.setAccessible(true);
		System.out.format("Name before set: %s, %s%n", name.get(dummy),
				dummy.getName());
		name.set(dummy, "Tutorial");
		System.out.format("Name after set: %s, %s%n", name.get(dummy),
				dummy.getName());

		// When using reflection to set or get a field, the compiler does not
		// have an opportunity to perform boxing.
		Field id = dummy.getClass().getDeclaredField("id");
		id.setAccessible(true);
		try
		{
			id.setInt(dummy, 1);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.format("Id before set: %d, %d%n", id.get(dummy),
				dummy.getId());
		id.set(dummy, new Integer(1));
		System.out.format("Id after set: %d, %d%n", id.get(dummy),
				dummy.getId());
		System.out.println("int converts to Integer in reflection: "
				+ Integer.class.isAssignableFrom(int.class));

		Field two = dummy.getClass().getDeclaredField("two");
		// Set accessible can make final field to be set, but only in this
		// object (heap)
		two.setAccessible(true);
		System.out
				.format("Tow before set: %d, %d%n", two.get(dummy), dummy.two);
		two.setInt(dummy, 3);
		System.out.format("Tow after set: %d, %d%n", two.get(dummy), dummy.two);

		System.out.println("DummyClass converts to ParentDummyClass: "
				+ ParentDummyClass.class.isAssignableFrom(DummyClass.class));
	}
}
