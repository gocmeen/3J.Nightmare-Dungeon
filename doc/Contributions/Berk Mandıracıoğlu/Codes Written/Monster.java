/**LTH
 * Created by wifinaynay on 01/11/17.
 */
public class Monster extends Character{
    private int monsterType;
    private static final int   SPEED = 2;
    private static final int   HEALTH = 5;
        private static final int   ATTACK_DAMAGE = 50;
        private static final int   ATTACK_SPEED = 50;
        public Monster(int x, int y, int typeID,int width, int height){
            super(x,  y,  typeID, width,  height,  HEALTH ,  SPEED, ATTACK_DAMAGE,ATTACK_SPEED);


    }
}
