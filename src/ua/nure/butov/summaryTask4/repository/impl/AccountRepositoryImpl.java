package ua.nure.butov.summaryTask4.repository.impl;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.form.AddAccountForm;
import ua.nure.butov.summaryTask4.form.EditAccountForm;
import ua.nure.butov.summaryTask4.form.RegistrationForm;
import ua.nure.butov.summaryTask4.model.Account;
import ua.nure.butov.summaryTask4.model.Id;
import ua.nure.butov.summaryTask4.model.SocialAccount;
import ua.nure.butov.summaryTask4.repository.AccountRepository;
import ua.nure.butov.summaryTask4.sql.handler.DefaultResultSetHandler;
import ua.nure.butov.summaryTask4.sql.handler.IdFounderRowSetHandler;

/**
 * Implementation for SQL database solution.
 * 
 * @author Viacheslav Butov
 *
 */
public class AccountRepositoryImpl extends AbstractRepositoryImpl
										implements AccountRepository {

	@Override
	public final Account findByLogin(final String login) {
		LOGGER.debug("Trying to find Account by login '" + login + "' in the database");
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			Account result = (Account) new QueryRunner().query(connection,
					"SELECT account.*, role.name as role_name FROM account,role "
				  + "WHERE account.id_role = role.id AND account.login = ?",
					new DefaultResultSetHandler(Account.class, false), login);
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Account> findAll(final int limit, final int offset) {
		LOGGER.debug("Trying to find all accounts in the database");
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			List<Account> result = (List<Account>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS account.*, role.name as role_name FROM account,role "
				  + "WHERE account.id_role = role.id ORDER BY updated desc"
				  + " LIMIT " + limit + " OFFSET " + offset,
					new DefaultResultSetHandler(Account.class, true));
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final Account findByEmail(final String email) {
		LOGGER.debug("Trying to find Account by email '" + email + "' in the database");
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			Account result = (Account) new QueryRunner().query(connection,
					"SELECT account.*, role.name as role_name" + " FROM account,role"
							+ " WHERE account.id_role = role.id AND account.email = ?",
					new DefaultResultSetHandler(Account.class, false), email);
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final Account register(final RegistrationForm form) {
		Connection connection = getCurrentConnection();
		try {
			Id id = (Id) new QueryRunner().insert(connection,
					"INSERT INTO account (login, email, first_name, last_name, password) VALUES (?,?,?,?,?)",
					new IdFounderRowSetHandler(), getInsertArguments(form));
			Account result = new Account();
			result.setId(id.getId());
			return result;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final Account findById(final long id) {
		LOGGER.debug("Trying to find Account by id '" + id + "' in the database");
		Connection connection = getCurrentConnection();
		LOGGER.debug("Connection established");
		try {
			Account result = (Account) new QueryRunner().query(connection,
					"SELECT account.*, role.name as role_name" + " FROM account,role"
							+ " WHERE account.id_role = role.id AND account.id = ?",
					new DefaultResultSetHandler(Account.class, false), id);
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void updateAccount(final EditAccountForm form) {
		Object[] args = { form.getFirstName(), form.getLastName(), form.getRole(),
						form.isActive(), form.getId() };
		Connection c = getCurrentConnection();
		try {
			new QueryRunner().update(c,
					"UPDATE account SET first_name =?, last_name=?, "
				  + "id_role =?, active =? WHERE id=?", args);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final Id addAccount(final AddAccountForm form) {
		Connection connection = getCurrentConnection();
		try {
			Id id = (Id) new QueryRunner()
					.insert(connection,
							"INSERT INTO account (login, email, first_name, last_name,"
							+ " password, id_role, active) VALUES (?,?,?,?,?,?,?)",
							new IdFounderRowSetHandler(), getInsertArguments(form));
			return id;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			throw new SqlApplicationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Account> searchAccountsByLogin(final String login,
										final int limit, final int offset) {
		Connection connection = getCurrentConnection();
		try {
			List<Account> result = (List<Account>) new QueryRunner().query(connection,
					"SELECT SQL_CALC_FOUND_ROWS account.*, role.name as role_name FROM account,role "
				  + "WHERE account.id_role = role.id AND account.login LIKE CONCAT('%','" + login + "','%') "
				  + "ORDER BY updated desc LIMIT " + limit + " OFFSET " + offset,
					new DefaultResultSetHandler(Account.class, true));
			return result;
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void deleteAccount(final long id) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection,
					"DELETE FROM account WHERE id = ?", id);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void activateAccount(final long idAccount) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection,
					"UPDATE account SET active=true WHERE id=?", idAccount);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final Account registerBySocialAccount(final SocialAccount socialAccount) {
		Connection connection = getCurrentConnection();
		try {
			Id id = (Id) new QueryRunner().insert(connection,
					"INSERT INTO account (first_name, last_name, email, password, id_role, active) "
				  + "VALUES (?,?,?,'facebook',3,1)",
					new IdFounderRowSetHandler(), getInsertArguments(socialAccount));
			Account result = (Account) new QueryRunner().query(connection,
					"SELECT account.*, role.name as role_name" + " FROM account,role "
				  + "WHERE account.id_role = role.id AND account.id = ?",
					new DefaultResultSetHandler(Account.class, false), id.getId());
			return result;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			throw new SqlApplicationException(e);
		}
	}

}
