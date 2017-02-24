package ua.nure.butov.summaryTask4.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows to skip class field while parsing by method 
 * {@link ua.nure.butov.summaryTask4.repository.impl.AbstractRepositoryImpl#getInsertArguments(Object)}.
 * 
 * @author Viacheslav Butov
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IgnorableOnInput {

}
