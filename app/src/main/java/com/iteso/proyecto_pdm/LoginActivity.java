package com.iteso.proyecto_pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static final String UID = "UID";
    Button login;
    EditText correo,pass;
    CallbackManager mCallbackManager;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //FirebaseAuth.getInstance().signOut();
        //LoginManager.getInstance().logOut();
        login  = (Button) findViewById(R.id.activity_login_btn);
        correo = findViewById(R.id.login_activity_email);
        pass = findViewById(R.id.login_activity_pass);
        LoginButton loginButton = findViewById(R.id.login_button_fb);
        SignInButton signInButton = findViewById(R.id.sign_in_button_google);
        loginButton.setReadPermissions("email", "public_profile");
        mCallbackManager = CallbackManager.Factory.create();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("214792827894-11387mc6tjpe3gh7ucfm89bvg4ju5eup.apps.googleusercontent.com")

                //.requestIdToken("214792827894-07np3sfqp6803jf35n7cdnbljbp81otq.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = correo.getText().toString();
                String password = pass.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("login normal", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    login(user.getUid());
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("error login normal", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("tag del login", "facebook:onSuccess:" + loginResult);

                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Log.d("tag del login", "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("tag del login", "facebook:onError", error);
                // ...
            }
        });
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.e("tag:google login", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("tag:google login", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            login(user.getUid());


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("tag:google login", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    private void handleFacebookAccessToken(AccessToken token) {

        Log.d("handle fb", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            /*
                            *
                            * saber si es un nuevo usuario
                            *
                            * */
                            //task.getResult().getAdditionalUserInfo().isNewUser();
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithCredential:success", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            login(user.getUid());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }
    private static final int RC_SIGN_IN = 9001;
    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.e("sigin", "google entro al login");
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.e("tag:google", "Google sign in failed", e);
                // ...
            }
        }
    }

    protected  void login(final String token){
        SharedPreferences sharedPreferences =
                getSharedPreferences(Constants.MAIN_PACKAGE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.TOKEN_PREFERENCE, token);
        editor.apply(); //apply asincrono , commit sincrono*/
        Log.e("usuario_token", token);

        final Map<String, Object> document = new HashMap<>();

        String strDocRef = Constants.USUARIOS + token;

        final DocumentReference documentReference = FirebaseFirestore.getInstance().document(strDocRef);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(! documentSnapshot.contains(UID)){
                    document.put(UID, token);

                    documentReference.set(document).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG);
                            toast1.show();
                        }
                    });
                }
                Toast toast1 = Toast.makeText(getApplicationContext(), "Stored", Toast.LENGTH_LONG);
                toast1.show();
                Intent intent = new Intent(LoginActivity.this, Activity_Dashboard.class);
                startActivity(intent);
            }
        });


    }
}
