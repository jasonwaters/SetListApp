import com.waters.api.SetlistFetcher;
import com.waters.io.*;
import fm.setlist.api.model.Set;
import fm.setlist.api.model.Setlist;
import fm.setlist.api.model.Setlists;
import fm.setlist.api.model.Song;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetListApp {
	
	public static void main(String[] args) throws Exception {
		if(args.length == 0)
			throw new Exception("Artist is a required command line argument");

		String artistName = args[0];
        String setlistID = null;

        if(args.length > 1) {
            setlistID = args[1];
        }

		//SetList Stuff
		SetlistFetcher fetcher = new SetlistFetcher();

        List<String> setListSongs = setlistID != null ? fetcher.getSetList(setlistID) : fetcher.getSetLists(artistName);

		// iTunes Library
		String libraryPath = System.getProperty("user.home") + "/Music/iTunes/iTunes Music Library.xml";
		ITunesLibrary library = new ITunesLibrary(libraryPath);

		List<ITunesLibrary.Song> iTunesSongsByArtist = library.getSongs(artistName);


		System.out.println("Name\tArtist\tAlbum");

		ArrayList<String> unknownSongs = new ArrayList<String>();

		for (String setListSong : setListSongs) {
			ITunesLibrary.Song song = getSong(iTunesSongsByArtist, setListSong);
			if(song != null) {
				System.out.println(song.getName()+"\t"+song.getArtist()+"\t"+song.getAlbum());
			}else {
				unknownSongs.add(setListSong);
			}
		}

		if(unknownSongs.size() > 0) {
            System.out.println();
			System.out.println("Songs that had no matching iTunes song:");
            System.out.println("=======================================");
			for (String unknownSong : unknownSongs) {
				System.out.println(unknownSong);
			}
		}
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
