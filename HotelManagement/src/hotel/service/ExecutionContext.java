package hotel.service;

import hotel.hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ExecutionContext {
	Session session;
	Transaction transaction;
	boolean rollbackOnly = false;
	public ExecutionContext() {
		this.session = HibernateUtil.currentSession();
		transaction = session.beginTransaction();
	}

	public Session getSession() {
		return session;
	}
	
	public void setRollbackOnly() {
		rollbackOnly = true;
	}
	
	public void close() {
		session = null;
		if (rollbackOnly) {
			transaction.rollback();
		} else {
			transaction.commit();
		}
		HibernateUtil.closeSession();
	}

}
