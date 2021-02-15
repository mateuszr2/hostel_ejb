package com.jsf.dao;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jpa_entities.User;
import com.jsf.dao.RoleDAO;




import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "jsfproject-simplePU";
	public static final String ID = "$31$"; // Each token produced by this class uses this identifier as a prefix.
	public static final int DEFAULT_COST = 16; // The minimum recommended cost, used by default
	private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	private static final int SIZE = 128;
	private static final Pattern layout = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.{43})");
	private final SecureRandom random;
	private final int cost;
	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}

	public UserDAO() {
		this(DEFAULT_COST);
	}

	public UserDAO(int cost) {
		iterations(cost); /* Validate cost */
		this.cost = cost;
		this.random = new SecureRandom();
	}
	private static int iterations(int cost) {
		if ((cost < 0) || (cost > 30))
			throw new IllegalArgumentException("cost: " + cost);
		return 1 << cost;
	}

	public List<User> getList(Map<String, Object> searchParams) {
		List<User> list = null;

	
		String select = "select p ";
		String from = "from User p ";
		String where = "";
		String join = " JOIN Role r ";
		String on = "on p.role = r.idRole";
		String orderby = "order by p.name asc, p.country";

		
		String name = (String) searchParams.get("name");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.name like :name ";
		}
		
	
	
	
		Query query = em.createQuery(select + from + where + join + on);

		
		if (name != null) {
			query.setParameter("name", name+"%");
		}

		

	
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
		
	}
	@Inject
	FacesContext ctx;
	
	public boolean authenticate(char[] password, String token)
	  {
	    Matcher m = layout.matcher(token);
	    if (!m.matches())
	      throw new IllegalArgumentException("Invalid token format");
	
	    int iterations = iterations(Integer.parseInt(m.group(1)));
	    byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
	    byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
	    byte[] check = pbkdf2(password, salt, iterations);
	    int zero = 0;
	    for (int idx = 0; idx < check.length; ++idx)
	      zero |= hash[salt.length + idx] ^ check[idx];
	    return zero == 0;
	  }
	
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
		KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
			return f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
		} catch (InvalidKeySpecException ex) {
			throw new IllegalStateException("Invalid SecretKeyFactory", ex);
		}
	}

	public User getbyUsername(String login) {
		Query query = em.createQuery("from User p where p.login=:login");
		query.setParameter("login", login);
		
		return (User)query.getSingleResult();
	}
	@Deprecated
	public boolean authenticate(String password, String token) {
		return authenticate(password.toCharArray(), token);
	}
	public User getloginAccount(String login, String password) {
		try {
			String databasePass;
			List<User> users = em.createQuery("select u from User u where u.login = :login")
					.setParameter("login", login).getResultList();

			if (users.size() > 0)
				databasePass = users.get(0).getPassword();
			else return null;
			
			if(authenticate(password, databasePass)) 
				return users.get(0);
			else return null;

		}catch(javax.persistence.NoResultException e) {
			return null;
		}

	}
}
