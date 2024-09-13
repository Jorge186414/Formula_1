package edu.tesji.appconapi;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import edu.tesji.appconapi.model.ErgastAPI;

public class MainActivity extends AppCompatActivity {

    private ErgastAPI.ErgastResponseListener listener;
    private TextInputEditText txtPiloto;
    private TextInputEditText txtTemporada;
    private TextInputEditText txtCarrera;
    private Button btnConectarAPI;
    private TextView txtImpresion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPiloto = findViewById(R.id.tiPiloto);
        txtTemporada = findViewById(R.id.tiTemporada);
        txtCarrera = findViewById(R.id.tiCarrera);
        btnConectarAPI = findViewById(R.id.btAPI);
        txtImpresion = findViewById(R.id.tvImpresion);

        btnConectarAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ErgastAPI.getDriverResults(MainActivity.this, "perez", 2023, 2,
                        new ErgastAPI.ErgastResponseListener() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                // Parsea los datos del JSONObject y configura el texto del TextView
                                try {

                                    // Obtener datos de la carrera
                                    JSONArray results = response.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
                                    JSONObject firstResult = results.getJSONObject(0);
                                    String raceName = firstResult.getString("raceName");
                                    String circuitName = firstResult.getJSONObject("Circuit").getString("circuitName");
                                    String locality = firstResult.getJSONObject("Circuit").getJSONObject("Location").getString("locality");
                                    String country = firstResult.getJSONObject("Circuit").getJSONObject("Location").getString("country");

                                    String resultado = "Resultados de la carrera:\n" + raceName + "\n" + circuitName + "\n" + locality + ", " + country + ",\n";
                                    txtImpresion.setText(resultado);
                                } catch (JSONException e) {
                                    txtImpresion.setText("Error al analizar la respuesta JSON");
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(String message) {
                                txtImpresion.setText("Error: " + message);
                            }
                        });
            }
        });

        // Crea un objeto de tipo ErgastAPI.ErgastResponseListener para manejar la respuesta de la API
        listener = new ErgastAPI.ErgastResponseListener() {
            @Override
            public void onSuccess(JSONObject response) {
                // Aquí manejas la respuesta exitosa de la API
            }

            @Override
            public void onError(String message) {
                // Aquí manejas el error de la API
            }
        };
    }
}

