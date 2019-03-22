package sr.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import sr.transferables.RegisterInfoTO;

@Entity (name = "RegisterInfo")
@Table (name = "REGISTERINFO")

@NamedQueries({
    @NamedQuery(name = "RegisterInfo.findAll", query = "SELECT ri FROM RegisterInfo ri"),
    @NamedQuery(name = "RegisterInfo.findById", query = "SELECT ri FROM RegisterInfo ri WHERE ri.id = :id"),
    @NamedQuery(name = "RegisterInfo.findByName", query = "SELECT ri FROM RegisterInfo ri WHERE ri.name = :name"),
    @NamedQuery(name = "RegisterInfo.findByVersion", query = "SELECT ri FROM RegisterInfo ri WHERE ri.version = :version"),
    @NamedQuery(name = "RegisterInfo.findByNameVersion", query = "SELECT ri FROM RegisterInfo ri WHERE ri.name = :name AND ri.version = :version"),
    @NamedQuery(name = "RegisterInfo.findByIp", query = "SELECT ri FROM RegisterInfo ri WHERE ri.ip = :ip")
})
public class RegisterInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional=false)
	@NotNull
	@Column(name="instance_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Size(max=255)
	@NotNull
	@Column(name="service_name")
	private String name;
	
	@Size(max=255)
	@NotNull
	@Column(name="instance_ip", unique=true)
	private String ip;
	
	@Size(max=255)
	@NotNull
	@Column(name="service_version")
	private String version;
	
    @OneToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "instance_quality", nullable = true)
	private ServiceQuality quality;
       
    public RegisterInfo() {
		super();
	}

	public RegisterInfo(long id, String name, String ip, String version,
			ServiceQuality quality) {
		super();
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.version = version;
		this.quality = quality;
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

	public ServiceQuality getQuality() {
		return quality;
	}

	public void setQuality(ServiceQuality quality) {
		this.quality = quality;
	}

	public static RegisterInfo fromRegisterInfoTO(EntityManager em, RegisterInfoTO rito) {
    	RegisterInfo ri = new RegisterInfo();
		ri.setId(rito.getId());
		ri.setIp(rito.getIp());
		ri.setName(rito.getName());
		ri.setVersion(rito.getVersion());
		ri.setQuality(rito.getQualityId() == 0 ? null : em.find(ServiceQuality.class, rito.getQualityId()));
		return ri;
	}
	
	public RegisterInfoTO toRegisterInfoTO() {
		RegisterInfoTO rito = new RegisterInfoTO();
		rito.setId(this.id);
		rito.setIp(this.ip);
		rito.setName(this.name);
		rito.setVersion(this.version);
		rito.setQualityId(this.quality == null ? 0 : this.quality.getId());
		return rito;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((quality == null) ? 0 : quality.hashCode());
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
		RegisterInfo other = (RegisterInfo) obj;
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
		if (quality == null) {
			if (other.quality != null)
				return false;
		} else if (!quality.equals(other.quality))
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
		return "RegisterInfo [id=" + id + ", name=" + name + ", ip=" + ip
				+ ", version=" + version + ", quality=" + quality + "]";
	}
    
}
