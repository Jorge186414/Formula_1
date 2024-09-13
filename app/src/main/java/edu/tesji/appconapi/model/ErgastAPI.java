package edu.tesji.appconapi.model;

import org.json.JSONObject;
import org.json.JSONException;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.util.Log;

import java.nio.charset.Charset;


public class ErgastAPI {

    private static final String BASE_URL = "https://ergast.com/api/f1/";
    private static final String TAG = "ErgastAPI";

    public interface ErgastResponseListener {
        void onSuccess(JSONObject response);

        void onError(String message);
    }

    public static void getDriverResults(Context context, String driverId, int season, int round, ErgastResponseListener listener) {
        String url = BASE_URL + season + "/" + round + "/drivers/" + driverId + "/results.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJson = new JSONObject(response);
                            listener.onSuccess(responseJson);
                        } catch (JSONException e) {
                            listener.onError("Error al analizar la respuesta JSON");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    String responseBody = new String(error.networkResponse.data, Charset.defaultCharset());
                    Log.e(TAG, responseBody);
                } else {
                    Log.e(TAG, "No se recibió respuesta de la red");
                }
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
