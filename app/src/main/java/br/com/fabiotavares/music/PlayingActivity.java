package br.com.fabiotavares.music;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.CardRequirements;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.TransactionInfo;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;

import java.util.Arrays;

import br.com.fabiotavares.music.domain.Music;
import br.com.fabiotavares.music.domain.Playlist;

public class PlayingActivity extends AppCompatActivity {

    private Playlist playlist;
    private Music music;
    private boolean pause = true;
    private PaymentsClient mPaymentsClient;
    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 991;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        mPaymentsClient =
                Wallet.getPaymentsClient(
                        this,
                        new Wallet.WalletOptions.Builder()
                                .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                                .build());

        // Objeto com a playlist
        playlist = (Playlist) getIntent().getParcelableExtra("playlist_music");

        // Objeto com a musica
        music = (Music) getIntent().getParcelableExtra("music");

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

        final TextView textViewComprar = findViewById(R.id.comprar);
        textViewComprar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                PaymentDataRequest request = createPaymentDataRequest();
                if (request != null) {
                    AutoResolveHelper.resolveTask(
                            mPaymentsClient.loadPaymentData(request),
                            PlayingActivity.this,
                            // LOAD_PAYMENT_DATA_REQUEST_CODE is a constant value
                            // you define.
                            LOAD_PAYMENT_DATA_REQUEST_CODE);
                }

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
        if (playlist.getMusicas().get(0).getNome().equals(music.getNome())) {
            return playlist.getMusicas().get(playlist.getMusicas().size() - 1);
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
        if (playlist.getMusicas().get(playlist.getMusicas().size() - 1).getNome().equals(music.getNome())) {
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

    private void isReadyToPay() {
        IsReadyToPayRequest request =
                IsReadyToPayRequest.newBuilder()
                        .addAllowedPaymentMethod(WalletConstants.PAYMENT_METHOD_CARD)
                        .addAllowedPaymentMethod(WalletConstants.PAYMENT_METHOD_TOKENIZED_CARD)
                        .build();
        Task<Boolean> task = mPaymentsClient.isReadyToPay(request);
        task.addOnCompleteListener(
                new OnCompleteListener<Boolean>() {
                    public void onComplete(Task<Boolean> task) {
                        try {
                            boolean result = task.getResult(ApiException.class);
                            if (result == true) {
                                // Show Google as payment option.
                            } else {
                                // Hide Google as payment option.
                            }
                        } catch (ApiException exception) {
                        }
                    }
                });
    }

        private PaymentDataRequest createPaymentDataRequest () {
            PaymentDataRequest.Builder request =
                    PaymentDataRequest.newBuilder()
                            .setTransactionInfo(
                                    TransactionInfo.newBuilder()
                                            .setTotalPriceStatus(WalletConstants.TOTAL_PRICE_STATUS_FINAL)
                                            .setTotalPrice("10.00")
                                            .setCurrencyCode("USD")
                                            .build())
                            .addAllowedPaymentMethod(WalletConstants.PAYMENT_METHOD_CARD)
                            .addAllowedPaymentMethod(WalletConstants.PAYMENT_METHOD_TOKENIZED_CARD)
                            .setCardRequirements(
                                    CardRequirements.newBuilder()
                                            .addAllowedCardNetworks(
                                                    Arrays.asList(
                                                            WalletConstants.CARD_NETWORK_AMEX,
                                                            WalletConstants.CARD_NETWORK_DISCOVER,
                                                            WalletConstants.CARD_NETWORK_VISA,
                                                            WalletConstants.CARD_NETWORK_MASTERCARD))
                                            .build());

            PaymentMethodTokenizationParameters params =
                    PaymentMethodTokenizationParameters.newBuilder()
                            .setPaymentMethodTokenizationType(
                                    WalletConstants.PAYMENT_METHOD_TOKENIZATION_TYPE_PAYMENT_GATEWAY)
                            .addParameter("gateway", "example")
                            .addParameter("gatewayMerchantId", "exampleGatewayMerchantId")
                            .build();

            request.setPaymentMethodTokenizationParameters(params);
            return request.build();
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
                case LOAD_PAYMENT_DATA_REQUEST_CODE:
                    switch (resultCode) {
                        case Activity.RESULT_OK:
                            PaymentData paymentData = PaymentData.getFromIntent(data);
                            String token = paymentData.getPaymentMethodToken().getToken();
                            break;
                        case Activity.RESULT_CANCELED:
                            break;
                        case AutoResolveHelper.RESULT_ERROR:
                            Status status = AutoResolveHelper.getStatusFromIntent(data);
                            // Log the status for debugging.
                            // Generally, there is no need to show an error to
                            // the user as the Google Pay API will do that.
                            break;
                        default:
                            // Do nothing.
                    }
                    break;
                default:
                    // Do nothing.
            }
        }
}
