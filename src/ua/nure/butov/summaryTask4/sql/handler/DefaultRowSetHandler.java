package ua.nure.butov.summaryTask4.sql.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.annotation.Column;
import ua.nure.butov.summaryTask4.annotation.IgnorableOnDefaultSetHandler;

/**
 * Processes one row from Result Set.
 * 
 * @author Viacheslav Butov
 *
 * @param <T>
 *            any application model
 */
class DefaultRowSetHandler<T> implements ResultSetHandler<T> {
	private static final Logger LOGGER = Logger.getLogger(DefaultRowSetHandler.class);
	/**
	 * Class of the entity that we want to have.
	 */
	private final Class<T> classEntity;

	/**
	 * @param classEntity
	 *            class of the entity that we want to have.
	 */
	DefaultRowSetHandler(final Class<T> classEntity) {
		this.classEntity = classEntity;
		LOGGER.debug("DefaultRowSetHandler object created");
	}

	@Override
	public final T handle(final ResultSet rs) throws SQLException {
		try {
			T entity = classEntity.newInstance();
			LOGGER.debug("Instance of " + entity.getClass().getName()
					+ " class has been created");
			Field[] fields = classEntity.getDeclaredFields();
			for (Field field : fields) {
				LOGGER.debug("Current field is " + field.getName());
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				if (field.getAnnotation(IgnorableOnDefaultSetHandler.class) != null) {
					continue;
				}
				field.setAccessible(true);
				String columnLabel = field.getName();
				LOGGER.debug("Column name is '" + columnLabel + "'");
				Column columnAnnotation = field.getAnnotation(Column.class);
				if (columnAnnotation != null) {
					columnLabel = columnAnnotation.value();
					LOGGER.debug("Read new colum name from annotation '"
								+ columnLabel + "'");
				}
				Object value = rs.getObject(columnLabel);
				if (value != null) {
					LOGGER.debug("Setting value " + value.toString() + " into instance");
					field.set(entity, value);
				}
			}
			return entity;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

}
