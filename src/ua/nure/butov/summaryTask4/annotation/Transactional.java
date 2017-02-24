package ua.nure.butov.summaryTask4.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the method is connecting to database.
 * 
 * @author V. Butov
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Transactional {

	/**
     * Should be {@code false} if method must be executed within a transaction.
     *
     * @return column name
     */
	boolean readOnly() default true;
}
