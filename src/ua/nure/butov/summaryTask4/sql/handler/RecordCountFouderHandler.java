package ua.nure.butov.summaryTask4.sql.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;

/**
 * ResultSetHadler to extract count of records that were found.
 * 
 * @author Viacheslav Butov
 *
 */
public class RecordCountFouderHandler implements ResultSetHandler<Integer> {
	private static final Logger LOGGER = Logger.getLogger(RecordCountFouderHandler.class);

	@Override
	public final Integer handle(final ResultSet rs) throws SQLException {
		LOGGER.debug("RecordCountFouderHandler.handle() started");
		try {
			int result = 0;
			if (rs.next()) {
				LOGGER.debug("Result set is not empty");
				result = rs.getInt(1);
				LOGGER.debug("Found records = "+result);
			}
			LOGGER.debug("RecordCountFouderHandler.handle() successfully finished");
			return result;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

}
