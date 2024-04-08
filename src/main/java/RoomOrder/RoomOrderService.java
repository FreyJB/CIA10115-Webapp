package RoomOrder;

import java.sql.Date;
import java.util.List;

public class RoomOrderService {

		private RoomOrderDAO_interface dao;

		public RoomOrderService() {
			dao = new RoomOrderDAO();
		}

		public RoomOrderVO addRoomOrder(Date room_order_date, Boolean room_order_status, Integer room_amount,
				Date check_in_date, Date check_out_date) {

			RoomOrderVO roomorderVO = new RoomOrderVO();

			roomorderVO.setRoomOrderDate(room_order_date);
			roomorderVO.setRoomOrderStatus(room_order_status);
			roomorderVO.setRoomAmount(room_amount);
			roomorderVO.setCheckInDate(check_in_date);
			roomorderVO.setCheckOutDate(check_out_date);
			
			dao.insert(roomorderVO);

			return roomorderVO;
		}

		public RoomOrderVO updateRoomOrder(Integer promotion_id, Integer room_amount, Integer pay_amount,
				Integer promotion_price) {

			RoomOrderVO roomorderVO = new RoomOrderVO();

			roomorderVO.setPromotionId(promotion_id);
			roomorderVO.setRoomAmount(room_amount);
			roomorderVO.setPayAmount(pay_amount);
			roomorderVO.setPromotionPrice(promotion_price);

			dao.update(roomorderVO);

			return roomorderVO;
		}

		public void deleteRoomOrder(Integer roomorderid) {
			dao.delete(roomorderid);
		}

		public RoomOrderVO getOneRoomOrder(Integer roomorderid) {
			return dao.findByPrimaryKey(roomorderid);
		}

		public List<RoomOrderVO> getAll() {
			return dao.getAll();
		}
	}
