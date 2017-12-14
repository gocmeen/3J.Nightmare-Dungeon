import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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
	public Map(int mapID, int width, int height)throws IOException{
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
	public void generateRooms()throws IOException{
		ArrayList<Door> positionsDoor=new ArrayList<Door>();
		//three doors randomly positioned
		for(int i = 0; i  < 3; i++){
			for(int j = i+1; j<3;j++){

				int randomX= ThreadLocalRandom.current().nextInt(0,1330);
				int randomY = ThreadLocalRandom.current().nextInt(0,740);
				int selectFrom4 = ThreadLocalRandom.current().nextInt(0,4);
				Door d1;
				Door d2;
				if(selectFrom4==0){
					d1 = new Door(0,randomY,4,30,30,i,j);
					d2 = new Door ( 1330,randomY,4,30,30,j,i);

				}
				else if(selectFrom4==1){
					d1 = new Door(1330,randomY,4,30,30,i,j);

					d2 = new Door(0,randomY,4,30,30,j,i);
				}
				else if(selectFrom4==2){
					d1 = new Door(randomX,0,4,30,30,i,j);

					d2 = new Door ( randomX,740,4,30,30,j,i);
				}
				else{
					d1 = new Door(randomX,740,4,30,30,i,j);
					d2 = new Door(randomX,0,4,30,30,j,i);

				}
				positionsDoor.add(d1);
				positionsDoor.add(d2);

			}


		}

		//door coordinates



		Room m1 = new Room( width, height1,0,positionsDoor,false);
		Room m2 = new Room(width, height1,1,positionsDoor,false);
		Room m3 = new Room(width, height1,2,positionsDoor,false);
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
