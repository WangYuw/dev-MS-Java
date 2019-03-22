package sr.transferables;

public class ServiceQualityInfoResp {

	//private String name;
	private long serviceId;
	//private String ip;
	private ServiceQualityTO quality;
	
	public ServiceQualityInfoResp() {
	}

	
	public ServiceQualityInfoResp(long serviceId, ServiceQualityTO quality) {
		super();
		this.serviceId = serviceId;
		this.quality = quality;
	}


	/*public ServiceQualityInfoResp(String name, long serviceId, String ip,
			ServiceQualityTO quality) {
		super();
		this.name = name;
		this.serviceId = serviceId;
		this.ip = ip;
		this.quality = quality;
	}*/
	/*
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	/*
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}*/

	public ServiceQualityTO getQuality() {
		return quality;
	}

	public void setQuality(ServiceQualityTO quality) {
		this.quality = quality;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((quality == null) ? 0 : quality.hashCode());
		result = prime * result + (int) (serviceId ^ (serviceId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceQualityInfoResp other = (ServiceQualityInfoResp) obj;
		if (quality == null) {
			if (other.quality != null)
				return false;
		} else if (!quality.equals(other.quality))
			return false;
		if (serviceId != other.serviceId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceQualityInfoResp [serviceId=" + serviceId + ", quality=" + quality + "]";
	}
	
}
