package com.rowdystudio.footballworldcup;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchesFragment extends Fragment {

    ArrayList<HashMap<String,String> > arrayList = new ArrayList<>();
    HashMap< String, String> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_matches, container, false);

        ListView listView = view.findViewById(R.id.listView);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);





        String url = "https://footballworldcup.crisisstudio.com/matches.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        progressBar.setVisibility(View.GONE);

                        // connect with custom adapter
                        myadapter myadapter = new myadapter();
                        listView.setAdapter(myadapter);


                        try {


                            for ( int x=1; x<=response.length(); x++){


                                JSONArray jsonArray = response.getJSONArray(""+x );

                                for ( int y=0; y<jsonArray.length(); y++ ){

                                    JSONObject jsonObject = jsonArray.getJSONObject(y);
                                    String group = jsonObject.getString("group");
                                    String date = jsonObject.getString("date");
                                    String round = jsonObject.getString("round");
                                    String t1f = jsonObject.getString("t1f");
                                    String t2f = jsonObject.getString("t2f");
                                    String goal = jsonObject.getString("goal");
                                    String t1 = jsonObject.getString("t1");
                                    String t2 = jsonObject.getString("t2");
                                    String time = jsonObject.getString("time");
                                    String tw = jsonObject.getString("tw");
                                    String stadium = jsonObject.getString("stadium");


                                    hashMap = new HashMap<>();
                                    hashMap.put("group",group);
                                    hashMap.put("date", date);
                                    hashMap.put("round", round);
                                    hashMap.put("t1f", t1f);
                                    hashMap.put("t2f", t2f);
                                    hashMap.put("goal", goal);
                                    hashMap.put("t1", t1);
                                    hashMap.put("t2", t2);
                                    hashMap.put("time", time);
                                    hashMap.put("tw", tw);
                                    hashMap.put("stadium", stadium);
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


    public class  myadapter extends BaseAdapter{

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
            View myView = layoutInflater.inflate(R.layout.matchitem, null);


            TextView grouptxt, datetxt, roundtxt, goalList, t1_name, t2_name, match_time;
            TextView stadium_name, teamWon;
            CircleImageView t1_flag, t2_flag;

            grouptxt = myView.findViewById(R.id.grouptxt);
            datetxt = myView.findViewById(R.id.datetxt);
            roundtxt = myView.findViewById(R.id.roundtxt);
            t1_flag = myView.findViewById(R.id.t1_flag);
            t2_flag = myView.findViewById(R.id.t2_flag);
            goalList = myView.findViewById(R.id.goalList);
            t1_name = myView.findViewById(R.id.t1_name);
            t2_name = myView.findViewById(R.id.t2_name);
            match_time = myView.findViewById(R.id.match_time);
            stadium_name = myView.findViewById(R.id.stadium_name);
            teamWon = myView.findViewById(R.id.teamWon);




            HashMap<String,String> hashMap = arrayList.get(position);

            String group = hashMap.get("group");
            String date = hashMap.get("date");
            String round = hashMap.get("round");
            String t1f = hashMap.get("t1f");
            String t2f = hashMap.get("t2f");
            String goal = hashMap.get("goal");
            String t1 = hashMap.get("t1");
            String t2 = hashMap.get("t2");
            String time = hashMap.get("time");
            String stadium = hashMap.get("stadium");
            String tw = hashMap.get("tw");


            grouptxt.append(group);
            datetxt.append(date);
            roundtxt.append(round);
            goalList.append(goal);
            t1_name.append(t1);
            t2_name.append(t2);
            match_time.append(time);
            stadium_name.append(stadium);
            teamWon.append(tw);



            // t1 flag
            Picasso.get()
                    .load(t1f)
                    .into(t1_flag);

            // t2 flag
            Picasso.get()
                    .load(t2f)
                    .into(t2_flag);



            return myView;
        }
    }


}