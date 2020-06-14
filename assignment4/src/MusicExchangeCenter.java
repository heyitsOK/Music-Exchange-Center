import java.util.*;

public class MusicExchangeCenter {
    private ArrayList<User> users;
    private HashMap<String, Float> royalties = new HashMap<String, Float>();
    private ArrayList<Song> downloadedSongs = new ArrayList<Song>();

    public MusicExchangeCenter() {
        users = new ArrayList<User>();
    }

    public ArrayList<Song> allAvailableSongs() {
        ArrayList<Song> allSongs = new ArrayList<Song>();
        for (int i = 0; i < onlineUsers().size(); i++) {
            allSongs.addAll(onlineUsers().get(i).getSongList());
        }
        return allSongs;
    }

    public ArrayList<User> onlineUsers() {
        ArrayList<User> online = new ArrayList<User>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).isOnline()) {
                online.add(users.get(i));
            }
        }
        return online;
    }

    public User userWithName(String s) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(s)) {
                return users.get(i);
            }
        }
        return null;
    }

    public void registerUser(User x) {
        if (userWithName(x.getUserName()) == null) {
            users.add(x);
        }
    }

    public void displayRoyalties() {
        System.out.println(String.format("%-8s%-15s", "Amount", "Artist"));
        System.out.println("-------------------");
        for (String i: royalties.keySet()){
            System.out.println(String.format("%-8s%-15s", "$" + royalties.get(i).toString(), i));
        }
    }

    public TreeSet<Song> uniqueDownloads() {
        TreeSet<Song> tsongs = new TreeSet<Song>();
        for (Song i : downloadedSongs) {
            if (!(tsongs.contains(i))) {
                tsongs.add(i);
            }
        }
        return tsongs;
    }

    public ArrayList<Pair<Integer, Song>> songsByPopularity() {
        ArrayList<Pair<Integer, Song>> pop = new ArrayList<Pair<Integer, Song>>();

        Collections.sort(pop, new Comparator<Pair<Integer, Song>>() {
            @Override
            public int compare(Pair<Integer, Song> o1, Pair<Integer, Song> o2) {

                if (o1.getKey() > o2.getKey()) {

                    pop.add(o1);
                    return 1;
                }
                else if (o1.getKey() < o2.getKey()) {
                    pop.add(o2);
                    return -1;
                }
                return 0;
            }
        });
        System.out.println(pop);
        return pop;
    }

    public ArrayList<Song> availableSongsByArtist(String artist) {
        ArrayList<Song> artsongs = new ArrayList<Song>();
        for (int i = 0; i < allAvailableSongs().size(); i++) {
            if (allAvailableSongs().get(i).getArtist().equals(artist)) {
                artsongs.add(allAvailableSongs().get(i));
            }
        }
        return artsongs;
    }

    public Song getSong(String title, String ownerName) {
        Song s = userWithName(ownerName).songWithTitle(title);
        if (s != null) {
            downloadedSongs.add(s);
            royalties.put(s.getArtist(), 0.25f);
        }
        return s;
    }

    public String toString() {
        return "Music Exchange Center (" + onlineUsers().size() + " users online, " + allAvailableSongs().size() + " songs available)";
    }

    public ArrayList<Song> getDownloadedSongs() {
        return downloadedSongs;
    }
}
