import java.util.Date;

/**
 * Created by wifinaynay on 04/11/17.
 */
public class Mytimergame implements Runnable{
public boolean aBoolean= false;
    public void run(){
        aBoolean=true;
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (elapsedTime < 1*1*1000) {
            //perform db poll/check
            elapsedTime = (new Date()).getTime() - startTime;
        }


        aBoolean= false;




    }

}
