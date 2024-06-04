package com.example.proyectotfg;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PARKING_UTIL {
    RequestQueue requestQueue;
    Map<String, String> plazasDisponibles = new HashMap<>();

    public void obtenerEstadoPlazaPlanta(Context context, String numPlanta, final PARKING_USUARIOS_P1 activity) {
        String url = "http://192.168.227.1/bbdd_tfg/obtener_plazas.php?numero_planta=" + numPlanta;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject plaza = response.getJSONObject(i);
                                String numPlaza = plaza.getString("num_plaza");
                                String disponible = plaza.getString("disponible");
                                plazasDisponibles.put(numPlaza, disponible);
                            }
                            activity.updateUIWithPlazas(plazasDisponibles);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error al procesar la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }
    public void obtenerEstadoPlazaPlanta2(Context context, String numPlanta, final PARKING_USUARIOS_P2 activity) {
        String url = "http://192.168.227.1/bbdd_tfg/obtener_plazas.php?numero_planta=" + numPlanta;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject plaza = response.getJSONObject(i);
                                String numPlaza = plaza.getString("num_plaza");
                                String disponible = plaza.getString("disponible");
                                plazasDisponibles.put(numPlaza, disponible);
                            }
                            activity.updateUIWithPlazas(plazasDisponibles);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error al procesar la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }
    public void obtenerEstadoPlazaPlanta3(Context context, String numPlanta, final PARKING_USUARIOS_P3 activity) {
        String url = "http://192.168.227.1/bbdd_tfg/obtener_plazas.php?numero_planta=" + numPlanta;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject plaza = response.getJSONObject(i);
                                String numPlaza = plaza.getString("num_plaza");
                                String disponible = plaza.getString("disponible");
                                plazasDisponibles.put(numPlaza, disponible);
                            }
                            activity.updateUIWithPlazas(plazasDisponibles);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error al procesar la respuesta JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public void obtenerEstadoPlazaPlantaAdmin1(Context context, String numPlanta, final PARKING_ADMIN_P1 activity) {
        String url = "http://192.168.227.1/bbdd_tfg/obtener_plazas.php?numero_planta=" + numPlanta;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject plaza = response.getJSONObject(i);
                        String numPlaza = plaza.getString("num_plaza");
                        String disponible = plaza.getString("disponible");
                        plazasDisponibles.put(numPlaza, disponible);
                    }
                    activity.updateUIWithPlazas(plazasDisponibles);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error al procesar la respuesta JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public void obtenerEstadoPlazaPlantaAdmin2(Context context, String numPlanta, final PARKING_ADMIN_P2 activity) {
        String url = "http://192.168.227.1/bbdd_tfg/obtener_plazas.php?numero_planta=" + numPlanta;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject plaza = response.getJSONObject(i);
                        String numPlaza = plaza.getString("num_plaza");
                        String disponible = plaza.getString("disponible");
                        plazasDisponibles.put(numPlaza, disponible);
                    }
                    activity.updateUIWithPlazas(plazasDisponibles);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error al procesar la respuesta JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public void obtenerEstadoPlazaPlantaAdmin3(Context context, String numPlanta, final PARKING_ADMIN_P3 activity) {
        String url = "http://192.168.227.1/bbdd_tfg/obtener_plazas.php?numero_planta=" + numPlanta;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject plaza = response.getJSONObject(i);
                        String numPlaza = plaza.getString("num_plaza");
                        String disponible = plaza.getString("disponible");
                        plazasDisponibles.put(numPlaza, disponible);
                    }
                    activity.updateUIWithPlazas(plazasDisponibles);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error al procesar la respuesta JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }
}
