/**
 * Created by wifinaynay on 14/12/17.
 */
public class Portal extends Entity{
    private int mapID1;
    private int mapID2;
    public Portal(int x , int y ,int typeID, int width,int height,int mapID1,int mapID2){
        super(x, y, typeID,width,height);
        this.mapID1=mapID1;
        this.mapID2 = mapID2;
    }

    public int getMapID1() {
        return mapID1;
    }

    public int getMapID2() {
        return mapID2;
    }
}
