package sr.transferables;

public class ServiceQualityRequest extends ServiceRequest {

	public ServiceQualityRequest() {
		super();
	}

	public ServiceQualityRequest(String name, String version) {
		super(name, version);
	}

	@Override
	public String toString() {
		return "ServiceQualityRequest [" + super.toString() + "]";
	}

}
