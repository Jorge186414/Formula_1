package edu.tesji.appconapi.model;

import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

public class ProcesarDatos {

    private String driverId;
    private String nombrePiloto;

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public void setNombrePiloto(String nombrePiloto) {
        this.nombrePiloto = nombrePiloto;
    }

    public String obtnerDriverId() {

        switch (nombrePiloto) {
            case "Max Verstappen":
                driverId = "max_verstappen";
                break;
            case "Charles Leclerc":
                driverId = "leclerc";
                break;
            case "Sergio Perez":
                driverId = "perez";
                break;
            case "George Russell":
                driverId = "russell";
                break;
            case "Carlos Sainz":
                driverId = "sainz";
                break;
            case "Lewis Hamilton":
                driverId = "hamilton";
                break;
            case "Lando Norris":
                driverId = "norris";
                break;
            case "Esteban Ocon":
                driverId = "ocon";
                break;
            case "Fernando Alonso":
                driverId = "alonso";
                break;
            case "Valteri Bottas":
                driverId = "bottas";
                break;
            case "Kevin Magnussen":
                driverId = "kevin_magnussen";
                break;
            case "Pierre Gasly":
                driverId = "gasly";
                break;
            case "Lance Stroll":
                driverId = "stroll";
                break;
            case "Yuki Tsunoda":
                driverId = "tsunoda";
                break;
            case "Zhou Guanyu":
                driverId = "zhou";
                break;
            case "Alexander Albon":
                driverId = "albon";
                break;
            case "Nyck de Vries":
                driverId = "de_vries";
                break;
            case "Nico Hulkenberg":
                driverId = "hulkenberg";
                break;
            case "Oscar Piastri":
                driverId = "piastri";
                break;
            case "Logan Sargeant":
                driverId = "sargeant";
                break;
            case "Nicholas Latifi":
                driverId = "latifi";
                break;
            case "Daniel Ricciardo":
                driverId = "ricciardo";
                break;
        }
        return driverId;
    }
}
