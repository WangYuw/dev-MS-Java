package sr.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity (name = "ServiceQuality")
@Table (name = "SERVICEQUALITY")
public class ServiceQuality  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional=false)
	@NotNull
	@Column(name="quality_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="instance_load")
	private float load;

	public ServiceQuality() {
		super();
	}

	public ServiceQuality(long id, float load) {
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
	
	public static ServiceQuality fromServiceQualityTO(ServiceQualityTO sqto) {
		ServiceQuality sq = new ServiceQuality();
		sq.setId(sqto.getId());
		sq.setLoad(sqto.getLoad());
		return sq;
	}
	
	public ServiceQualityTO toServiceQualityTO() {
		ServiceQualityTO sqto = new ServiceQualityTO();
		sqto.setId(this.id);
		sqto.setLoad(this.load);
		return sqto;
	}

	@Override
	public String toString() {
		return "ServiceQuality [id=" + id + ", load=" + load + "]";
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
		ServiceQuality other = (ServiceQuality) obj;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(load) != Float.floatToIntBits(other.load))
			return false;
		return true;
	}
	
}
