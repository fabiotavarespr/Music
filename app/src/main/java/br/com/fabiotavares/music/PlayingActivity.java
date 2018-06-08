package br.com.fabiotavares.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fabiotavares.music.domain.Music;
import br.com.fabiotavares.music.domain.Playlist;
import br.com.fabiotavares.music.services.PopulaPlayList;

public class PlayingActivity extends AppCompatActivity {

    private Playlist playlist;
    private Music music;
    private boolean pause = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        playlist = (Playlist) getIntent().getSerializableExtra("playlist_music");
        music = (Music) getIntent().getSerializableExtra("music");

        TextView titulo = findViewById(R.id.tituloPlaylist);
        titulo.setText(playlist.getNome());

        TextView nomeMusica = findViewById(R.id.nomeMusica);
        nomeMusica.setText(music.getNome());

        TextView duracao = findViewById(R.id.duracao);
        duracao.setText(music.getDuracao());

        final ImageView playlists = findViewById(R.id.playlist);
        playlists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlayingActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        final ImageView buttonNext = findViewById(R.id.next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.toast_proxima_musica, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PlayingActivity.this, PlayingActivity.class);
                i.putExtra("playlist_music", playlist);
                i.putExtra("music", proximaMusica());
                startActivity(i);
            }
        });

        final ImageView buttonBack = findViewById(R.id.back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), R.string.toast_musica_anterior, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PlayingActivity.this, PlayingActivity.class);
                i.putExtra("playlist_music", playlist);
                i.putExtra("music", musicaAnterior());
                startActivity(i);
            }
        });


        final ImageView buttonPause = findViewById(R.id.pause);
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pause) {
                    Toast.makeText(getApplicationContext(), R.string.toast_pausando, Toast.LENGTH_SHORT).show();
                    buttonPause.setImageResource(R.drawable.play);
                    pause = false;
                } else {
                    Toast.makeText(getApplicationContext(), R.string.toast_tocando, Toast.LENGTH_SHORT).show();
                    buttonPause.setImageResource(R.drawable.pause);
                    pause = true;
                }
            }
        });

    }

    private Music musicaAnterior() {
        if (playlist.getMusicas().get(0).getNome().equals(music.getNome())){
            return playlist.getMusicas().get(playlist.getMusicas().size()-1);
        }
        for (int i = 0; i < playlist.getMusicas().size(); i++) {
            Music musicaLista = playlist.getMusicas().get(i);
            if (musicaLista.getNome().equals(music.getNome())) {
                return playlist.getMusicas().get(i - 1);
            }

        }
        return null;
    }

    private Music proximaMusica() {
        if (playlist.getMusicas().get(playlist.getMusicas().size()-1).getNome().equals(music.getNome())){
            return playlist.getMusicas().get(0);
        }
        for (int i = 0; i < playlist.getMusicas().size(); i++) {
            Music musicaLista = playlist.getMusicas().get(i);
            if (musicaLista.getNome().equals(music.getNome())) {
                return playlist.getMusicas().get(i + 1);
            }

        }
        return null;
    }
}
