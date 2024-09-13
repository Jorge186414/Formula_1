package edu.tesji.appconapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.tesji.appconapi.model.ErgastAPI;
import edu.tesji.appconapi.model.ProcesarDatos;
import edu.tesji.appconapi.spinners.AdaptadorSpinner;

public class splashCarga extends AppCompatActivity {

    private ErgastAPI.ErgastResponseListener listener;

    private Button btnConeccion;
    private Spinner cmbPiloto;
    private Spinner cmbTemporada;
    private String nombrePiloto;
    private String numeroTemporada;
    private TextInputEditText txtCarrera;
    private TextView txtImpresion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            btnConeccion = findViewById(R.id.btAPI);
            cmbPiloto = findViewById(R.id.tiPiloto);
            cmbTemporada = findViewById(R.id.tiTemporada);
            txtCarrera = findViewById(R.id.tiCarrera);
            txtImpresion = findViewById(R.id.tvImpresion);

            int[] iconos = {R.drawable.logopiloto, R.drawable.logo_max, R.drawable.logo_leclerc,
                    R.drawable.logo_checo, R.drawable.logo_russel, R.drawable.logo_sains,
                    R.drawable.logo_hamilton, R.drawable.logo_norris, R.drawable.logo_ocon,
                    R.drawable.logo_alonso, R.drawable.logo_botas, R.drawable.logo_magnussen,
                    R.drawable.logo_gasly, R.drawable.logo_stroll, R.drawable.logo_tsunoda,
                    R.drawable.logo_guanyu, R.drawable.logo_albon, R.drawable.logo_alphavries,
                    R.drawable.logo_hassnico, R.drawable.logo_piastri, R.drawable.logo_logan,
                    R.drawable.logopiloto, R.drawable.logopiloto};

            String[] pilotos = {"Piloto", "Max Verstappen", "Charles Leclerc", "Sergio Perez",
                    "George Russell", "Carlos Sainz", "Lewis Hamilton", "Lando Norris",
                    "Esteban Ocon", "Fernando Alonso", "Valteri Bottas", "Kevin Magnussen",
                    "Pierre Gasly", "Lance Stroll", "Yuki Tsunoda", "Zhou Guanyu", "Alexander Albon",
                    "Nyck de Vries", "Nico Hulkenberg", "Oscar Piastri", "Logan Sargeant",
                    "Nicholas Latifi", "Daniel Ricciardo"};

            String[] temporada = {"Temporada", "2023", "2022"};
            int[] iconosTemporada = {R.drawable.letra_t, R.drawable.logo_2022, R.drawable.logo_2023};

            AdaptadorSpinner adapter = new AdaptadorSpinner(this, iconos, pilotos);
            AdaptadorSpinner adapter2 = new AdaptadorSpinner(this, iconosTemporada, temporada);
            cmbPiloto.setAdapter(adapter);
            cmbTemporada.setAdapter(adapter2);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG).show();
        }

        btnConeccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temporadaS, piloto;
                int carrera, temporada;

                nombrePiloto = cmbPiloto.getSelectedItem().toString();
                numeroTemporada = cmbTemporada.getSelectedItem().toString();

                temporada = Integer.parseInt(numeroTemporada);
                ProcesarDatos mandarDatos = new ProcesarDatos();
                mandarDatos.setNombrePiloto(nombrePiloto);
                piloto = mandarDatos.obtnerDriverId();
                carrera = Integer.parseInt(txtCarrera.getText().toString());

                ErgastAPI.getDriverResults(splashCarga.this, "perez", 2023, 3,
                        new ErgastAPI.ErgastResponseListener() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                try {
                                    // Obtener datos del piloto
                                    JSONArray results = response.getJSONObject("MRData").
                                            getJSONObject("RaceTable").getJSONArray("Races").
                                            getJSONObject(0).getJSONArray("Results");
                                    JSONObject driver = results.getJSONObject(0).
                                            getJSONObject("Driver");
                                    String position = results.getJSONObject(0).getString("position");
                                    String points = results.getJSONObject(0).getString("points");
                                    String driverName = driver.getString("givenName") + " " +
                                            driver.getString("familyName");
                                    String nationality = driver.getString("nationality");

                                    // Obtener datos de la carrera
                                    JSONArray results2 = response.getJSONObject("MRData").
                                            getJSONObject("RaceTable").getJSONArray("Races");
                                    JSONObject firstResult = results2.getJSONObject(0);
                                    String raceName = firstResult.getString("raceName");
                                    String circuitName = firstResult.getJSONObject("Circuit").
                                            getString("circuitName");
                                    String locality = firstResult.getJSONObject("Circuit").
                                            getJSONObject("Location").getString("locality");
                                    String country = firstResult.getJSONObject("Circuit").
                                            getJSONObject("Location").getString("country");

                                    // Mostrar los datos del piloto en el TextView
                                    String nombrePiloto = driverName;
                                    String nacionalidadPiloto = nationality;
                                    String resultado = "Datos del piloto:\n" + "Nombre PIoto: " +
                                            driverName + "\n" + "Nacionalidad: " + nationality +
                                            "\n" + "Posicion final: " + position + "\n" + "" +
                                            "Puntos Sumados: " + points + "\n" + "Datos de la carrera: " +
                                            "\n" + "Nombre de la carrera: "
                                            + raceName + "\n" + "Nombre del circuito: " +
                                            circuitName + "\n" + "Localidad: " + locality + "\n" + "Pais: "
                                            + country;

                                    txtImpresion.setText(resultado);
                                } catch (JSONException e) {
                                    txtImpresion.setText("Error al analizar la respuesta JSON");
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(String message) {
                                Toast.makeText(getApplicationContext(), message.toString().trim(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


        // Crea un objeto de tipo ErgastAPI.ErgastResponseListener para manejar la respuesta de la API
        listener = new ErgastAPI.ErgastResponseListener() {
            @Override
            public void onSuccess(JSONObject response) {

            }

            @Override
            public void onError(String message) {
                Toast.makeText(getApplicationContext(), message.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        };
    }

}