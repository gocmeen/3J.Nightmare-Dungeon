/**
 * Created by wifinaynay on 14/12/17.
 */
public class Door extends Entity {
    private int roomID1;
    private int roomID2;
    public Door(int x, int y, int typeID, int width,int height,int roomID1,int roomID2){
        super(x, y, typeID,width,height);
        this.roomID1=roomID1;
        this.roomID2 = roomID2;
    }

    public int getRoomID1() {
        return roomID1;
    }

    public int getRoomID2() {
        return roomID2;
    }
}
