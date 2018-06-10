package br.com.fabiotavares.music.services;

import java.util.Arrays;

import br.com.fabiotavares.music.domain.Music;
import br.com.fabiotavares.music.domain.Playlist;

public class PopulaPlayList {

    public static final Playlist playlistEletronic() {
        Playlist playlist = new Playlist();
        playlist.setNome("Eletronic");
        Music musicEletronic1 = new Music("Alok e Mathieu Koss - Big Jet Plane", "1:45");
        Music musicEletronic2 = new Music("Alok (part Zeeba) - Hear me now", "2:35");
        Music musicEletronic3 = new Music("Avicii (part Sandro Cavazza) - Without you", "5:02");
        Music musicEletronic4 = new Music("Bruno Martini (part Zeeba) - With me", "4:09");

        playlist.getMusicas().addAll(Arrays.asList(musicEletronic1, musicEletronic2, musicEletronic3, musicEletronic4));

        return playlist;
    }

    public static final Playlist playlistRock() {
        Playlist playlist = new Playlist();
        playlist.setNome("Rock");
        Music musicRock1 = new Music("The Beatles - Help!", "03:03");
        Music musicRock2 = new Music("Rolling Stones - Sympathy For The Devil", "2:35");
        Music musicRock3 = new Music("The Who - Baba O'Riley", "5:02");
        Music musicRock4 = new Music("Pink Floyd - Wish You Were Here", "4:09");
        Music musicRock5 = new Music("Queen - Bohemian Rhapsody", "6:06");
        Music musicRock6 = new Music("Led Zeppelin - Stairway To Heaven", "4:09");
        Music musicRock7 = new Music(" The Doors - Light My Fire", "4:42");
        Music musicRock8 = new Music("Kiss - Rock And Roll All Nite", "2:47");

        playlist.getMusicas().addAll(
                Arrays.asList(musicRock1,
                        musicRock2,
                        musicRock3,
                        musicRock4,
                        musicRock5,
                        musicRock6,
                        musicRock7,
                        musicRock8));

        return playlist;
    }

    public static final Playlist playlistAcustic() {
        Playlist playlist = new Playlist();
        playlist.setNome("Acustic");
        Music musicAcustic1 = new Music("Karizma Duo - Shut up and Dance", "3:40");
        Music musicAcustic2 = new Music("Zoe Zori - Little Things", "3:23");
        Music musicAcustic3 = new Music("Hannah Dorman - Slow Hands", "2:12");
        Music musicAcustic4 = new Music("Shannon & Keast - Bad Romance", "4:11");
        Music musicAcustic5 = new Music("Ginnie - Waiting All Night", "3:27");

        playlist.getMusicas().addAll(Arrays.asList(musicAcustic1, musicAcustic2, musicAcustic3, musicAcustic4, musicAcustic5));

        return playlist;
    }

    public static final Playlist playlistPop() {
        Playlist playlist = new Playlist();
        playlist.setNome("Pop");
        Music musicPop1 = new Music("Troye Sivan - My My My!", "1:45");
        Music musicPop2 = new Music("Halsey - Sorry", "2:35");
        Music musicPop3 = new Music("Kesha - Woman (feat. the Dap-Kings Horns)", "5:02");
        Music musicPop4 = new Music("Meghan Trainor - No Excuses", "4:09");
        Music musicPop5 = new Music("Shakira - Trap (feat. Maluma)", "4:09");
        Music musicPop6 = new Music("Justin Timberlake - Say Something (feat. Chris Stapleton)", "4:09");

        playlist.getMusicas().addAll(Arrays.asList(musicPop1, musicPop2, musicPop3, musicPop4, musicPop5, musicPop6));

        return playlist;
    }

}
