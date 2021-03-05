package com.jsf.dao;


import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import jpa_entities.RoomBooking;
import jpa_entities.User;


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
	
	public List<RoomBooking> searchForDuplicate(Date checkInDate, Date checkOutDate, int idRoom) {
		Query query =  em.createQuery("SELECT b FROM RoomBooking b WHERE ( :check_in_date BETWEEN b.checkInDate AND b.checkOutDate ) OR ( :check_out_date BETWEEN b.checkInDate AND b.checkOutDate ) AND b.room.idRoom = :id_room");
		query.setParameter("check_in_date", checkInDate);
		query.setParameter("check_out_date", checkOutDate);
		query.setParameter("id_room", idRoom);
	
		return query.getResultList();
		
	}


	public List<RoomBooking> searchForDuplicate1(Date checkInDate, Date checkOutDate, int idRoom) {
		Query query =  em.createQuery("SELECT b FROM RoomBooking b WHERE b.checkInDate BETWEEN :check_in_date AND :check_out_date AND b.checkOutDate BETWEEN :check_in_date AND :check_out_date AND b.room.idRoom = :id_room");
		query.setParameter("check_in_date", checkInDate);
		query.setParameter("check_out_date", checkOutDate);
		query.setParameter("id_room", idRoom);
	
		return query.getResultList();
		
	}
	/*
	public List<RoomBooking> searchForDuplicate1(Date checkInDate, Date checkOutDate, int idRoom) {
		
		Query query1 =  em.createQuery("SELECT b FROM RoomBooking b WHERE b.checkInDate < :check_in_date AND b.checkOutDate < :check_out_date AND b.room.idRoom = :id_room");
		
		query1.setParameter("check_in_date", checkInDate);
		query1.setParameter("check_out_date", checkOutDate);
		query1.setParameter("id_room", idRoom);
		return query1.getResultList();
		
	}
	
	public List<RoomBooking> searchForDuplicate2(Date checkInDate, Date checkOutDate, int idRoom) {
		
		Query query2 =  em.createQuery("SELECT b FROM RoomBooking b WHERE b.checkInDate > :check_in_date AND b.checkOutDate > :check_out_date AND b.room.idRoom = :id_room");
		
		query2.setParameter("check_in_date", checkInDate);
		query2.setParameter("check_out_date", checkOutDate);
		query2.setParameter("id_room", idRoom);
		return query2.getResultList();
		
	}
*/	
	public List<RoomBooking> getList(Map<String, Object> searchParams) {
		List<RoomBooking> list = null;

	
		String select = "select p ";
		String from = "from RoomBooking p ";
		String where = "";
		
		
	
		
	
	
	
		Query query = em.createQuery(select + from);
		
	
		
	

		

	
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	
	
	
	
	public List<RoomBooking> getBookingUserList(User user) {
		List<RoomBooking> list = null;

		Query query6 = em.createQuery("from RoomBooking p " + "where p.user =:user_id");
		query6.setParameter("user_id", user);

		try {
			list = query6.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	
	
	
	

}
