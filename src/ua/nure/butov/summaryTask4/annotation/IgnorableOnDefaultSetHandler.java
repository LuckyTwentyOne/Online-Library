package ua.nure.butov.summaryTask4.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows to skip fields using DefaultResultSetHandler.
 * @see ua.nure.butov.summaryTask4.sql.handler.DefaultResultSetHandler
 * 
 * @author V. Butov
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IgnorableOnDefaultSetHandler {
}
