public class Song implements Comparable<Song>{
  private String title;
  private String artist;
  private int duration;
  private User owner = null;

  public Song()  {
    this("", "", 0, 0);
  }
  
  public Song(String t, String a, int m, int s)  {
    title = t;
    artist = a;
    duration = m * 60 + s;
  }

  @Override
  public int compareTo(Song o) {
    if (this.getTitle().compareTo(o.getTitle()) > 0) {
      return 1;
    }
    else if (this.getTitle().compareTo(o.getTitle()) < 0) {
      return -1;
    }
    return 0;
  }

  public String getTitle() {
    return title; 
  } 
  
  public String getArtist() { 
    return artist; 
  }
  
  public int getDuration() { 
    return duration; 
  }
  
  public int getMinutes() {
    return duration / 60;
  }
  
  public int getSeconds() {
    return duration % 60;
  }

  public void setOwner(User x) {owner = x;}

  public User getOwner() {return owner;}

  public String toString()  {
    return("\"" + title + "\" by " + artist + " " + (duration / 60) + ":" + (duration%60));
  }
}
