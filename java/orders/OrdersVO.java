package orders;

import java.sql.Date;

public class OrdersVO {

	private int ordersNum;		// PK
	private int productNum;		// FK - PRODUCT
	private String memberId;	// FK - MEMBER
	private String memberName;
	private Date ordersDate;		// SYSDATE -> 데이터타입 바꿈(추가)
	private int ordersCnt;		
	private String memberPhone;   //!
	private String ordersMemo;
	private int ordersPayment;	// DEFAULT 0 결제상태
	private String ordersPaymentMethod;	// 결제 방식
	private int ordersState;	// DEFAULT 0 배송상태
	
	//멤버변수로만(추가)
	private int productPrice; //상품가격
	private String productCategory; //상품카테고리
	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Date getOrdersDate() {
		return ordersDate;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public void setOrdersDate(Date ordersDate) {
		this.ordersDate = ordersDate;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getOrdersPaymentMethod() {
		return ordersPaymentMethod;
	}

	public void setOrdersPaymentMethod(String ordersPaymentMethod) {
		this.ordersPaymentMethod = ordersPaymentMethod;
	}

	public int getOrdersPayment() {
		return ordersPayment;
	}

	public void setOrdersPayment(int ordersPayment) {
		this.ordersPayment = ordersPayment;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}

	public int getOrdersState() {
		return ordersState;
	}

	public void setOrdersState(int ordersState) {
		this.ordersState = ordersState;
	}

	public int getOrdersNum() {
		return ordersNum;
	}

	public void setOrdersNum(int ordersNum) {
		this.ordersNum = ordersNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	

	public int getOrdersCnt() {
		return ordersCnt;
	}

	public void setOrdersCnt(int ordersCnt) {
		this.ordersCnt = ordersCnt;
	}

	public String getOrdersMemo() { 
		return ordersMemo;
	}

	public void setOrdersMemo(String ordersMemo) {
		this.ordersMemo = ordersMemo;
	}

	@Override
	public String toString() {
		return "OrdersVO [ordersNum=" + ordersNum + ", productNum=" + productNum + ", memberId=" + memberId
				+ ", memberName=" + memberName + ", ordersDate=" + ordersDate + ", ordersCnt=" + ordersCnt
				+ ", memberPhone=" + memberPhone + ", ordersMemo=" + ordersMemo + ", ordersPayment=" + ordersPayment
				+ ", ordersPaymentMethod=" + ordersPaymentMethod + ", ordersState=" + ordersState + "]";
	}

	


}
