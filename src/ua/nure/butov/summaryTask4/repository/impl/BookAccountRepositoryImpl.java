package ua.nure.butov.summaryTask4.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.model.BookAccount;
import ua.nure.butov.summaryTask4.repository.BookAccountRepository;
import ua.nure.butov.summaryTask4.sql.handler.DefaultResultSetHandler;
import ua.nure.butov.summaryTask4.sql.handler.IdFounderRowSetHandler;

/**
 * Implementation for SQL database solution.
 * 
 * @author V.Butov
 *
 */
public class BookAccountRepositoryImpl extends AbstractRepositoryImpl implements BookAccountRepository {

	@Override
	public final long countUnprocessedOrders() {
		Connection connection = getCurrentConnection();
		try {
			return new QueryRunner().query(connection,"SELECT COUNT(*) AS count FROM book_account "
					+ "WHERE return_time IS NULL", new ScalarHandler<>(1));
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<BookAccount> findAll() {
		Connection connection = getCurrentConnection();
		try {
			List<BookAccount> result = (List<BookAccount>) new QueryRunner().query(connection,
							  "SELECT book_account.*, account.first_name as account_first_name,"
							+ "account.last_name as account_last_name, book.name as book_name"
							+ " FROM book_account,account,book WHERE book_account.id_account=account.id"
							+ " AND book_account.id_book=book.id ORDER BY return_time is NULL DESC, return_time",
					new DefaultResultSetHandler(BookAccount.class, true));
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<BookAccount> findAllDebtors() {
		Connection connection = getCurrentConnection();
		try {
			List<BookAccount> result = (List<BookAccount>) new QueryRunner().query(connection,
							  "SELECT book_account.*, account.first_name as account_first_name,"
							+ "account.last_name as account_last_name, book.name as book_name"
							+ " FROM book_account,account,book WHERE book_account.id_account=account.id"
							+ " AND book_account.id_book=book.id AND return_time < now()",
					new DefaultResultSetHandler(BookAccount.class, true));
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void addFine(final long bookAccountId, final double fine) {
		Object[] args = { fine, bookAccountId };
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection, "UPDATE book_account SET fine=? WHERE id=?", args);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}

	}

	@Override
	public final BookAccount findById(final long orderId) {
		Connection connection = getCurrentConnection();
		try {
			BookAccount result = (BookAccount) new QueryRunner().query(connection,
							  "SELECT book_account.*, account.first_name as account_first_name,"
							+ "account.last_name as account_last_name, book.name as book_name "
							+ " FROM book_account,account,book WHERE book_account.id_account=account.id"
							+ " AND book_account.id_book=book.id AND book_account.id=?",
					new DefaultResultSetHandler(BookAccount.class, false), orderId);
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void processNewOrder(final long orderId,
			final String borrowType, final Timestamp returnTime) {
		Object[] args = { borrowType, returnTime, orderId };
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection,
					"UPDATE book_account SET borrow_type=?, borrow_time=now(),"
				  + "return_time=? WHERE id=?", args);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void deleteOrder(final long idOrder) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection,
					"DELETE FROM book_account WHERE id = ?", idOrder);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void createNewOrder(final long idAccount, final long idBook) {
		Object[] args = { idAccount, idBook };
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().insert(connection,
					"INSERT INTO book_account (id_account,id_book) VALUES (?,?)",
					new IdFounderRowSetHandler(), args);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<BookAccount> findAllForAccount(final long idAccount) {
		Connection connection = getCurrentConnection();
		try {
			List<BookAccount> result = (List<BookAccount>) new QueryRunner().query(connection,
					"SELECT book_account.*, account.first_name as account_first_name,"
				  + "account.last_name as account_last_name, book.name as book_name"
				  + " FROM book_account,account,book WHERE book_account.id_account=account.id"
				  + " AND book_account.id_book=book.id AND book_account.id_account = ? ORDER BY return_time",
					new DefaultResultSetHandler(BookAccount.class, true), idAccount);
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

}
