package com.awoo.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ClassDocAnotation
{
	String author();

	String date();

	int currentRevision() default 1;

	String lastModified() default "N/A";

	String lastModifiedBy() default "N/A";

	// Note use of array
	String[] reviewers();
}
