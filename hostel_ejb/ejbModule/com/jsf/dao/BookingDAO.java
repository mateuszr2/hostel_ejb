package com.jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpa_entities.Room;
import jpa_entities.RoomBooking;


@Stateless
public class BookingDAO {
	private final static String UNIT_NAME = "jsfproject-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(RoomBooking roombooking) {
		em.persist(roombooking);
	}

	public RoomBooking merge(RoomBooking roombooking) {
		return em.merge(roombooking);
	}

	public void remove(RoomBooking roombooking) {
		em.remove(em.merge(roombooking));
	}

	public RoomBooking find(Object id) {
		return em.find(RoomBooking.class, id);
	}

	
	public List<RoomBooking> getFullList() {
		List<RoomBooking> list = null;

		Query query = em.createQuery("select p from RoomBooking p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<RoomBooking> getList(Map<String, Object> searchParams) {
		List<RoomBooking> list = null;

	
		String select = "select p ";
		String from = "from RoomBooking p ";
		String where = "";
		String join = "JOIN Room r ";
		String on = "on p.idRoom = r.idRoom";
		
	
		
	
	
	
		Query query = em.createQuery(select + from + join + on);
		
	

	

		

	
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	

}
