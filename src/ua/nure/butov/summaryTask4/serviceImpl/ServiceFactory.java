package ua.nure.butov.summaryTask4.serviceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import ua.nure.butov.summaryTask4.annotation.Transactional;
import ua.nure.butov.summaryTask4.exception.SqlApplicationException;
import ua.nure.butov.summaryTask4.service.Service;
import ua.nure.butov.summaryTask4.util.ConnectionUtils;

/**
 * Wraps real services into proxy instances.
 * 
 * @author V.Butov
 *
 */
public class ServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T extends Service> T createService(DataSource dataSource, T realService) {
		return (T) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), realService.getClass().getInterfaces(),
				new ServiceInvocationHandler<T>(realService, dataSource));
	}

	private static class ServiceInvocationHandler<T> implements InvocationHandler {
		private final T realService;
		private final DataSource dataSource;

		ServiceInvocationHandler(T realService, DataSource dataSource) {
			this.realService = realService;
			this.dataSource = dataSource;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Method m = findMethod(method);
			Transactional transactional = m.getAnnotation(Transactional.class);
			try {
				if (transactional != null) {
					return invokeTransactional(transactional, m, args);
				} else {
					return m.invoke(realService, args);
				}
			} catch (Exception e) {
				if(e instanceof InvocationTargetException){
					throw ((InvocationTargetException)e).getTargetException();
				} else {
					throw e;
				}
			}
		}

		/**
		 * Adds extra functionality to each real service's method marked as {@link Transactional}.
		 * 
		 * @param method method that are executing
		 * @param args method's arguments
		 * @throws IllegalAccessException
		 * @throws IllegalArgumentException
		 * @throws InvocationTargetException
		 */
		private Object invokeTransactional(Transactional transactional, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			try (Connection connection = dataSource.getConnection()) {
				connection.setAutoCommit(false);
				ConnectionUtils.setCurrentConnection(connection);
				if(transactional.readOnly()){
					return method.invoke(realService, args);
				}else{
					try{
						Object result = method.invoke(realService, args);
						connection.commit();
						return result;
					}catch(Exception e){
						if(e instanceof RuntimeException){
							connection.rollback();
						}//else {
						//	connection.commit();
						//}
						throw e;
					}
				}
				
			} catch (SQLException e) {
				throw new SqlApplicationException(e);
			} finally {
				ConnectionUtils.removeCurrentConnection();
			}
		}

		private Method findMethod(Method method) {
			for (Method m : realService.getClass().getDeclaredMethods()) {
				if (m.getName().equals(method.getName()) &&
						Arrays.equals(m.getParameterTypes(), method.getParameterTypes())) {
					return m;
				}
			}
			throw new IllegalArgumentException("Can't fint method " + method + " in the " + realService.getClass());
		}

	}
}
