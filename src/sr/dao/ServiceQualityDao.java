package sr.dao;

import sr.entities.ServiceQuality;

public interface ServiceQualityDao {

	ServiceQuality createServiceQuality(ServiceQuality sq);
	
	void deleteServiceQuality(long sqId);
	
	ServiceQuality updateServiceQuality(ServiceQuality sq);
	
	ServiceQuality findServiceQualityById(long id);
}
