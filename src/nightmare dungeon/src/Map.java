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
	private int neighbourID;
	//Constructor
	public Map(int mapID, int width, int height,int neighbourID1)throws IOException{
		this.mapID=mapID;
		roomArrayList=new ArrayList<Room>();
		currentRoomID=0;
		 // generating the rooms
		this.height1 = height;
		this.width = width;
		this.neighbourID = neighbourID1;
		generateRooms();
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

				int randomX= ThreadLocalRandom.current().nextInt(0,1330-31);
				int randomY = ThreadLocalRandom.current().nextInt(0,740-31);
				int selectFrom4 = ThreadLocalRandom.current().nextInt(0,4);
				Door d1;
				Door d2;
				if(selectFrom4==0){
					d1 = new Door(0 + 1,randomY,4,30,30,i,j);
					d2 = new Door ( 1330 - 1,randomY,4,30,30,j,i);

				}
				else if(selectFrom4==1){
					d1 = new Door(1330 - 1,randomY,4,30,30,i,j);

					d2 = new Door(0 + 1,randomY,4,30,30,j,i);
				}
				else if(selectFrom4==2){
					d1 = new Door(randomX,0 + 17,4,30,30,i,j);

					d2 = new Door ( randomX,740 - 17,4,30,30,j,i);
				}
				else{
					d1 = new Door(randomX,740-17,4,30,30,i,j);
					d2 = new Door(randomX,0+17,4,30,30,j,i);

				}
				positionsDoor.add(d1);
				positionsDoor.add(d2);

				System.out.println("Door: " + positionsDoor.get(0).x + " -- " + positionsDoor.get(0).y);
				System.out.println("Door: " + positionsDoor.get(1).x + " -- " +positionsDoor.get(1).y);

			}


		}

		//door coordinates



		Room m1 = new Room( width, height1,0,positionsDoor,false,null);
		Room m2 = new Room(width, height1,1,positionsDoor,false,null);
		Portal port = new Portal(0,0,5,40,40,mapID,neighbourID);
		Room m3 = new Room(width, height1,2,positionsDoor,true,port);

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
