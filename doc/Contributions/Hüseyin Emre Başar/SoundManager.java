
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
    static boolean sound_on; //boolean variable to determine the state of effect sounds
    static boolean music_on; //boolean variable to determine the state of music
    static protected Music music; //music object is created
    static protected ArrayList <Sound> soundList; //an arrayList of sound objects
    static protected final int PLAYER_ATTACK = 1; //index of player attack sound in soundlist

    //constructor
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

    //music is played on loop if it is on
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


    //sound effects
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
    
    //getter
    public Music getMusic()
    {
        return music;
    }


}
