package com.spotyfoo.priyanka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    protected static final String BASE_PATH_ASSETS = System.getProperty("user.dir") + File.separator + "assets" + File.separator;
    protected static final String DATA_PATH = BASE_PATH_ASSETS + "data.txt";
    protected static final String ALBUM_PATH = BASE_PATH_ASSETS + "albums" + File.separator;
    protected static final String SONG_PATH = BASE_PATH_ASSETS + "songs" + File.separator;
    protected static final String DEFAULT_ALBUM_ART_PATH = BASE_PATH_ASSETS + "no-picture.png";

    protected static final String SONG = "Songs";
    protected static final String ARTIST = "Artists";
    protected static final String ALBUM = "Albums";
    protected static final String GENRE = "Genres";
    protected static final String SEARCH = "Search";
    protected static final String EXIT = "Exit";
    protected static final String ROOT = "Main";
    protected static final String BACK_TO_MAIN_MENU = "Back to main menu";

    public static List<Song> parseDataFile() {
        List<Song> songList = new ArrayList<>();
        String[] songLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(Utils.DATA_PATH));
            String line;
            while ((line = br.readLine()) != null) {
                songLine = line.split(",");
                Song song = Song.createSongObject(songLine);
                songList.add(song);
            }

        } catch (IOException e) {
            System.out.println("Data File \"data.txt\" is not provided");
            System.exit(0);
        }
        return songList;

    }
}