import java.util.ArrayList;

/**
 * Created by wifinaynay on 01/11/17.
 */
public class GameManager {
    private ArrayList<Map> mapList;
    private Player someone;
    private int currentMapID;
    public GameManager(){
        currentMapID = 0;
        someone= new Player(0,0,0,50,50);
        mapList = new ArrayList<Map>();
    }
    public Map getCurrentMap(){
        return mapList.get(currentMapID);
    }
    public void generateMaps(){
        Room m1 = new Room(1000,1000,0);
        Room m2 = new Room(1000,1000,1);
        Room m3 = new Room(1000,1000,2);
        roomArrayList.add(m1);
        roomArrayList.add(m2);
        roomArrayList.add(m3);
    }
    public void setCurrentMapID(int currentMapID) {
        this.currentMapID = currentMapID;
    }



}
