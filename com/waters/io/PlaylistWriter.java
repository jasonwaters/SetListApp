package com.waters.io;

import com.waters.vo.SongVO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaylistWriter {
    protected static final String UTF_8 = "UTF-8";

    private List<SongVO> setListSongs;
    private List<ITunesLibrary.Song> iTunesSongsByArtist;

    public PlaylistWriter(List<SongVO> setListSongs, List<ITunesLibrary.Song> iTunesSongsByArtist) {
        this.setListSongs = setListSongs;
        this.iTunesSongsByArtist = iTunesSongsByArtist;
    }

    public ArrayList<SongVO> write(String fileName) {

        File tempFile = new File(fileName);


        PrintWriter writer = null;

        try {
            FileOutputStream out = new FileOutputStream(tempFile);
            writer = new PrintWriter(new OutputStreamWriter(out, UTF_8));

        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<SongVO> unknownSongs = new ArrayList<SongVO>();

        if (writer != null) {
            writer.println("Name\tArtist\tAlbum");

            for (SongVO setListSong : setListSongs) {
                ITunesLibrary.Song song = getSong(iTunesSongsByArtist, setListSong.getSongRegex());
                if (song != null) {
                    writer.println(song.getName() + "\t" + song.getArtist() + "\t" + song.getAlbum());
                } else {
                    unknownSongs.add(setListSong);
                }
            }
        }

        writer.close();

        return unknownSongs;
    }


    private static ITunesLibrary.Song getSong(List<ITunesLibrary.Song> songs, String songName) {
        for (ITunesLibrary.Song song : songs) {
            Pattern p = Pattern.compile(songName, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(song.getName());

            if(m.matches())
                return song;
        }
        return null;
    }
}
