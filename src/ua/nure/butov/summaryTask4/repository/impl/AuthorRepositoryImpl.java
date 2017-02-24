package ua.nure.butov.summaryTask4.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.form.AuthorForm;
import ua.nure.butov.summaryTask4.model.Author;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.model.Id;
import ua.nure.butov.summaryTask4.repository.AuthorRepository;
import ua.nure.butov.summaryTask4.sql.handler.DefaultResultSetHandler;
import ua.nure.butov.summaryTask4.sql.handler.IdFounderRowSetHandler;

/**
 * Implementation for SQL database solution.
 * 
 * @author V.Butov
 *
 */
public class AuthorRepositoryImpl extends AbstractRepositoryImpl implements AuthorRepository{

	@SuppressWarnings("unchecked")
	@Override
	public final List<Author> findBookAuthors(final Book book) {
		Connection connection = getCurrentConnection();
		try {
			return (List<Author>) new QueryRunner().query(connection,
					"SELECT author.* FROM author,book_author WHERE "
				  + "author.id = book_author.id_author and book_author.id_book = ?",
					new DefaultResultSetHandler(Author.class, true), book.getId());
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Author> findAll() {
		Connection connection = getCurrentConnection();
		try {
			return (List<Author>) new QueryRunner().query(connection,
					"SELECT * FROM author ORDER BY last_name",
					new DefaultResultSetHandler(Author.class, true));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Author> findAll(final int limit, final int offset) {
		Connection connection = getCurrentConnection();
		try {
			List<Author> result = (List<Author>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS * FROM author ORDER BY last_name "
				  + "LIMIT " + limit + " OFFSET "	+ offset,
					new DefaultResultSetHandler(Author.class, true));
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final Id createAuthor(final AuthorForm form) {
		Connection connection = getCurrentConnection();
		try {
			Id id = (Id) new QueryRunner().insert(connection,
							"INSERT INTO author (first_name, last_name, alternative_last_name)"
						 + " VALUES (?,?,?)",
							new IdFounderRowSetHandler(), getInsertArguments(form));
			return id;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			throw new SqlApplicationException(e);
		}
	}


}
