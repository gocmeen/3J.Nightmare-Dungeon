
import java.util.ArrayList;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Emre
 */
public class SoundManager {
    static boolean sound_on;
    static boolean music_on;
    static protected Music music;
    static protected ArrayList <Sound> soundList;
    static protected final int PLAYER_ATTACK = 1;


    public SoundManager(boolean sound_on, boolean music_on)throws SlickException
    {
        soundList = new ArrayList<Sound>();
        music = new Music("res/MainMenu.ogg");
        soundList.add(new Sound("res/PlayerAttack.wav"));
        this.sound_on = sound_on;
        this.music_on = music_on;
//        soundList.add(new Sound("res/Player_Attack"));
    }


    //public playSound();


    public static void playMusic()throws SlickException
    {
        if(music_on)
        {
            music.loop();;
        }
        if(!music_on && music.playing())
        {
            music.stop();
        }
    }



    public void playSound(int i)throws SlickException
    {
        if(sound_on)
        {
            if(i ==1)
            {
                soundList.get(0).play();
            }
        }

    }

    public Music getMusic()
    {
        return music;
    }


}
