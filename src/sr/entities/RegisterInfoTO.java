package sr.entities;

public class RegisterInfoTO {
	
	private long id;
	private String name;
	private String ip;
	private String version;
	private long qualityId;
	
	public RegisterInfoTO() {
		super();
	}
	
	public RegisterInfoTO(long id, String name, String ip, String version,
			long qualityId) {
		super();
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.version = version;
		this.qualityId = qualityId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
	public long getQualityId() {
		return qualityId;
	}

	public void setQualityId(long qualityId) {
		this.qualityId = qualityId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (qualityId ^ (qualityId >>> 32));
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
		RegisterInfoTO other = (RegisterInfoTO) obj;
		if (id != other.id)
			return false;
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
		if (qualityId != other.qualityId)
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
		return "RegisterInfoTO [id=" + id + ", name=" + name + ", ip=" + ip
				+ ", version=" + version + ", qualityId=" + qualityId + "]";
	}
	
}
