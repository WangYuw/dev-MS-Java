package sr.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import sr.dao.RegisterInfoDao;
import sr.entities.RegisterInfo;

public class RegisterInfoDaoImpl implements RegisterInfoDao {
	
	private final EntityManager em;
	
	public RegisterInfoDaoImpl(EntityManager em) {
        this.em = em;
    }

	@Override
	public RegisterInfo register(RegisterInfo ri) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(ri);
		
		tx.commit();
        em.clear();
        System.out.println("Record Successfully Inserted In The Database");
		return ri;
	}

	@Override
	public void unregister(long riId) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		RegisterInfo ri = em.find(RegisterInfo.class, riId);
		if (ri != null) {
			em.remove(ri);
			System.out.println("Record Successfully Deleted From The Database");
		}else 
			System.out.println("Error Unregister.");
		
		tx.commit();
        em.clear();
	}

	@Override
	public RegisterInfo updateRegisterInfo(RegisterInfo ri) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.merge(ri);
		
		tx.commit();
        em.clear();
        System.out.println("Record Successfully Updated In The Database");
		return ri;
	}

	@Override
	public RegisterInfo findServiceById(long id) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		RegisterInfo result = em.find(RegisterInfo.class, id);
		
		tx.commit();
        em.clear();
        System.out.println("Find Service By Id...");
        return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegisterInfo> findServicesByNameVersion(String name, String version) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Query q = em.createNamedQuery("RegisterInfo.findByNameVersion");
        q.setParameter("name", name);
        q.setParameter("version", version);        
        List<RegisterInfo> results = q.getResultList();
        
        tx.commit();
        em.clear();
        System.out.println("Service Request...");
        return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegisterInfo> listAllServices() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
        Query q = em.createNamedQuery("RegisterInfo.findAll");
        List<RegisterInfo> results = q.getResultList();
        
        tx.commit();
        em.clear();
        System.out.println("List All Service...");
        return results;
	}

}
