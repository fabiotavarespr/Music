package br.com.fabiotavares.music;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.fabiotavares.music.domain.Playlist;
import br.com.fabiotavares.music.services.PopulaPlayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Rock
        ImageView rock = findViewById(R.id.imageRock);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PlayListActivity.class);
                i.putExtra("playlist_music", PopulaPlayList.playlistRock());
                startActivity(i);
            }
        });

        //Eletronic
        ImageView eletronic = findViewById(R.id.imageEletronic);

        eletronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PlayListActivity.class);
                i.putExtra("playlist_music", PopulaPlayList.playlistEletronic());
                startActivity(i);
            }
        });

        //Pop
        ImageView pop = findViewById(R.id.imagePop);

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PlayListActivity.class);
                i.putExtra("playlist_music", PopulaPlayList.playlistPop());
                startActivity(i);
            }
        });

        //Acustic
        ImageView acustic = findViewById(R.id.imageAcustic);

        acustic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PlayListActivity.class);
                i.putExtra("playlist_music", PopulaPlayList.playlistAcustic());
                startActivity(i);
            }
        });
    }
}
