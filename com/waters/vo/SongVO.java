package com.waters.vo;

import fm.setlist.api.model.Song;

public class SongVO {

    private Song song;
    private String songRegex;

    public SongVO(Song song, String songRegex) {
        this.song = song;
        this.songRegex = songRegex;
    }

    public String getSongName() {
        return song.getName();
    }

    public String getSongRegex() {
        return songRegex;
    }
}
