import com.waters.api.SetlistFetcher;
import com.waters.io.*;
import com.waters.vo.SongVO;
import fm.setlist.api.model.Set;
import fm.setlist.api.model.Setlist;
import fm.setlist.api.model.Setlists;
import fm.setlist.api.model.Song;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
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

        List<SongVO> setListSongs = setlistID != null ? fetcher.getSetList(setlistID) : fetcher.getSetLists(artistName);

		// iTunes Library
		String libraryPath = System.getProperty("user.home") + File.separator + "Music"+File.separator+"iTunes"+File.separator+"iTunes Music Library.xml";
		ITunesLibrary library = new ITunesLibrary(libraryPath);

		List<ITunesLibrary.Song> iTunesSongsByArtist = library.getSongs(artistName);

        PlaylistWriter writer = new PlaylistWriter(setListSongs, iTunesSongsByArtist);

        String playlistFileName = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + artistName + ".txt";

        ArrayList<SongVO> unknownSongs = writer.write(playlistFileName);

        System.out.println("=======================================");
        System.out.println("Playlist written to: " + playlistFileName);
        System.out.println("Import it into iTunes via the 'File > Library > Import Playlist...' menu.");
        System.out.println();

        if (unknownSongs.size() > 0) {
            System.out.println("=======================================");
            System.out.println("Songs by "+artistName+" that had no matching iTunes song:");
            System.out.println("=======================================");
            for (SongVO unknownSong : unknownSongs) {
                System.out.println(unknownSong.getSongName());
            }
        }

        System.out.println("=======================================");
    }
}
