package com.example.communityhero;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    EditText nameField;
    EditText passField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameField = findViewById(R.id.name);
        passField = findViewById(R.id.pass);

        //If already logged in, go directly to home activity.
        ParseUser curr = ParseUser.getCurrentUser();
        if(curr != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    // Takes you to signup page
    public void noAcc(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    //Performs the login function
    public void login(View view) {
        ParseUser.logInInBackground(nameField.getText().toString(), passField.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if(e == null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "LOGGED IN SUCCESSFULLY", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundColor(Color.GREEN);
                    toast.show();

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundColor(Color.RED);
                    toast.show();
                    e.printStackTrace();
                }
            }
        });
    }
}
