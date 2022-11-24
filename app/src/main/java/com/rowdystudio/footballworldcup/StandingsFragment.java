package com.rowdystudio.footballworldcup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class StandingsFragment extends Fragment {

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_standings, container, false);


        ListView listView = view.findViewById(R.id.listView);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);



        String url = "https://footballworldcup.crisisstudio.com/pointtable.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        progressBar.setVisibility(View.GONE);


                        // standings adapter set
                        standings standings = new standings();
                        listView.setAdapter(standings);


                        try {

                            for (int x=1; x<=response.length(); x++){

                                JSONArray jsonArray = response.getJSONArray(""+x);

                                for (int y=1; y<=jsonArray.length(); y++){

                                    JSONObject jsonObject = jsonArray.getJSONObject(y);

                                    String tn = jsonObject.getString("tn");
                                    String tf = jsonObject.getString("tf");
                                    String mp = jsonObject.getString("mp");
                                    String mw = jsonObject.getString("mw");
                                    String md = jsonObject.getString("md");


                                    hashMap = new HashMap<>();
                                    hashMap.put("tn", tn);
                                    hashMap.put("tf", tf);
                                    hashMap.put("mp", mp);
                                    hashMap.put("mw", mw);
                                    hashMap.put("md", md);
                                    arrayList.add(hashMap);

                                }

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(jsonObjectRequest);




        return view;
    }


    public class standings extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.fragment_standings, null);




            return myView;
        }
    }




    public class standing_item extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {



            return null;
        }
    }


}