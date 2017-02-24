package ua.nure.butov.summaryTask4.repository.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.form.EditBookForm;
import ua.nure.butov.summaryTask4.model.Book;
import ua.nure.butov.summaryTask4.model.Id;
import ua.nure.butov.summaryTask4.repository.BookRepository;
import ua.nure.butov.summaryTask4.sql.handler.DefaultResultSetHandler;
import ua.nure.butov.summaryTask4.sql.handler.IdFounderRowSetHandler;

/**
 * Implementation for SQL database solution.
 * 
 * @author V.Butov
 *
 */
public class BookRepositoryImpl extends AbstractRepositoryImpl implements BookRepository {

	@SuppressWarnings("unchecked")
	@Override
	public final List<Book> findAll(final int limit, final int offset) {
		LOGGER.debug("Trying to find all books in the database");
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			List<Book> result = (List<Book>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY created desc"
				  + " LIMIT " + limit + " OFFSET " + offset,
					new DefaultResultSetHandler(Book.class, true));
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final Book findById(final long id) {
		Connection connection = getCurrentConnection();
		try {
			Book result = (Book) new QueryRunner().query(connection,
					"SELECT * FROM book WHERE id = ?",
					new DefaultResultSetHandler(Book.class, false), id);
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void updateBook(final Book book) {
		Object[] args = { book.getName(), book.getPublisher(),
				book.getImprintYear(), book.getAlternativeName(), book.getId() };
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection,
					"UPDATE book SET name = ?, publisher = ?, imprint_year = ?,"
					+ " alternative_name = ? WHERE id = ?",
					args);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void deleteBook(final long bookId) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection,
					"DELETE FROM book WHERE id = ?", bookId);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final Id createBook(final EditBookForm form) {
		Connection connection = getCurrentConnection();
		try {
			Id id = (Id) new QueryRunner().insert(connection,
					"INSERT INTO book (name, publisher, imprint_year, alternative_name)"
				  + " VALUES (?,?,?,?)",
					new IdFounderRowSetHandler(), getInsertArguments(form));
			return id;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> searchBooksByName(String bookName, int limit, int offset) {
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			return (List<Book>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS * FROM book WHERE" + " name LIKE CONCAT('%','" + bookName + "','%') OR "
							+ "alternative_name LIKE CONCAT('%','" + bookName + "','%') " + "ORDER BY name " + "LIMIT "
							+ limit + " OFFSET " + offset,
					new DefaultResultSetHandler(Book.class, true));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> searchBooksByAuthor(String authorSurname, int limit, int offset) {
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			return (List<Book>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS book.* FROM book,author,book_author"
							+ " WHERE book_author.id_book = book.id AND book_author.id_author=author.id"
							+ " AND (author.last_name LIKE CONCAT('%','" + authorSurname + "','%') OR"
							+ " author.alternative_last_name LIKE CONCAT('%','" + authorSurname + "','%')) "
							+ "ORDER BY author.last_name " + "LIMIT " + limit + " OFFSET " + offset,
					new DefaultResultSetHandler(Book.class, true));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> searchAuthorBooks(long idAuthor) {
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			return (List<Book>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS book.* FROM book,author,book_author"
							+ " WHERE book_author.id_book = book.id AND book_author.id_author=author.id"
							+ " AND author.id=" +idAuthor,
					new DefaultResultSetHandler(Book.class, true));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> sortByName(int limit, int offset) {
		LOGGER.debug("Trying to find all books in the database");
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			return (List<Book>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY name " + "LIMIT " + limit + " OFFSET " + offset,
					new DefaultResultSetHandler(Book.class, true));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> sortByAuthor(int limit, int offset) {
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			return (List<Book>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS book.* FROM book,author,book_author"
							+ " WHERE book_author.id_book = book.id AND book_author.id_author=author.id"
							+ " ORDER BY author.last_name LIMIT " + limit + " OFFSET " + offset,
					new DefaultResultSetHandler(Book.class, true));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> sortByPublisher(int limit, int offset) {
		LOGGER.debug("Trying to find all books in the database");
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			return (List<Book>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY publisher "
						+ "LIMIT " + limit + " OFFSET " + offset,
					new DefaultResultSetHandler(Book.class, true));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> sortByYear(int limit, int offset) {
		LOGGER.debug("Trying to find all books in the database");
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			return (List<Book>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS * FROM book ORDER BY imprint_year desc "
						+ "LIMIT " + limit + " OFFSET " + offset,
					new DefaultResultSetHandler(Book.class, true));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public void updateBookTotalOrdersCount(long idBook) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection,
					"UPDATE book SET total_orders = total_orders+1 WHERE id = ?",
					idBook);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}
	
	public int getTotalOrdersNumber() {
		Connection connection = getCurrentConnection();
		try {
			return new QueryRunner().query(connection,"SELECT SUM(total_orders) FROM book",
					new ScalarHandler<>(1));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}
	
	public void updatePercent() {
		Connection connection = getCurrentConnection();
		try {
			BigDecimal total = new QueryRunner().query(connection,"SELECT SUM(total_orders) FROM book",
					new ScalarHandler<>(1));
			new QueryRunner().update(connection,"UPDATE book SET percent =(total_orders /"+total+")*100");
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}
}
