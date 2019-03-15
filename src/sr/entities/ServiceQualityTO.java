package sr.entities;

public class ServiceQualityTO {
	
	private long id;
	private float load;

	public ServiceQualityTO() {
		super();
	}

	public ServiceQualityTO(long id, float load) {
		super();
		this.id = id;
		this.load = load;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getLoad() {
		return load;
	}

	public void setLoad(float load) {
		this.load = load;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Float.floatToIntBits(load);
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
		ServiceQualityTO other = (ServiceQualityTO) obj;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(load) != Float.floatToIntBits(other.load))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceQualityTO [id=" + id + ", load=" + load + "]";
	}
	
}
