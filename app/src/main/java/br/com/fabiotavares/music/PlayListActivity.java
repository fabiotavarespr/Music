package br.com.fabiotavares.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.fabiotavares.music.domain.Music;
import br.com.fabiotavares.music.domain.Playlist;

public class PlayListActivity extends AppCompatActivity {

    private Playlist playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        playlist = (Playlist) getIntent().getParcelableExtra("playlist_music");

        TextView titulo = findViewById(R.id.tituloPlaylist);
        titulo.setText(playlist.getNome());

        LinearLayout llMusica = findViewById(R.id.LinearNomeMusica);

        for (final Music music : playlist.getMusicas()) {
            TextView nomeMusica = new TextView(this);
            nomeMusica.setText(music.getNome());
            nomeMusica.setTextAppearance(getApplicationContext(), R.style.TextViewNomeMusica);
            nomeMusica.setPadding(10, 10, 10, 10);
            llMusica.addView(nomeMusica);

            nomeMusica.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(PlayListActivity.this, PlayingActivity.class);
                    i.putExtra("playlist_music", playlist);
                    i.putExtra("music", music);
                    startActivity(i);
                }
            });
        }

    }


}
