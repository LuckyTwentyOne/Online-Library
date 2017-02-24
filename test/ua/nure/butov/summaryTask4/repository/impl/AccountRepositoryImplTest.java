package ua.nure.butov.summaryTask4.repository.impl;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.model.Account;

public class AccountRepositoryImplTest {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet rs;
	private ParameterMetaData pmd;
	private AccountRepositoryImpl accountRepositoryImpl;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void before() throws SQLException{
		connection = Mockito.mock(Connection.class);
		preparedStatement = Mockito.mock(PreparedStatement.class);
		Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
		rs = Mockito.mock(ResultSet.class);
		Mockito.when(preparedStatement.executeQuery()).thenReturn(rs);
		pmd = Mockito.mock(ParameterMetaData.class);
		Mockito.when(preparedStatement.getParameterMetaData()).thenReturn(pmd);
		
		
		accountRepositoryImpl = new AccountRepositoryImpl() {
			@Override
			protected Connection getCurrentConnection() {
				return connection;
			}
		};
		
	}
	
	@Test
	public void findByLoginSuccess() throws SQLException{
		Mockito.when(pmd.getParameterCount()).thenReturn(1);
		Mockito.when(rs.next()).thenReturn(false);
		Account account = accountRepositoryImpl.findByLogin("login");
		
		Mockito.verify(connection).prepareStatement("SELECT account.*, role.name as role_name FROM account,role "
				  + "WHERE account.id_role = role.id AND account.login = ?");
		Mockito.verify(preparedStatement).setObject(1, "login");
		Mockito.verify(rs, Mockito.times(1)).next();
		Assert.assertNull(account);
		
		Mockito.verify(rs).close();
		Mockito.verify(preparedStatement).close();
		Mockito.verify(connection, Mockito.never()).close();
	}
	
	@Test
	public void findByLoginFailed() throws SQLException{
		SQLException ex = new SQLException("test");
		Mockito.when(connection.prepareStatement(Mockito.anyString())).thenThrow(ex);
		
		thrown.expect(SqlApplicationException.class);
		thrown.expectCause(Is.isA(SQLException.class));

		accountRepositoryImpl.findByLogin("login");
	}
}
