package com.jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpa_entities.ActionLog;
import jpa_entities.User;


@Stateless
public class LogDAO {
	private final static String UNIT_NAME = "jsfproject-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(ActionLog actionlog) {
		em.persist(actionlog);
	}

	public ActionLog merge(ActionLog actionlog) {
		return em.merge(actionlog);
	}

	public void remove(ActionLog actionlog) {
		em.remove(em.merge(actionlog));
	}

	public ActionLog find(Object id) {
		return em.find(ActionLog.class, id);
	}
	
	public List<ActionLog> getAllLogs(){
		return em.createNamedQuery("ActionLog.findAll").getResultList();
	}
	
	public List<ActionLog> getLazyLogs(int first, int pageSize){
		Query query = em.createNamedQuery("ActionLog.findAll");
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public int countLogs() {
		Query query = em.createNamedQuery("ActionLog.findAll");
		return query.getResultList().size();
	}




}
