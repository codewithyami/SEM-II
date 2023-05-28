package jdbc.study.college.pcps.model;

public class Address {
	private String tole;
	private String VdcOrMnc;
	private String dist;
	private String state;
	private int wardNo;
	private String addressType;

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getTole() {
		return tole;
	}

	public void setTole(String tole) {
		this.tole = tole;
	}

	public String getVdcOrMnc() {
		return VdcOrMnc;
	}

	public void setVdcOrMnc(String vdcOrMnc) {
		VdcOrMnc = vdcOrMnc;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getWardNo() {
		return wardNo;
	}

	public void setWardNo(int wardNo) {
		this.wardNo = wardNo;
	}

}
