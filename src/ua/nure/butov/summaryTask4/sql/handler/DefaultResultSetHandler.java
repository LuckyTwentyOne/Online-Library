package ua.nure.butov.summaryTask4.sql.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;

/**
 * Convert ResultSets into application entities.
 *
 */
public class DefaultResultSetHandler implements ResultSetHandler<Object> {
	private static final Logger LOGGER = Logger.getLogger(DefaultResultSetHandler.class);
	private DefaultRowSetHandler<?> rowHandler;
	private boolean isCollection;

	public DefaultResultSetHandler(final Class<?> classEntity, boolean isCollection) {
		rowHandler = new DefaultRowSetHandler<>(classEntity);
		this.isCollection = isCollection;
		LOGGER.debug("DefaultResultSetHandler created to convert"
				+ " result set into " + classEntity.getName()
				+ " object.");
	}

	@Override
	public Object handle(final ResultSet rs) throws SQLException {
		if (isCollection) {
			LOGGER.debug("Result set is collection of objects");
			List<Object> list = new ArrayList<Object>();
			while (rs.next()) {
				list.add(rowHandler.handle(rs));
			}
			return list;
		} else {
			if (rs.next()) {
				LOGGER.debug("Trying to convert single object");
				return rowHandler.handle(rs);
			} else {
				LOGGER.debug("There is no rows into result set");
				return null;
			}
		}

	}
}
