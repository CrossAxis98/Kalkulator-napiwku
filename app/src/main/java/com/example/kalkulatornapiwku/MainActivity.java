package com.example.kalkulatornapiwku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    DecimalFormat kosztF = new DecimalFormat("###,###,###,###.##");
    private static final String TAG = "Aktywność główna";

    private int procent;
    Button ocenButton;
    EditText kosztEditText;
    EditText napiwekEditText;
    Button buttonOblicz;
    TextView textViewKwotaNapiwku;
    TextView textViewKwotaCalkowita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ocenButton = (Button) findViewById(R.id.ocenButton);
        kosztEditText = (EditText) findViewById(R.id.kosztEditText);
        napiwekEditText = (EditText) findViewById(R.id.napiwekEditText);
        buttonOblicz = (Button) findViewById(R.id.buttonOblicz);
        textViewKwotaCalkowita = (TextView) findViewById(R.id.textViewKwotaCalkowita);
        textViewKwotaNapiwku = (TextView) findViewById(R.id.textViewKwotaNapiwku);

        final RatingBar ratingBar = findViewById(R.id.rating);
        ocenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = "Ocena wynosi: " + ratingBar.getRating();
                procent = (int) (2 * ratingBar.getRating());
                String strWarNap = String.valueOf(procent);
                napiwekEditText.setText(strWarNap);
                Toast.makeText(MainActivity.this, rating, Toast.LENGTH_LONG).show();

                Log.i(TAG, "Przycisk oceny użyty. ");
            }
        });


        final Spinner spinner = (Spinner) findViewById(R.id.spinnerOcenaJedzenia);
        String[] elementy = {"Brak opinii.", "Nie. Było okropne", "Średnie", "Przyzwoite", "Tak. Bardzo smaczne"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, elementy);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int id, long position) {


                switch ((int) position) {
                    case 0:
                        //brak opinii
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Wybrano opcję: 'Nie. Było okropne'", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Wybrano opcję: 'Średnie'", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "Wybrano opcję: 'Przyzwoite'", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "Wybrano opcję: 'Tak. Bardzo smaczne'", Toast.LENGTH_SHORT).show();
                        break;


                }
                Log.i(TAG, "Spinner uzyty. ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


        buttonOblicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kwotaZaplaty = kosztEditText.getText().toString();
                String prNapiwku = napiwekEditText.getText().toString();
                int procentNapiwek = Integer.parseInt(prNapiwku);
                double napiwek = (Double.parseDouble(kwotaZaplaty) * procentNapiwek) /100;
                String strNap ="Kwota napiwku: " + String.valueOf(kosztF.format(napiwek));
                double razem = Double.parseDouble(kwotaZaplaty)+napiwek;
                String strRaz = "Razem do zapłaty: "+ String.valueOf(kosztF.format(razem));
                textViewKwotaNapiwku.setText(strNap);
                textViewKwotaCalkowita.setText(strRaz);

                Toast.makeText(MainActivity.this, "Obliczono", Toast.LENGTH_SHORT).show();

                Log.i(TAG, "Przycisk obliczenia użyty. ");
            }

        });


    }

}
