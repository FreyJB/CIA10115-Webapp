package RoomOrder;

import java.sql.Date;

public class RoomOrderVO implements java.io.Serializable{
	
	
	private Integer RoomOrderId;
	private Integer MemberId;
	private Integer PromotionId;
	private Date RoomOrderDate;
	private Boolean RoomOrderStatus;
	private Integer RoomAmount;
	private Integer PayAmount;
	private Integer PromotionPrice;
	private Date CheckInDate;
	private Date CheckOutDate;
	private Byte[] Idconform;
	private String RoomOrderIdReq;
	
	
	public Integer getRoomOrderId() {
		return RoomOrderId;
	}
	public void setRoomOrderId(Integer roomOrderId) {
		RoomOrderId = roomOrderId;
	}
	public Integer getMemberId() {
		return MemberId;
	}
	public void setMemberId(Integer memberId) {
		MemberId = memberId;
	}
	public Integer getPromotionId() {
		return PromotionId;
	}
	public void setPromotionId(Integer promotionId) {
		PromotionId = promotionId;
	}
	public Date getRoomOrderDate() {
		return RoomOrderDate;
	}
	public void setRoomOrderDate(Date roomOrderDate) {
		RoomOrderDate = roomOrderDate;
	}
	public Boolean getRoomOrderStatus() {
		return RoomOrderStatus;
	}
	public void setRoomOrderStatus(Boolean roomOrderStatus) {
		RoomOrderStatus = roomOrderStatus;
	}
	public Integer getRoomAmount() {
		return RoomAmount;
	}
	public void setRoomAmount(Integer room_amount) {
		RoomAmount = room_amount;
	}
	public Integer getPayAmount() {
		return PayAmount;
	}
	public void setPayAmount(Integer payAmount) {
		PayAmount = payAmount;
	}
	public Integer getPromotionPrice() {
		return PromotionPrice;
	}
	public void setPromotionPrice(Integer promotionPrice) {
		PromotionPrice = promotionPrice;
	}
	public Date getCheckInDate() {
		return CheckInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		CheckInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return CheckOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		CheckOutDate = checkOutDate;
	}
	public Byte[] getIdconform() {
		return Idconform;
	}
	public void setIdconform(Byte[] idconform) {
		Idconform = idconform;
	}
	public String getRoomOrderIdReq() {
		return RoomOrderIdReq;
	}
	public void setRoomOrderIdReq(String roomOrderIdReq) {
		RoomOrderIdReq = roomOrderIdReq;
	}
	
	
}
