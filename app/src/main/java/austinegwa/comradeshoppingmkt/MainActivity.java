package austinegwa.comradeshoppingmkt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends Activity {
    private  final String TAG = "FACELOG";
  Button login ;
  CallbackManager mCallbackManager;
  FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();

        login=  findViewById(R.id.login);
        login.setOnClickListener( new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                login.setEnabled(false);
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email","public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());

                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });
        }
    });

    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            // Pass the activity result back to the Facebook SDK
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
   @Override
    public void onStart() {
       super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
       // if(currentUser != null) {
            updateUI();
      // }else{


       // }
    }

    public void updateUI(){
        Intent intent= new Intent(getApplicationContext(), home.class);
        startActivity(intent);
        finish();
    }


    private void handleFacebookAccessToken(AccessToken token){
        Log.d(TAG,"handleFacebookAccessToken:"+token);

        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
        .addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
@Override
public void onComplete(@NonNull Task<AuthResult> task){
        if(task.isSuccessful()){
        // Sign in success, update UI with the signed-in user's information
        Log.d(TAG,"signInWithCredential:success");
        FirebaseUser user=mAuth.getCurrentUser();
            login.setEnabled(true);
        updateUI();
        }else{
        // If sign in fails, display a message to the user.
        Log.w(TAG,"signInWithCredential:failure",task.getException());
        Toast.makeText(MainActivity.this,"Authentication failed.",
        Toast.LENGTH_SHORT).show();

            login.setEnabled(true);
        }

        // ...
        }
        });
        }
        }

         /*
        //handling the app instance id
       Context context= getApplicationContext();
        String iid = InstanceID.getInstance(context).getId();
        String authorizedEntity = "comradeshoppingmkt"; // Project id from Google Developer Console
        String scope = "GCM"; // e.g. communicating using GCM, but you can use any
        // URL-safe characters up to a maximum of 1000, or
        // you can also leave it blank.
        try {
            String token = InstanceID.getInstance(context).getToken(authorizedEntity,scope);
        } catch (IOException e) {
            e.printStackTrace();
        }

        */
