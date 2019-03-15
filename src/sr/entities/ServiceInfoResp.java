package sr.entities;

public class ServiceInfoResp {
	
	private String name;
	private long serviceId;
	private String ip;
	private String version;
	private long ttl;
	
	public ServiceInfoResp() {
	}

	public ServiceInfoResp(String name, long serviceId, String ip,
			String version, long ttl) {
		super();
		this.name = name;
		this.serviceId = serviceId;
		this.ip = ip;
		this.version = version;
		this.ttl = ttl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getTtl() {
		return ttl;
	}

	public void setTtl(long ttl) {
		this.ttl = ttl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (serviceId ^ (serviceId >>> 32));
		result = prime * result + (int) (ttl ^ (ttl >>> 32));
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		ServiceInfoResp other = (ServiceInfoResp) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (serviceId != other.serviceId)
			return false;
		if (ttl != other.ttl)
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceInfoResp [name=" + name + ", serviceId=" + serviceId
				+ ", ip=" + ip + ", version=" + version + ", ttl=" + ttl + "]";
	}
	
}
