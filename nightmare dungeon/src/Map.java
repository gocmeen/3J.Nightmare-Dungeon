import java.util.ArrayList;

/**
 * Created by wifinaynay on 01/11/17.
 */
public class Map {
    private ArrayList<Room> roomArrayList;
    private int currentRoomID;
    private int mapID;
    public Map(int mapID){
        this.mapID=mapID;
        roomArrayList=new ArrayList<Room>();
        currentRoomID=0;
        generateRooms();
    }
   public Room getCurrentRoom(){
        return roomArrayList.get(currentRoomID);
   }
    public void generateRooms(){
        Room m1 = new Room(1000,1000,0);
        Room m2 = new Room(1000,1000,1);
        Room m3 = new Room(1000,1000,2);
        roomArrayList.add(m1);
        roomArrayList.add(m2);
        roomArrayList.add(m3);
    }
    public void setCurrentRoomID(int currentRoomID) {
        this.currentRoomID = currentRoomID;
    }

}
