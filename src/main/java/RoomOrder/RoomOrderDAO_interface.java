package RoomOrder;

import java.util.List;
import java.util.Set;

public interface  RoomOrderDAO_interface {
	
	public void insert (RoomOrderVO roomVO);
	public void update (RoomOrderVO roomVO);
	public void delete(Integer roomorderid);
    public RoomOrderVO findByPrimaryKey(Integer RoomOrderId);
    public List<RoomOrderVO> getAll();

}
