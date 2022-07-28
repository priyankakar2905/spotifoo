package com.spotyfoo.priyanka;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class SongMenuController {

    private final List<Song> songList;

    public SongMenuController(List<Song> songList) {
        this.songList = songList;
    }

    public Song getSongByName(String songName) {
        for (Song song : songList) {
            if (song.getSongName().equalsIgnoreCase(songName)) return song;
        }
        return null;
    }

    public List<Song> getSongListByAlbum(String album) {
        List<Song> albumSongs = new ArrayList<>();
        for (Song song : songList) {
            if (song.getAlbumName().equalsIgnoreCase(album)) albumSongs.add(song);
        }
        return albumSongs;
    }

    public List<Song> getSongListByArtist(String artist) {
        List<Song> artistSongs = new ArrayList<>();
        for (Song song : songList) {
            if (song.getAlbumArtist().equalsIgnoreCase(artist)) artistSongs.add(song);
        }
        return artistSongs;
    }

    public List<Song> getSongListByGenre(String genre) {
        List<Song> genreSongs = new ArrayList<>();
        for (Song song : songList) {
            if (song.getAlbumGenre().equalsIgnoreCase(genre)) genreSongs.add(song);
        }
        return genreSongs;
    }


    public List<String> getAppropriateMenuList(String item, String searchString) {
        switch (item) {
            case Utils.SONG:
                return getSongList(null);

            case Utils.ALBUM:
                return getAlbumList();

            case Utils.ARTIST:
                return getArtistList();

            case Utils.GENRE:
                return getGenreList();

            case Utils.SEARCH:
                if (searchString != null) return getSearchList(searchString);
                else return null;

            default:
                return null;
        }

    }

    public List<String> getSubmenuListOrTakeAction(String menuTitle, String menuEntry) throws IOException {
        switch (menuTitle) {
            case Utils.ALBUM:
                return getSongList(getSongListByAlbum(menuEntry));
            case Utils.ARTIST:
                return getSongList(getSongListByArtist(menuEntry));
            case Utils.GENRE:
                return getSongList(getSongListByGenre(menuEntry));
            default:
                playMusicAndShowAlbumArt(getSongByName(menuEntry));
                break;
        }
        return null;
    }

    public List<String> getSongList(List<Song> currentSongList) {
        if (currentSongList == null) {
            currentSongList = songList;
        }
        List<String> songNameList = new ArrayList<>();
        for (Song song : currentSongList) {
            songNameList.add(song.getSongName());
        }

        return songNameList;
    }


    public List<String> getAlbumList() {
        List<String> uniqueAlbumList = new ArrayList<>();
        for (Song song : songList) {
            uniqueAlbumList.add(song.getAlbumName());
            uniqueAlbumList = uniqueAlbumList.stream().distinct().collect(Collectors.toList());
        }

        return uniqueAlbumList;
    }

    public List<String> getArtistList() {
        List<String> uniqueArtistList = new ArrayList<>();
        for (Song song : songList) {
            uniqueArtistList.add(song.getAlbumArtist());
            uniqueArtistList = uniqueArtistList.stream().distinct().collect(Collectors.toList());
        }
        return uniqueArtistList;
    }

    public List<String> getGenreList() {
        List<String> uniqueGenreList = new ArrayList<>();
        for (Song song : songList) {
            uniqueGenreList.add(song.getAlbumGenre());
            uniqueGenreList = uniqueGenreList.stream().distinct().collect(Collectors.toList());
        }
        return uniqueGenreList;
    }

    public List<String> getSearchList(String searchItem) {
        List<String> searchList = new ArrayList<>();
        for (Song song : songList) {
            if (song.getSongName().toLowerCase().contains(searchItem.toLowerCase())) {
                searchList.add(song.getSongName());
            }
            searchList = searchList.stream().distinct().collect(Collectors.toList());
        }
        return searchList.size() > 0 ? searchList : null;
    }

    private void playMusicAndShowAlbumArt(Song song) throws IOException {
        String songPath = Utils.SONG_PATH + song.getSongFilePath();
        String albumArtPath = Utils.ALBUM_PATH + song.getAlbumArtPath();

        File file = new File(songPath);
        File file1 = new File(albumArtPath);
        if (!Desktop.isDesktopSupported()) {
            throw new IOException("Cannot support opening file in your environment");
        }
        Desktop desktop = Desktop.getDesktop();

        if (file.exists()) {
            desktop.open(file);
            if (file1.exists()) {
                desktop.open(file1);
            } else {
                desktop.open(new File(Utils.DEFAULT_ALBUM_ART_PATH));
            }
            exit(0);

        } else {
            throw new IOException("Selected Music File is not available!!!\n");
        }
    }

}