package br.com.fabiotavares.music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import br.com.fabiotavares.music.services.PopulaPlayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Rock
        ImageView imageViewRock = findViewById(R.id.imageRock);

        imageViewRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PlayListActivity.class);
                i.putExtra("playlist_music", PopulaPlayList.playlistRock());
                startActivity(i);
            }
        });

        //Eletronic
        ImageView imageViewEletronic = findViewById(R.id.imageEletronic);

        imageViewEletronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PlayListActivity.class);
                i.putExtra("playlist_music", PopulaPlayList.playlistEletronic());
                startActivity(i);
            }
        });

        //Pop
        ImageView imageViewPop = findViewById(R.id.imagePop);

        imageViewPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PlayListActivity.class);
                i.putExtra("playlist_music", PopulaPlayList.playlistPop());
                startActivity(i);
            }
        });

        //Acustic
        ImageView imageViewAcustic = findViewById(R.id.imageAcustic);

        imageViewAcustic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PlayListActivity.class);
                i.putExtra("playlist_music", PopulaPlayList.playlistAcustic());
                startActivity(i);
            }
        });
    }
}
