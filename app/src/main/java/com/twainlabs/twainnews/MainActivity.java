package com.twainlabs.twainnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

//    Button btnLO;
//    FirebaseAuth mAuth;

    private ViewPager newsPager;
    ArticleViewPagerAdapter articleViewPagerAdapter;
    private RequestQueue mQueue;
    private ArrayList<ArticleModel> articleList;
    TabLayout TLnews;
    private Timer timer;
    private int current_position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);
        //final List<ArticleModel> articleList = new ArrayList<>();
        articleList = new ArrayList<>();
        newsPager = findViewById(R.id.vpNewsArticles);
        TLnews = findViewById(R.id.newsTL);

        parseJSON();

//--------------------LOGOUT BUTTON, JUST IN CASE------------------------------
//
//        mAuth = FirebaseAuth.getInstance();
//        btnLO = findViewById(R.id.lgtbtn);
//
//        btnLO.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent y= new Intent(getApplicationContext(), Login.class);
//                startActivity(y);
//                finish();
//            }
//        });
//-----------------------------------------------------------------------------
    }

    private void parseJSON(){
        String url = "https://newsapi.org/v2/everything?domains=wsj.com&apiKey=f1f1686df9b842209b7dc10f5163e2cb";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("articles");

                            for (int i=0; i<5; i++){
                                JSONObject article = jsonArray.getJSONObject(i);
                                JSONObject p = article.getJSONObject("source");

                                String aImg = article.getString("urlToImage");
                                String aHL = article.getString("title");
                                String aS = p.getString("name");
                                String aD = article.getString("publishedAt");

                                articleList.add(new ArticleModel(aImg, aHL, aS, aD.substring(0, 9)));
                            }

                            articleViewPagerAdapter = new ArticleViewPagerAdapter(MainActivity.this, articleList);
                            newsPager.setAdapter(articleViewPagerAdapter);
                            slide();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
        });

        mQueue.add(request);
        TLnews.setupWithViewPager(newsPager);
    }

    private void slide() {
        final Handler handler = new Handler();
        final  Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (current_position == 5)
                    current_position = 0;
                newsPager.setCurrentItem(current_position++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 250, 2500);
    }
}
