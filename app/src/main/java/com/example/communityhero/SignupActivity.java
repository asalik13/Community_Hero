package com.example.communityhero;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import static com.parse.Parse.getApplicationContext;

public class SignupActivity extends AppCompatActivity {

    EditText emailField;
    EditText nameField;
    EditText passField;
    EditText repassField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Intent intent = getIntent();

        emailField = findViewById(R.id.email);
        nameField = findViewById(R.id.name);
        passField = findViewById(R.id.pass);
        repassField = findViewById(R.id.repass);
    }

    //Performs the signup function
    public void signup(View view) {
        if(nameField.getText().toString().length() > 0  && passField.getText().toString().length() > 0 && emailField.getText().toString().length() > 0 && passField.getText().toString().equals(repassField.getText().toString())) {
            ParseUser user = new ParseUser();
            user.setEmail(emailField.getText().toString());
            user.setUsername(nameField.getText().toString());
            user.setPassword(passField.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null) {
                        Toast toast = Toast.makeText(getApplicationContext(), "SIGNED UP SUCCESSFULLY", Toast.LENGTH_LONG);
                        View toastView = toast.getView();
                        toastView.setBackgroundColor(Color.GREEN);
                        toast.show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Username or Email already exits or is incorrect", Toast.LENGTH_SHORT);
                        View toastView = toast.getView();
                        toastView.setBackgroundColor(Color.RED);
                        toast.show();
                        e.printStackTrace();
                    }
                }
            });
        }else {
            //If all fields aren't filled correctly
            Toast toast = Toast.makeText(this, "Please fill in all the fields correctly", Toast.LENGTH_SHORT);
            View toastView = toast.getView();
            toastView.setBackgroundColor(Color.RED);
            toast.show();
        }
    }
}
