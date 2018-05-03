package theinterview.marion.com.theinterviewapp.Registration;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import theinterview.marion.com.theinterviewapp.R;

public class Registration extends AppCompatActivity implements View.OnClickListener {


    private Button buttonRegister;
    private EditText editTextEmail, editTextPassword;
    private TextView textViewSignIn;
    private ProgressDialog progressDialog;

    //Firebase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buttonRegister = findViewById(R.id.buttonRegister);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword= findViewById(R.id.editTextPassword);
        textViewSignIn=findViewById(R.id.textViewSignIn);
        progressDialog = new ProgressDialog(this);

        //firebase object
        firebaseAuth= FirebaseAuth.getInstance();

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v==buttonRegister){
            registerUser();
        }

        if (v==textViewSignIn){
            //We will add intent later
        }

    }

    private void registerUser() {
        String email= editTextEmail.getText().toString();
        String password= editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_LONG).show();
            return;
        }

        /*
        if validations are done then we are going to show
        progress bar
         */
        progressDialog.setMessage("Registering the user...hang on a second");
        progressDialog.show();

        /*
        To create user in firebase console and to notify when completed
        */
        firebaseAuth.createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //user is registered
                    Toast.makeText(Registration.this,"Registered successfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Registration.this,"Failed to Register..please try again",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
