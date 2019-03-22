package sr.dao;

import java.util.List;

import sr.entities.RegisterInfo;

public interface RegisterInfoDao {

	RegisterInfo register(RegisterInfo ri);
	
	void unregister(long riId);
	
	RegisterInfo updateRegisterInfo(RegisterInfo ri);
	
	RegisterInfo findServiceById(long id);
	
	List<RegisterInfo> findServicesByNameVersion(String name, String version);
	
	List<RegisterInfo> listAllServices();
	
}
