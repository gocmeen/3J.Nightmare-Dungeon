import java.util.ArrayList;

/**
 * Created by wifinaynay on 01/11/17.
 */
public class Map {
	//Attributes
	private ArrayList<Room> roomArrayList; //roomlist
	private int currentRoomID; //current id of the room
	private int mapID; //id of the map

	//Constructor
	public Map(int mapID){
		this.mapID=mapID;
		roomArrayList=new ArrayList<Room>();
		currentRoomID=0;
		generateRooms(); // generating the rooms
	}
	// returns the current room
	public Room getCurrentRoom(){
		return roomArrayList.get(currentRoomID);
	}

	//ths method generates the rooms
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
