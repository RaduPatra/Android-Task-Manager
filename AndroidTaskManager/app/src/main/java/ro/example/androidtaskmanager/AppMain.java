package ro.example.androidtaskmanager;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import ro.example.androidtaskmanager.utils.Constants;

public class AppMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            Fragment selectedFragment = new TasksFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,
                    selectedFragment).commit();
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new TasksFragment();
                        break;
                    case R.id.nav_rewards:
                        selectedFragment = new RewardsFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,
                        selectedFragment).commit();
                return true;
            };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutOption:
                logoutOption();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutOption() {
        //clears displayed data prefs and go back to login

        SharedPreferences preferences = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.SESSION_DATA, "");
        editor.apply();
        Intent registerScreen = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(registerScreen);
    }
}
