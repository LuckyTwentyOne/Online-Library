package ua.nure.butov.summaryTask4.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.model.BookAuthor;
import ua.nure.butov.summaryTask4.model.Id;
import ua.nure.butov.summaryTask4.repository.BookAuthorRepository;
import ua.nure.butov.summaryTask4.sql.handler.IdFounderRowSetHandler;

/**
 * Implementation for SQL database solution.
 * 
 * @author V.Butov
 *
 */
public class BookAuthorRepositoryImpl extends AbstractRepositoryImpl
										implements BookAuthorRepository {

	@Override
	public final Id addBookAuthor(final BookAuthor bookAuthor) {
		Object[] args = { bookAuthor.getIdBook(), bookAuthor.getIdAuthor() };
		Connection connection = getCurrentConnection();
		try {
			Id id = (Id) new QueryRunner().insert(connection,
					"INSERT INTO book_author (id_book, id_author) VALUES (?,?)",
					new IdFounderRowSetHandler(), args);
			return id;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void removeAllBookAuthors(final long bookId) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection,
					"DELETE FROM book_author WHERE id_book = ?", bookId);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

}
