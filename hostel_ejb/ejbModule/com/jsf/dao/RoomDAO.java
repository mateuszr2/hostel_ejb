package com.jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpa_entities.Room;


@Stateless
public class RoomDAO {
	private final static String UNIT_NAME = "jsfproject-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Room room) {
		em.persist(room);
	}

	public Room merge(Room room) {
		return em.merge(room);
	}

	public void remove(Room room) {
		em.remove(em.merge(room));
	}

	public Room find(Object id) {
		return em.find(Room.class, id);
	}

	public List<Room> getFullList() {
		List<Room> list = null;

		Query query = em.createQuery("select p from Rooms p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Room> getList(Map<String, Object> searchParams) {
		List<Room> list = null;

	
		String select = "select p ";
		String from = "from Room p ";
		String where = "";
		String orderby = "order by p.roomNo asc";

		
		String name = (String) searchParams.get("type");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.type like :type ";
		}
		
	
	
	
		Query query = em.createQuery(select + from + where + orderby);

		
		if (name != null) {
			query.setParameter("type", name+"%");
		}

		

	
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
