package org.example.music;

import org.example.db.DatabaseConnection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBtest {
    @BeforeAll
    public static void openDB(){
        org.example.db.DatabaseConnection.connect("/home/student/IdeaProjects/music/src/main/java/org/example/db/songs.db");
    }
    @Test
    public void songRead() throws SQLException {
        try {
            Optional<Song> song = Song.Persistnce.read(1);
            assertTrue(song.isPresent());
        }catch(SQLException e){
            System.err.println(e.getErrorCode());
        }
    }
    @ParameterizedTest
    @CsvFileSource(
        files = "songs.csv",
        useHeadersInDisplayName = true
    )
    void csvIndex(int id, String artist, String title, int length) throws SQLException {
        Optional<Song> optionalSong = Song.Persistnce.read(id);
        Song csvSong = new Song(artist, title, length);
        assertEquals(csvSong, optionalSong.orElse(null));
    }
    @AfterAll
    public static void closeDB(){
        org.example.db.DatabaseConnection.disconnect("connection");
    }



}
