package ua.nure.butov.summaryTask4.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.repository.CopyRepository;
import ua.nure.butov.summaryTask4.sql.handler.IdFounderRowSetHandler;

/**
 * Implementation for SQL database solution.
 * 
 * @author V.Butov
 *
 */
public class CopyRepositoryImpl extends AbstractRepositoryImpl
											implements CopyRepository {

	@Override
	public final void addBookCopy(final int idBook) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().insert(connection,
					"INSERT INTO copy (id_book) VALUES (?)",
					new IdFounderRowSetHandler(), idBook);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final long countBookCopies(final long idBook) {
		Connection connection = getCurrentConnection();
		try {
			return new QueryRunner().query(connection,"SELECT COUNT(*)"
					+ " FROM copy WHERE id_book=" + idBook, new ScalarHandler<>(1));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final long countFreeBookCopies(final long idBook) {
		Connection connection = getCurrentConnection();
		try {
			return new QueryRunner().query(connection,"SELECT COUNT(*)"
					+ " FROM copy WHERE id_book=" + idBook + " AND is_free=1", new ScalarHandler<>(1));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void setFreeOneBookCopy(final long idBook) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection, "UPDATE copy SET is_free=true"
					+ " WHERE id_book=? AND is_free=false LIMIT 1", idBook);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void setBusyOneBookCopy(final long idBook) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection, "UPDATE copy SET is_free=false"
					+ " WHERE id_book=? AND is_free=true LIMIT 1", idBook);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}	
	}

}
