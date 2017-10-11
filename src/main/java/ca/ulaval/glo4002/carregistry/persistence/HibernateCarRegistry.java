package ca.ulaval.glo4002.carregistry.persistence;

import java.security.acl.Owner;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Query;

import org.hibernate.SessionFactory;

import ca.ulaval.glo4002.carregistry.domain.CarOwner;

public class HibernateCarRegistry {

	private static final int MAX_ID = 9999;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private static Random idGenerator = new Random();

	private static Map<Integer, CarOwner> owners = new HashMap<>();

	private static SessionFactory factory;
	protected EntityManager em;

	public HibernateCarRegistry(EntityManager em) {
		this.em = em;
	}

	public CarOwner findOwner(int ownerId) {
		return (CarOwner) em.find(Owner.class, ownerId);
	}

	public void insert(CarOwner owner) {
		em.persist(owner);

		/*
		 * Session session = factory.openSession();
		 * Transaction tx = null;
		 * Integer employeeID = null;
		 * 
		 * Integer ownerId = null;
		 * 
		 * try {
		 * tx = session.beginTransaction();
		 * ownerId = (Integer) session.save(owner);
		 * tx.commit();
		 * } catch (HibernateException e) {
		 * if (tx != null)
		 * tx.rollback();
		 * e.printStackTrace();
		 * } finally {
		 * session.close();
		 * }
		 */
	}

	public void update(CarOwner owner) {

		/*
		 * Session session = factory.openSession();
		 * Transaction tx = null;
		 * 
		 * try {
		 * tx = session.beginTransaction();
		 * session.update(owner);
		 * int id = idGenerator.nextInt(MAX_ID);
		 * owner.setId(id);
		 * session.update(owner);
		 * tx.commit();
		 * } catch (HibernateException e) {
		 * if (tx != null)
		 * tx.rollback();
		 * e.printStackTrace();
		 * } finally {
		 * session.close();
		 * }
		 */
	}

	public Collection<CarOwner> findAllOwners() {

		Query query = em.createQuery("SELECT e FROM Professor e");
		return (Collection<CarOwner>) query.getResultList();

		/*
		 * Session session = factory.openSession();
		 * Transaction tx = null;
		 * 
		 * try {
		 * tx = session.beginTransaction();
		 * List carOwners = session.createQuery("FROM CarOwners").list();
		 * for (Iterator iterator = carOwners.iterator(); iterator.hasNext();) {
		 * CarOwner owner = (CarOwner) iterator.next();
		 * System.out.print("First Name: " + owner.getName());
		 * }
		 * tx.commit();
		 * return carOwners;
		 * } catch (HibernateException e) {
		 * if (tx != null)
		 * tx.rollback();
		 * e.printStackTrace();
		 * } finally {
		 * session.close();
		 * }
		 * return null;
		 */

	}

}
