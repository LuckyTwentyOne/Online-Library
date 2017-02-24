package ua.nure.butov.summaryTask4.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.annotation.IgnorableOnInput;
import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.sql.handler.RecordCountFouderHandler;
import ua.nure.butov.summaryTask4.util.ConnectionUtils;

/**
 * Provides a base functionality for all repositories.
 * 
 * @author V.Butov
 *
 */
public abstract class AbstractRepositoryImpl {
	
	protected final Logger LOGGER = Logger.getLogger(getClass());
	
	protected Connection getCurrentConnection(){
		return ConnectionUtils.getCurrentConnection();
	}

	/**
	 * Parses all fields of javaBean object into Object[] for further
	 * inserting them into database.
	 * <p>
	 * An order of fields in the parsed object is strict.
	 * 
	 * @param obj could be {@link ua.nure.butov.summaryTask4.form} or javaBean obj
	 * @return array of fields with their values 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public Object[] getInsertArguments(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Field fields[] = obj.getClass().getDeclaredFields();
		List<Object> resolvedArgs = new ArrayList<>();
		for (Field field : fields) {
			IgnorableOnInput ignorable = field.getAnnotation(IgnorableOnInput.class);
			if (ignorable != null || Modifier.isStatic(field.getModifiers())) {
				continue;
			} else {
				field.setAccessible(true);
				resolvedArgs.add(field.get(obj));
			}
		}
		return resolvedArgs.toArray();
	}

	/**
	 * Common query to find found records.
	 * <p>
	 * Calls after SELECT query <b>only</b>, with signature 
	 * like {@code SELECT SQL_CALC_FOUND_ROWS.....}
	 * @return
	 */
	public int countFoundedRecords() {
		Connection connection = getCurrentConnection();
		try {
			return new QueryRunner().query(connection, "SELECT FOUND_ROWS()", new RecordCountFouderHandler());
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}
}
