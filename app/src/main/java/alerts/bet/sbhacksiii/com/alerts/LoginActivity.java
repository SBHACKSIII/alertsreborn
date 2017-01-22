package alerts.bet.sbhacksiii.com.alerts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logInButton;
    private EditText emailEditText, passwordEditText;
    private TextView signUpTextView, loggingInTextView, guestTextView;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    private Animation shakeAnim;

    private RelativeLayout activity_lagin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            // Start main activity
            startActivity(new Intent(getApplicationContext(), MainActivity.class)
                    .putExtra("userId", firebaseAuth.getCurrentUser().getUid()));
        }

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        logInButton = (Button) findViewById(R.id.loginButton);
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        loggingInTextView = (TextView) findViewById(R.id.loggingInTextView);
        guestTextView = (TextView) findViewById(R.id.guestTextView);
        activity_lagin = (RelativeLayout) findViewById(R.id.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        logInButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
        guestTextView.setOnClickListener(this);
    }

    private void userLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            // Email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            // Stopping the fnx exec further
            return;
        }
        if (TextUtils.isEmpty(password)) {
            // password is empty
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            // Stopping the function execution further
            return;
        }

        hideKeyboard(this);
        progressBar.setVisibility(View.VISIBLE);
        loggingInTextView.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.INVISIBLE);
                        loggingInTextView.setVisibility(View.INVISIBLE);

                        Log.i("Before if", "Before the if clause");

                        if (task.isSuccessful()) {
                            // User successfully registered and logged in
                            // We will start the profile activity here
                            // Making a Toast for now, will change later
                            Toast.makeText(LoginActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class)
                                    .putExtra("userId", firebaseAuth.getCurrentUser().getUid()));
                        } else {
                            // Start shake animation
                            Log.i("Else clause", "In the else clause");
                            //shakeAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
                            //activity_lagin.startAnimation(shakeAnim);
                            Toast.makeText(LoginActivity.this, "Could not register. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == logInButton) {
            userLogin();
        }

        if (view == signUpTextView) {
            startActivity(new Intent(this, SignUpActivity.class));
        }

        if (view == guestTextView) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}