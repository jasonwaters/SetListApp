package com.waters.api;

import com.waters.util.StringUtil;
import com.waters.vo.SongVO;
import fm.setlist.api.model.Set;
import fm.setlist.api.model.Setlist;
import fm.setlist.api.model.Setlists;
import fm.setlist.api.model.Song;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SetlistFetcher {

	private String baseURL = "http://api.setlist.fm/rest/0.1/";


	public SetlistFetcher(){
	}

    public List<SongVO> getSetLists(String artist) throws JAXBException, IOException {
        List<SongVO> resultSongs = new ArrayList<SongVO>();

        URL url = new URL(baseURL + "search/setlists?artistName=" + URLEncoder.encode(artist, "UTF-8"));

		JAXBContext context = JAXBContext.newInstance( Setlists.class );
		URLConnection connection = url.openConnection();
		connection.connect();

		Unmarshaller unmarshaller = context.createUnmarshaller();
		Setlists result = (Setlists) unmarshaller.unmarshal( connection.getInputStream() );

		ArrayList<Setlist> setlists = result.getList();

		if(setlists.size() > 0) {

            Setlist setlist;
            int i=0;

            //keep looking until we find a setlist that has sets recorded for it.
            do {
                setlist = setlists.get(i);
                i++;
            } while(i < setlists.size() && setlist.getSets().size() == 0);

            ArrayList<Set> sets = setlist.getSets();
			for (Set set : sets) {
				ArrayList<Song> songs = set.getSongs();
				for (Song song : songs) {
                    resultSongs.add(new SongVO(song, prepareSongName(song.getName())));
				}
			}
		}

        return resultSongs;
    }



    public List<SongVO> getSetList(String setlistID) throws JAXBException, IOException {
        List<SongVO> resultSongs = new ArrayList<SongVO>();

        URL url = new URL(baseURL + "setlist/" + setlistID);

		JAXBContext context = JAXBContext.newInstance( Setlist.class );
		URLConnection connection = url.openConnection();
		connection.connect();

		Unmarshaller unmarshaller = context.createUnmarshaller();
		Setlist setlist = (Setlist) unmarshaller.unmarshal( connection.getInputStream() );

        ArrayList<Set> sets = setlist.getSets();
        for (Set set : sets) {
            ArrayList<Song> songs = set.getSongs();
            for (Song song : songs) {
                resultSongs.add(new SongVO(song, prepareSongName(song.getName())));
            }
        }

        return resultSongs;
    }



    private String prepareSongName(String originalName) {
        String name = StringUtil.removeParentheses(originalName);
        name = StringUtil.removeThe(name);
        name = StringUtil.removeAnd(name);
        name = name.replace("+", "\\+"); //probably want to check for other things like ( ) * .
//        name = StringUtil.removePunctuation(name);

        String[] tokens = name.trim().split(" ");

        StringBuffer regexPattern = new StringBuffer("(.)*");

        for (String token : tokens) {
            token = token.trim();
            if(token.length() > 0) {
               regexPattern.append("(" + token + ")+(.)*");
            }
        }

        return regexPattern.toString();
    }
}
