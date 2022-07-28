package com.spotyfoo.priyanka;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Song> mSongDataListMenuModel = Utils.parseDataFile();
        if (mSongDataListMenuModel.size() < 1) {
            System.out.println("There are no songs.Exiting");
            return;
        }

        SongMenuController songMenuController = new SongMenuController(mSongDataListMenuModel);
        SongMenuView menu = new SongMenuView(songMenuController);
        menu.clearScreen();
        menu.initMenu();
    }
}