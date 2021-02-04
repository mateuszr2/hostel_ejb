package com.jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpa_entities.Role;


@Stateless
public class RoleDAO {
	private final static String UNIT_NAME = "jsfproject-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Role role) {
		em.persist(role);
	}

	public Role merge(Role role) {
		return em.merge(role);
	}

	public void remove(Role role) {
		em.remove(em.merge(role));
	}

	public Role find(Object id) {
		return em.find(Role.class, id);
	}

	public List<Role> getFullList() {
		List<Role> list = null;

		Query query = em.createQuery("select r from Role r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


		
	
	
	


	

}
