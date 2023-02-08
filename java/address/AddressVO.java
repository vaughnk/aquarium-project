package address;

public class AddressVO {

	private int addressNum;	// PK
	private String address;
	private String addressPost;
	private String memberId;	// FK
	
	public int getAddressNum() {
		return addressNum;
	}
	public void setAddressNum(int addressNum) {
		this.addressNum = addressNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressPost() {
		return addressPost;
	}
	public void setAddressPost(String addressPost) {
		this.addressPost = addressPost;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "AddressVO [addressNum=" + addressNum + ", address=" + address + ", addressPost=" + addressPost
				+ ", memberId=" + memberId + "]";
	}
	
	
}
