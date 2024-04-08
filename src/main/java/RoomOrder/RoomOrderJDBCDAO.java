package RoomOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomOrderJDBCDAO implements RoomOrderDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cactus?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "ciaJB";
	
	private static final String INSERT_STMT = 
			"INSERT INTO room_order (room_order_date, room_order_status, room_amount, check_in_date, check_out_date, pay_amount) VALUES (?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT room_order_id, member_id, room_order_date, promotion_id, room_order_status, room_amount, pay_amount, promotion_price,  check_in_date, check_out_date,id_confirm, room_order_id_req from room_order order by room_order_id";
		private static final String GET_ONE_STMT = 
			"SELECT room_order_id, member_id, room_order_date, promotion_id, room_order_status, room_amount, pay_amount, promotion_price,  check_in_date, check_out_date,id_confirm, room_order_id_req from room_order where room_order_id = ?";
		private static final String DELETE = 
			"DELETE FROM room_order where promotion_id = ?";
		private static final String UPDATE = 
				"UPDATE room_order SET promotion_id=?, room_amount=?, pay_amount=?, promotion_price=? WHERE check_in_date = ?";
			
		
		
		
		@Override
		public void insert(RoomOrderVO roomorderVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);


				pstmt.setDate(1, roomorderVO.getRoomOrderDate());
				pstmt.setBoolean(2, roomorderVO.getRoomOrderStatus());
				pstmt.setInt(3, roomorderVO.getRoomAmount());
				pstmt.setDate(4, roomorderVO.getCheckInDate());
				pstmt.setDate(5, roomorderVO.getCheckOutDate());
				pstmt.setInt(6, roomorderVO.getPayAmount());

				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}
		
		@Override
		public void update(RoomOrderVO roomorderVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, roomorderVO.getPromotionId());
				pstmt.setInt(2, roomorderVO.getRoomAmount());
				pstmt.setInt(3, roomorderVO.getPayAmount());
				pstmt.setInt(4, roomorderVO.getPromotionPrice());
				
				
				pstmt.setDate(5, java.sql.Date.valueOf("2024-04-24"));  //指定特定日期做更新
			
				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}	
		
		
		@Override
		public void delete(Integer promotionid) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, promotionid);

				pstmt.executeUpdate();
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}
		
		
		
		
		@Override
		public RoomOrderVO findByPrimaryKey(Integer room_order_id) {

			RoomOrderVO roomorderVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, room_order_id);	

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					roomorderVO = new RoomOrderVO();
					roomorderVO.setRoomOrderId(rs.getInt("room_order_id"));
					roomorderVO.setMemberId(rs.getInt("member_id"));
					roomorderVO.setPromotionId(rs.getInt("promotion_id"));
					roomorderVO.setRoomOrderDate(rs.getDate("room_order_date"));
					roomorderVO.setRoomOrderStatus(rs.getBoolean("room_order_status"));
					roomorderVO.setRoomAmount(rs.getInt("room_amount"));
					roomorderVO.setPayAmount(rs.getInt("pay_amount"));
					roomorderVO.setPromotionPrice(rs.getInt("promotion_price"));
					roomorderVO.setCheckInDate(rs.getDate("check_in_date"));
					roomorderVO.setCheckOutDate(rs.getDate("check_out_date"));
					roomorderVO.setRoomOrderIdReq(rs.getString("room_order_id_req"));
					
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return roomorderVO;
		}
		
		
		@Override
		public List<RoomOrderVO> getAll() {
			
			List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
			RoomOrderVO roomorderVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			

			try {

				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					roomorderVO = new RoomOrderVO();
					roomorderVO.setRoomOrderId(rs.getInt("room_order_id"));
					roomorderVO.setMemberId(rs.getInt("member_id"));
					roomorderVO.setPromotionId(rs.getInt("promotion_id"));
					roomorderVO.setRoomOrderDate(rs.getDate("room_order_date"));
					roomorderVO.setRoomOrderStatus(rs.getBoolean("room_order_status"));
					roomorderVO.setRoomAmount(rs.getInt("room_amount"));
					roomorderVO.setPayAmount(rs.getInt("pay_amount"));
					roomorderVO.setPromotionPrice(rs.getInt("promotion_price"));
					roomorderVO.setCheckInDate(rs.getDate("check_in_date"));
					roomorderVO.setCheckOutDate(rs.getDate("check_out_date"));
					roomorderVO.setRoomOrderIdReq(rs.getString("room_order_id_req"));
					list.add(roomorderVO);
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
}

		
public static void main(String[] args) {
	
	RoomOrderJDBCDAO dao = new RoomOrderJDBCDAO();
	
	// 新增
//			RoomOrderVO roomorderVO1 = new RoomOrderVO();
//			roomorderVO1.setRoomOrderDate(java.sql.Date.valueOf("2024-05-01"));
//			roomorderVO1.setRoomOrderStatus(true);
//			roomorderVO1.setRoomAmount(50000);
//			roomorderVO1.setCheckInDate(java.sql.Date.valueOf("2024-05-01"));
//			roomorderVO1.setCheckOutDate(java.sql.Date.valueOf("2024-05-02"));
//			roomorderVO1.setPayAmount(50000);
//			dao.insert(roomorderVO1);

			// 修改
			RoomOrderVO roomorderVO2 = new RoomOrderVO();
			roomorderVO2.setCheckInDate(java.sql.Date.valueOf("2024-05-01"));
			roomorderVO2.setPromotionId(12);
			roomorderVO2.setRoomAmount(50000);	
			roomorderVO2.setPayAmount(45000);
			roomorderVO2.setPromotionPrice(5000);
			dao.update(roomorderVO2);
//
//			// 刪除
//			dao.delete(1);
	

			// 查詢 one
//			RoomOrderVO roomorderVO3 = dao.findByPrimaryKey(1);
//			System.out.print(roomorderVO3.getRoomOrderId() + ",");
//			System.out.print(roomorderVO3.getMemberId() + ",");
//			System.out.print(roomorderVO3.getPromotionId() + ",");
//			System.out.print(roomorderVO3.getRoomOrderDate() + ",");
//			System.out.print(roomorderVO3.getRoomOrderStatus() + ",");
//			System.out.print(roomorderVO3.getRoomAmount() + ",");
//			System.out.println(roomorderVO3.getPayAmount());
//			System.out.println(roomorderVO3.getPromotionPrice());
//			System.out.println(roomorderVO3.getCheckInDate());
//			System.out.println(roomorderVO3.getCheckOutDate());
//			System.out.println(roomorderVO3.getRoomOrderIdReq());
//			System.out.println("---------------------");

			// 查詢 All
//			List<RoomOrderVO> list = dao.getAll();
//			for (RoomOrderVO aRoomOrder : list) {
//			    System.out.print("RoomOrderId: " + aRoomOrder.getRoomOrderId() + ", ");
//			    System.out.print("MemberId: " + aRoomOrder.getMemberId() + ", ");
//			    System.out.print("PromotionId: " + aRoomOrder.getPromotionId() + ", ");
//			    System.out.print("RoomOrderDate: " + aRoomOrder.getRoomOrderDate() + ", ");
//			    System.out.print("RoomOrderStatus: " + aRoomOrder.getRoomOrderStatus() + ", ");
//			    System.out.print("RoomAmount: " + aRoomOrder.getRoomAmount() + ", ");
//			    System.out.print("PayAmount: " + aRoomOrder.getPayAmount() + ", ");
//			    System.out.print("PromotionPrice: " + aRoomOrder.getPromotionPrice() + ", ");
//			    System.out.print("CheckInDate: " + aRoomOrder.getCheckInDate() + ", ");
//			    System.out.print("CheckOutDate: " + aRoomOrder.getCheckOutDate() + ", ");
//			    System.out.println("RoomOrderIdReq: " + aRoomOrder.getRoomOrderIdReq());
//			    System.out.println();
//			}
		}
}

