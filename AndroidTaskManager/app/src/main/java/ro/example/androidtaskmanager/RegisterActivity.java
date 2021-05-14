package ro.example.androidtaskmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ro.example.androidtaskmanager.utils.Constants;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        userName = findViewById(R.id.editTextNewUsername);
        password = findViewById(R.id.editTextNewPassword);
        registerBtn = findViewById(R.id.buttonNewRegister);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUser = userName.getText().toString();
                String newPassword = password.getText().toString();

                makeSharedPrefs(newUser, newPassword);

                Intent loginScreen = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginScreen);
            }
        });
    }

    private void makeSharedPrefs(String newUser, String newPassword) {
        SharedPreferences preferences = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(newUser + newPassword + "Id", newUser);
        editor.apply();
    }
}
