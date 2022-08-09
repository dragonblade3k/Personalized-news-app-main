package com.example.thyme;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.thyme.Model.Articles;
import com.example.thyme.Model.Recommended;
import com.example.thyme.Model.business;
import com.example.thyme.Model.health;
import com.example.thyme.Model.science;
import com.example.thyme.Model.technology;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendedDet extends AppCompatActivity {

    RecyclerView recyclerView;
    final String API_KEY = "api key";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_det);


        recyclerView = findViewById(R.id.recyclerViewRecommended);
        swipeRefreshLayout = findViewById(R.id.swipeRefershRecommended);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String country = getCountry();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson(country,API_KEY);
            }
        });

        retrieveJson(country,API_KEY);


    }


    public void retrieveJson(String country, String apiKey) {

        swipeRefreshLayout.setRefreshing(true);



        final int I_sci=0, I_tech=0, I_health=0, I_buss=0;



        Call<Recommended> call = ApiClient.getInstance().getApi().getRecommended(country,apiKey);
        call.enqueue(new Callback<Recommended>() {
            @Override
            public void onResponse(Call<Recommended> call, Response<Recommended> response) {
                if(response.isSuccessful() && response.body().getArticles() != null) {
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(RecommendedDet.this,articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Recommended> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(RecommendedDet.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public String getCountry() {

        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }

}
