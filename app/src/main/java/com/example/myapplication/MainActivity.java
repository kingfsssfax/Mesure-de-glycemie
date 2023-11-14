package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvAge , TvResult;
    private SeekBar sbAge;
    private RadioButton rbIsFasting, rbIsNoFasting;
    private EditText Valeur;
    private Button btnConsulter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progrss, boolean fromuser) {
                Log.i("Information","onProgressChangeed"+progrss);
                tvAge.setText("Votre age : "+progrss);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            calculer(view);
            }
        });
    }
    private  void init(){
         tvAge=(TextView)findViewById(R.id.tvAge);
         sbAge=(SeekBar) findViewById(R.id.sbAge);
         rbIsFasting=(RadioButton) findViewById(R.id.rbtoui);
         rbIsNoFasting=(RadioButton) findViewById(R.id.rbtnon);
         Valeur=(EditText) findViewById(R.id.valeur);
         btnConsulter=(Button) findViewById(R.id.btnConsulter);
         TvResult=(TextView) findViewById(R.id.tvRes);
    }
public void calculer(View view) {
    Log.i("Information", "button cliqué");
    int age;
    float valeurMesuree;
    boolean verifAge = false, verifValeur = false;
    if (sbAge.getProgress() != 0)
        verifAge = true;
    else
        Toast.makeText(MainActivity.this, "Veuillez saisir votre age !", Toast.LENGTH_LONG).show();
    if (!Valeur.getText().toString().isEmpty())
        verifValeur = true;
    else
        Toast.makeText(MainActivity.this, "Veuillez saisir votre valeur mesuréé !", Toast.LENGTH_LONG).show();
    if (verifAge && verifValeur)
    {
        age = sbAge.getProgress();
        valeurMesuree = Float.valueOf(Valeur.getText().toString());
        boolean isFasting = rbIsFasting.isChecked();
        if(isFasting){
            if(age >= 13){
                if(valeurMesuree < 5.0)
                    TvResult.setText("Niveau de glycémie est trop bas");
                else if (valeurMesuree >= 5.0 && valeurMesuree <= 7.2)
                    TvResult.setText("Niveau de glycémie est normale");
                else
                    TvResult.setText("Niveau de glycémie est trop élevé");
            } else if (age >= 6 && age <= 12) {
                if (valeurMesuree < 5.0)
                    TvResult.setText("Niveau de glycémie est trop bas");
                else if (valeurMesuree >= 5.0 && valeurMesuree <= 10.0)
                    TvResult.setText("Niveau de glycémie est normale");
                else
                    TvResult.setText("Niveau de glycémie est trop élevé");
            }
            else if (age < 6) {
                if (valeurMesuree < 5.5)
                    TvResult.setText("Niveau de glycémie est trop bas");
                else if (valeurMesuree >= 5.5 && valeurMesuree <= 10.0)
                    TvResult.setText("Niveau de glycémie est normale");
                else
                    TvResult.setText("Niveau de glycémie est trop élevé");
            }
        } else {
            if (valeurMesuree > 10.5)
                TvResult.setText("Niveau de glycémie est trop élevé");
            else
                TvResult.setText("Niveau de glycémie est normale");
        }
        }
    }
}