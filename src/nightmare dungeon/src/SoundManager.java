
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
        music = new Music("src/nightmare dungeon/res/MainMenu.ogg");
        soundList.add(new Sound("src/nightmare dungeon/res/PlayerAttack.wav"));
        soundList.add(new Sound("src/nightmare dungeon/res/damageTaken.wav"));
        soundList.add(new Sound("src/nightmare dungeon/res/death.wav"));
        soundList.add(new Sound("src/nightmare dungeon/res/doorOpen.wav"));
        soundList.add(new Sound("src/nightmare dungeon/res/powerUp.wav"));
        soundList.add(new Sound("src/nightmare dungeon/res/bossAttack.wav"));
        this.sound_on = sound_on;
        this.music_on = music_on;
    }


    //public playSound();

    //music is played on loop if it is on
    public static void playMusic()throws SlickException
    {
        if(music_on)
        {
            music.loop();
        }
        if(!music_on && music.playing())
        {
            music.stop();
        }
    }


    //sound effects
    public static void playSound(int i)throws SlickException
    {
        if(sound_on)
        {
            if(i ==1)
            {
                soundList.get(0).play();
            }
            if(i == 2 )
            {
                soundList.get(1).play();
            }
            if(i == 3 )
            {
                soundList.get(2).play();
            }
            if(i == 4 )
            {
                soundList.get(3).play();
            }
            if(i == 5 )
            {
                soundList.get(4).play();
            }
            if(i == 6 )
            {
                soundList.get(5).play();
            }

        }

    }
    
    //getter
    public Music getMusic()
    {
        return music;
    }


}
