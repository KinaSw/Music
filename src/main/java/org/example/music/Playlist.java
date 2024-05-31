package org.example.music;

import java.util.ArrayList;

public class Playlist extends ArrayList<Song> {

    public Song atSecond(int second){
        if(second<0){
            throw new IndexOutOfBoundsException("Seconds less than 0: "+second);

        }
        int sum = 0;
        for(Song song : this){
            sum += song.getDuration();
            if(sum >= second){
                return song;
            }
        }
        throw new IndexOutOfBoundsException("Seconds greater than playlist time: "+ second);
    }
}
