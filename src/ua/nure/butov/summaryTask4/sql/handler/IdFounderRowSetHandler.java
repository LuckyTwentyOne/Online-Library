package ua.nure.butov.summaryTask4.sql.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.model.Id;

/**
 * ResultSetHadler to extract generated id of any entity into database.
 * <p>
 * Use for inserts only
 * 
 * @author Viacheslav Butov
 *
 */
public class IdFounderRowSetHandler implements ResultSetHandler<Id> {
	private static final Logger LOGGER = Logger.getLogger(IdFounderRowSetHandler.class);

	@Override
	public final Id handle(final ResultSet rs) throws SQLException {
		LOGGER.debug("IdFounderRowSetHandler.handle() started");
		try {
			Id result = new Id();
			if (rs.next()) {
				LOGGER.debug("Result set is not empty");
				Long value = rs.getLong("GENERATED_KEY");
				result.setId(value);
			}
			LOGGER.debug("IdFounderRowSetHandler.handle() successfully finished");
			return result;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

}
