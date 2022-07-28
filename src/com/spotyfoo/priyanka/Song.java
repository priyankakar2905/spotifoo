package com.spotyfoo.priyanka;

public class Song {

    private final String songName;
    private final String albumName;
    private final String albumArtist;
    private final String albumGenre;
    private final String songFilePath;
    private final String albumArtPath;

    private Song(String[] songLine) {
        this.songName = songLine[0];
        this.albumArtist = songLine[1];
        this.albumName = songLine[2];
        this.albumGenre = songLine[3];
        this.songFilePath = songLine[4];
        this.albumArtPath = songLine[5];
    }

    public static Song createSongObject(String[] songLine) {
        return new Song(songLine);
    }

    public String getSongName() {

        return songName;
    }

    public String getAlbumName() {

        return albumName;
    }

    public String getAlbumArtist() {

        return albumArtist;
    }

    public String getAlbumGenre() {

        return albumGenre;
    }

    public String getSongFilePath() {

        return songFilePath;
    }

    public String getAlbumArtPath() {

        return albumArtPath;
    }

}


