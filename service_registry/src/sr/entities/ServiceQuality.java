package sr.entities;

public class ServiceQuality {
	
	private float load;

	public ServiceQuality() {
	}

	public ServiceQuality(float load) {
		super();
		this.load = load;
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
		ServiceQuality other = (ServiceQuality) obj;
		if (Float.floatToIntBits(load) != Float.floatToIntBits(other.load))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceQuality [load=" + load + "]";
	}
	
}
