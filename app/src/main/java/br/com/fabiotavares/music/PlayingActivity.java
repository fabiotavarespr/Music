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

        // Objeto com a playlist
        playlist = (Playlist) getIntent().getSerializableExtra("playlist_music");

        // Objeto com a musica
        music = (Music) getIntent().getSerializableExtra("music");

        //Preenche a textview com nome da playlist
        TextView titulo = findViewById(R.id.tituloPlaylist);
        titulo.setText(playlist.getNome());

        //Preenche a textview com nome da musica na tela
        TextView nomeMusica = findViewById(R.id.nomeMusica);
        nomeMusica.setText(music.getNome());

        //Preenche a textview com a duracao
        TextView duracao = findViewById(R.id.duracao);
        duracao.setText(music.getDuracao());

        //Ao clicar a imagem retorna para a selecao de playlist
        final ImageView playlists = findViewById(R.id.playlist);
        playlists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PlayingActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        //Botao Proximo
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

        //Botao Retornar
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


        //Botao Pause/Play
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

    /**
     * Identifica a musica anterior da playlist
     */
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

    /**
     * Identifica a proxima musica da playlist
     */
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
