package ro.example.androidtaskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ro.example.androidtaskmanager.utils.Constants;

public class LoginActivity extends AppCompatActivity {


    private EditText userName;
    private EditText password;
    private Button registerBtn;
    private Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        registerBtn = findViewById(R.id.buttonRegister);
        loginBtn = findViewById(R.id.buttonLogin);

        SharedPreferences preferences = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String display = preferences.getString(Constants.SESSION_DATA, "");

        if (display != "") {
            Intent appScreen = new Intent(LoginActivity.this, AppMain.class);
            startActivity(appScreen);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String userInfo = preferences.getString(user + pass + "Id", "Invalid");
                editor.putString(Constants.SESSION_DATA, userInfo);
                editor.apply();

                if (!userInfo.equals("Invalid")) {
                    Intent appScreen = new Intent(LoginActivity.this, AppMain.class);
                    startActivity(appScreen);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerScreen = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerScreen);
            }
        });

    }
}