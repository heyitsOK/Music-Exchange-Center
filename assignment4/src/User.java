import java.util.ArrayList;

public class User {
  private String     userName;
  private boolean    online;
  private ArrayList<Song> songList;

  public User()  {this("");}
  
  public User(String u)  {
    userName = u;
    online = false;
    songList = new ArrayList<Song>();
  }
  
  public String getUserName() { return userName; }
  public boolean isOnline() { return online; }
  public ArrayList<Song> getSongList() {return songList;}

  public void addSong(Song s) {
    s.setOwner(this);
    songList.add(s);
  }

  public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m) {
    ArrayList<Song> songs = m.allAvailableSongs();
    ArrayList<String> fmt = new ArrayList<String>();
    fmt.add(String.format("%-32s%-17s%-8s%-15s", "   TITLE", "ARTIST", "TIME", "OWNER"));
    for (int i = 0; i < songs.size(); i++) {
      fmt.add(String.format("%-32s%-17s%-8s%-15s", (i+1) + ". " + songs.get(i).getTitle(), songs.get(i).getArtist(), (songs.get(i).getDuration()/60 + ":" + String.format("%02d", songs.get(i).getDuration()%60)), songs.get(i).getOwner()));
    }
    return fmt;
  }

  public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist) {
    ArrayList<Song> songs = m.allAvailableSongs();
    ArrayList<String> fmt = new ArrayList<String>();
    fmt.add(String.format("%-32s%-17s%-8s%-15s", "   TITLE", "ARTIST", "TIME", "OWNER"));
    int count = 1;
    for (int i = 0; i < songs.size(); i++) {
      if (songs.get(i).getArtist().equals(artist)) {
        fmt.add(String.format("%-32s%-17s%-8s%-15s", count + ". " + songs.get(i).getTitle(), songs.get(i).getArtist(), (songs.get(i).getDuration()/60 + ":" + String.format("%01d", songs.get(i).getDuration()%60)), songs.get(i).getOwner()));
        count++;
      }
    }
    return fmt;
  }

  public void downloadSong(MusicExchangeCenter m, String title, String ownerName) {
    Song s = m.getSong(title, ownerName);
    if (s != null) {
      addSong(s);
    }
  }

  public Song songWithTitle(String title) {
    for (int i = 0; i < songList.size(); i++) {
      if (songList.get(i).getTitle().equals(title)) {
        return songList.get(i);
      }
    }
    return null;
  }

  public int totalSongTime() {
    int x = 0;
    for (int i = 0; i < songList.size(); i++) {
      x += songList.get(i).getSeconds();
    }
    return x;
  }

  public void register(MusicExchangeCenter m) {
    m.registerUser(this);
  }

  public void logon() {online = true;}
  public void logoff() {online = false;}

  public String toString()  {
    String s = "" + userName + ": " + songList.size() + " songs (";
    if (!online) s += "not ";
    return s + "online)";
  }
}
