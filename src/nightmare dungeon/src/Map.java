import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by wifinaynay on 01/11/17.
 */
public class Map {
	//Attributes
	private ArrayList<Room> roomArrayList; //roomlist
	private int currentRoomID; //current id of the room
	private int mapID; //id of the map
	private int width;
	private int height1;

	//Constructor
	public Map(int mapID, int width, int height){
		this.mapID=mapID;
		roomArrayList=new ArrayList<Room>();
		currentRoomID=0;
		generateRooms(); // generating the rooms
		this.height1 = height;
		this.width = width;
	}
	// returns the current room
	public Room getCurrentRoom(){
		return roomArrayList.get(currentRoomID);
	}

	//ths method generates the rooms
	public void generateRooms(){
		ArrayList<Integer> neighbours1 = new ArrayList<Integer>();
		neighbours1.add(1);
		neighbours1.add(2);
		ArrayList<Integer> neighbours2 = new ArrayList<Integer>();
		neighbours2.add(0);
		neighbours2.add(2);
		ArrayList<Integer> neighbours3 = new ArrayList<Integer>();
		neighbours3.add(1);
		neighbours3.add(0);
		Room m1 = new Room( width, height1,0,neighbours1);
		Room m2 = new Room(width, height1,1,neighbours2);
		Room m3 = new Room(width, height1,2,neighbours3);
		roomArrayList.add(m1);
		roomArrayList.add(m2);
		roomArrayList.add(m3);
	}

	public void setCurrentRoomID(int currentRoomID) {
		this.currentRoomID = currentRoomID;
	}

	public int getMapID(){
	    return mapID;
    }

	public int getWidth() {
		return width;
	}

	public int getHeight()
	{
		return height1;
	}

	public int getCurrentRoomID() {
		return currentRoomID;
	}
}
