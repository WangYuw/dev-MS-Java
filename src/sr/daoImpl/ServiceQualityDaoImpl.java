package sr.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import sr.dao.ServiceQualityDao;
import sr.entities.ServiceQuality;

public class ServiceQualityDaoImpl implements ServiceQualityDao {
	
	private final EntityManager em;
	
	public ServiceQualityDaoImpl(EntityManager em) {
        this.em = em;
    }

	@Override
	public ServiceQuality createServiceQuality(ServiceQuality sq) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(sq);
		
		tx.commit();
        em.clear();
        System.out.println("Record Quality Successfully Inserted In The Database");
		return sq;
	}

	@Override
	public void deleteServiceQuality(long sqId) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		ServiceQuality sq = em.find(ServiceQuality.class, sqId);
		if (sq != null) {
			em.remove(sq);
			System.out.println("Record Quality Successfully Deleted From The Database");
		}else 
			System.out.println("Error Unregister.");
		
		tx.commit();
        em.clear();
	}

	@Override
	public ServiceQuality updateServiceQuality(ServiceQuality sq) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.merge(sq);
		
		tx.commit();
        em.clear();
        System.out.println("Record Quality Successfully Updated In The Database");
		return sq;
	}

	@Override
	public ServiceQuality findServiceQualityById(long id) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		ServiceQuality result = em.find(ServiceQuality.class, id);
		
		tx.commit();
        em.clear();
        System.out.println("Find Service Quality By Id...");
        return result;
	}

}
