package com.waters.api;

import com.waters.util.StringUtil;
import fm.setlist.api.model.Set;
import fm.setlist.api.model.Setlist;
import fm.setlist.api.model.Setlists;
import fm.setlist.api.model.Song;
import sun.misc.Regexp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetlistFetcher {

	private String baseURL = "http://api.setlist.fm/rest/0.1/";
	private java.util.Set<String> _songs = new HashSet<String>();

	public SetlistFetcher(String artist) throws JAXBException, IOException {
		URL url = new URL(baseURL + "search/setlists?artistName=" + URLEncoder.encode(artist, "UTF-8"));

		JAXBContext context = JAXBContext.newInstance( Setlists.class );
		URLConnection connection = url.openConnection();
		connection.connect();

		Unmarshaller unmarshaller = context.createUnmarshaller();
		Setlists result = (Setlists) unmarshaller.unmarshal( connection.getInputStream() );

		ArrayList<Setlist> setlists = result.getList();

		if(setlists.size() > 0) {
			Setlist setlist = setlists.get(0);
			ArrayList<Set> sets = setlist.getSets();
			for (Set set : sets) {
				ArrayList<Song> songs = set.getSongs();
				for (Song song : songs) {
					_songs.add(StringUtil.removeParentheses(song.getName()).trim());
				}
			}
		}
	}


	public String[] getSongs() {
		String[] result = new String[_songs.size()];
		return _songs.toArray(result);
	}
}
