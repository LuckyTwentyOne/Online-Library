package ua.nure.butov.summaryTask4.repository.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.model.AccountRegistration;
import ua.nure.butov.summaryTask4.model.EmptyModel;
import ua.nure.butov.summaryTask4.repository.AccountRegistrationRepository;
import ua.nure.butov.summaryTask4.sql.handler.DefaultResultSetHandler;

/**
 * SQL implementation of {@code AccountRegistrationRepository}.
 * 
 * @author V.Butov
 *
 */
public class AccountRegistrationRepositoryImpl extends AbstractRepositoryImpl implements AccountRegistrationRepository {

	@Override
	public final void addActivateHashToAccount(final long idAccount, final String hash) {
		Object[] args = { idAccount, hash };
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().insert(connection,
					"INSERT INTO account_registration (id_account, code) VALUES (?, ?)",
					new DefaultResultSetHandler(EmptyModel.class, false), args);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final AccountRegistration findByCode(final String code) {
		Connection connection = getCurrentConnection();
		try {
			return (AccountRegistration) new QueryRunner().query(connection,
					"SELECT * from account_registration WHERE code=?",
					new DefaultResultSetHandler(AccountRegistration.class, false), code);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

	@Override
	public final void deleteRecord(final long idAccount) {
		Connection connection = getCurrentConnection();
		try {
			new QueryRunner().update(connection,
									 "DELETE FROM account_registration WHERE id_account = ?",
									 idAccount);
		} catch (SQLException e) {
			throw new SqlApplicationException(e);
		}
	}

}
