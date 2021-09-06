
package com.example.deesha;

import static com.example.deesha.Utils.API.BASEURL;
import static com.example.deesha.Utils.API.IMGURL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.deesha.adapter.tvapdapter;
import com.example.deesha.model.TvModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<TvModel> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    private tvapdapter tvapdapter;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // SetDialog();
        SetRecyclerView();
        Requestcall();




    }

    /*private void SetDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("loadiing.....");
        dialog.setCancelable(false);
        dialog.show();}
*/


    private void Requestcall() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                BASEURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String backdrop_path = IMGURL+object.getString("backdrop_path");

                                Log.e("rajj",backdrop_path);
                                String first_air_date = object.getString("first_air_date");
                                String name = object.getString("name");
                                String vote_average = object.getString("vote_average");
                                String overview = object.getString("overview");
                                String poster_path = IMGURL+object.getString("poster_path");
                                TvModel model =  new TvModel(backdrop_path, first_air_date,name,overview,poster_path,vote_average);
                                arrayList.add(model);

                            }
                            tvapdapter.notifyDataSetChanged();
                            //dialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );

        requestQueue.add(jsonObjectRequest);

    }

    private void SetRecyclerView() {

        recyclerView= findViewById(R.id.recview);
        tvapdapter = new tvapdapter(arrayList , MainActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(tvapdapter);
    }

}