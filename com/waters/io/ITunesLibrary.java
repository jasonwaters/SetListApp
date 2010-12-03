package com.waters.io;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public class ITunesLibrary extends DefaultHandler {

	private String libraryPath;

	List<Song> songs;
	Map<String, List<Song>> songsByArtist;

	private StringBuffer tempVal;

	//to maintain context
	private Song tempSong;

	boolean foundSongs = false;

	private String previousTag;
	private String previousTagVal;

	private Comparator<Song> songComparator = new Comparator<Song>() {
		public int compare(Song songA, Song songB) {
			int a = songA.getArtist().compareToIgnoreCase(songB.getArtist());
			if(a!=0) return a;

			return songA.getName().compareToIgnoreCase(songB.getName());
		}
	};

	public ITunesLibrary(String libraryPath) {
		songs = new ArrayList<Song>();
		songsByArtist = new HashMap<String, List<Song>>();

		this.libraryPath = libraryPath;

		parseDocument();

		Collections.sort(songs, songComparator);

		for (Song song : songs) {
			if(song.getArtist() != null && song.getArtist().length() > 0) {
				String artistName = song.getArtist().toLowerCase();

				if(!songsByArtist.containsKey(artistName)) {
					songsByArtist.put(artistName, new ArrayList<Song>());
				}

				songsByArtist.get(artistName).add(song);
			}
		}
	}

	private void parseDocument() {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser sp = spf.newSAXParser();
			sp.parse(libraryPath, this);
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public List<Song> getSongs() {
		return songs;
	}

	public List<Song> getSongs(String artistName) {
		List<Song> artistSongs = songsByArtist.get(artistName.toLowerCase());

		if(artistSongs == null) {
			artistSongs = new ArrayList<Song>();
		}

		return artistSongs;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		tempVal = new StringBuffer();

		if (foundSongs) {
			if ("key".equals(previousTag) && "dict".equalsIgnoreCase(qName)) {
				tempSong = new Song();
				songs.add(tempSong);
			}
		} else {
			if ("key".equals(previousTag) && "Tracks".equalsIgnoreCase(previousTagVal) && "dict".equalsIgnoreCase(qName)) {
				foundSongs = true;
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal.append(ch, start, length); //this is to work around special html characters.  we just keep appending read characters even if there are odd characters separating them.
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (foundSongs) {
			if (previousTagVal.equalsIgnoreCase("Name") && qName.equals("string")) {
				tempSong.setName(tempVal.toString());
			} else if (previousTagVal.equalsIgnoreCase("Artist") && qName.equals("string")) {
				tempSong.setArtist(tempVal.toString());
			} else if (previousTagVal.equalsIgnoreCase("Track ID") && qName.equals("integer")) {
				Integer value = Integer.parseInt(tempVal.toString());
				tempSong.setTrackID(value.intValue());
			} else if (previousTagVal.equalsIgnoreCase("Album") && qName.equals("string")) {
				tempSong.setAlbum(tempVal.toString());
			} else if (previousTagVal.equalsIgnoreCase("Location") && qName.equals("string")) {
				try {
					tempSong.setLocation(URLDecoder.decode(tempVal.toString(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else if (previousTagVal.equalsIgnoreCase("Play Count") && qName.equals("integer")) {
				Integer value = Integer.parseInt(tempVal.toString());
				tempSong.setPlayCount(value.intValue());
			}

			// Mark when we come to the end of the "Tracks" dict.
			if ("key".equals(qName) && "Playlists".equalsIgnoreCase(tempVal.toString())) {
				foundSongs = false;
			}
		}

		// Keep track of the previous tag so we can track the context when we're at the second tag in a key, value pair.
		previousTagVal = tempVal.toString();
		previousTag = qName;
	}

	/**
	 * A simple representation of a song in the iTunes library.
	 */
	public class Song {

		private String name="";
		private String artist="";
		private String album="";
		private String location="";
		private Integer trackID=0;

		private Integer playCount;

		public Song() {
		}

		public Song(String artist) {
			this.artist = artist;
			this.name = null;
			this.album = null;
			this.location = null;
			this.trackID = null;
			this.playCount = null;
		}

		public Song(String artist, String album, String name) {
			this.name = name;
			this.artist = artist;
			this.album = album;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name.trim();
		}

		public String getArtist() {
			return artist;
		}

		public void setArtist(String artistName) {
			this.artist = artistName.trim();
		}

		public String getAlbum() {
			return album;
		}

		public void setAlbum(String albumName) {
			this.album = albumName.trim();
		}

		public int getPlayCount() {
			return playCount;
		}

		public void setPlayCount(int playCount) {
			this.playCount = playCount;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public int getTrackID() {
			return trackID;
		}

		public void setTrackID(int trackID) {
			this.trackID = trackID;
		}
	}

}
