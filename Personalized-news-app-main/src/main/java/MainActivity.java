package com.example.thyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.thyme.Model.Articles;
import com.example.thyme.Model.headlines;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.thyme.R.id.Mainframe;
import static com.example.thyme.R.id.Mainframe;
import static com.example.thyme.R.id.bottomNavigationView;
import static com.example.thyme.R.id.nav_controller_view_tag;

public class MainActivity extends AppCompatActivity {

   /* RecyclerView recyclerView;
    final String API_KEY = "api key";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>(); */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        FrameLayout frameLayout = findViewById(R.id.Mainframe);

        setFragment(new home());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        setFragment(new home());
                        return true;

                    case R.id.personal:
                        setFragment(new personal());
                        return true;

                    case R.id.menu:
                        setFragment(new menu());
                        return true;

                    default:
                        return false;

                }
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.Mainframe,fragment);
        fragmentTransaction.commit();

    }

}
