package ua.nure.butov.summaryTask4.task;

import org.apache.log4j.Logger;

import ua.nure.butov.summaryTask4.serviceImpl.ServiceManager;

public class CalculateFinesTask implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(CalculateFinesTask.class);
	private ServiceManager serviceManager;

	public CalculateFinesTask(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@Override
	public void run() {
		LOGGER.info("Calculating fines for orders");
        serviceManager.getLibrarianService().countFineForAllDebtors();
        LOGGER.info("Calculating complited");
	}

}
