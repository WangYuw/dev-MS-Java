package sr.registry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import sr.dao.RegisterInfoDao;
import sr.dao.ServiceQualityDao;
import sr.daoImpl.RegisterInfoDaoImpl;
import sr.daoImpl.ServiceQualityDaoImpl;
import sr.entities.RegisterInfo;
import sr.entities.ServiceQuality;
import sr.transferables.RegisterInfoTO;
import sr.transferables.ServiceQualityTO;
import sr.transferables.ServiceRequest;

public class ServiceRegistry {
	
    //@PersistenceContext(unitName = "service-registry")
    private EntityManager em;
	
    private static final String PERSISTENCE_UNIT_NAME = "service-registry";
    
    public ServiceRegistry() {
    	this.em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}
    
    public RegisterInfoTO register(RegisterInfoTO rito) {
    	RegisterInfoDao dao = new RegisterInfoDaoImpl(em);
    	return dao.register(RegisterInfo.fromRegisterInfoTO(em, rito)).toRegisterInfoTO();
    }
    
    
    public void unregister(long riId) {
    	RegisterInfoDao dao = new RegisterInfoDaoImpl(em);
    	dao.unregister(riId);
    }
    
    public RegisterInfoTO updateRegisterInfo(RegisterInfoTO rito) {
    	RegisterInfoDao dao = new RegisterInfoDaoImpl(em);
    	return dao.updateRegisterInfo(RegisterInfo.fromRegisterInfoTO(em, rito)).toRegisterInfoTO();
    }
    
    public RegisterInfoTO findServiceById(long id) {
    	RegisterInfoDao dao = new RegisterInfoDaoImpl(em);
    	return dao.findServiceById(id).toRegisterInfoTO();
    }
    
    private List<RegisterInfoTO> makeRegisterInfoTOList(List<RegisterInfo> riList) {
    	List<RegisterInfoTO> toList = new ArrayList<RegisterInfoTO>();
    	for (RegisterInfo ri : riList) {
    		toList.add(ri.toRegisterInfoTO());
    	}
    	return toList;
    }
    
    public List<RegisterInfoTO> findServicesByNameVersion(ServiceRequest req) {
    	RegisterInfoDao dao = new RegisterInfoDaoImpl(em);
    	return makeRegisterInfoTOList(dao.findServicesByNameVersion(req.getName(), req.getVersion()));
    }
    
    public List<RegisterInfoTO> listAllServices(){
    	RegisterInfoDao dao = new RegisterInfoDaoImpl(em);
    	return makeRegisterInfoTOList(dao.listAllServices());
    }
    
    public ServiceQualityTO createServiceQuality(ServiceQualityTO sqto) {
    	ServiceQualityDao dao = new ServiceQualityDaoImpl(em);
    	return dao.createServiceQuality(ServiceQuality.fromServiceQualityTO(sqto)).toServiceQualityTO();
    }
    
    public void deleteServiceQuality(long sqId) {
    	ServiceQualityDao dao = new ServiceQualityDaoImpl(em);
    	dao.deleteServiceQuality(sqId);
    }
    
    public ServiceQualityTO updateServiceQuality(ServiceQualityTO sqto) {
    	ServiceQualityDao dao = new ServiceQualityDaoImpl(em);
    	return dao.updateServiceQuality(ServiceQuality.fromServiceQualityTO(sqto)).toServiceQualityTO();
    }
    
    public ServiceQualityTO findServiceQualityById(long id) {
    	ServiceQualityDao dao = new ServiceQualityDaoImpl(em);
    	return dao.findServiceQualityById(id).toServiceQualityTO();
    }
    
}
