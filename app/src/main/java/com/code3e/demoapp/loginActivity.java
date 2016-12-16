package com.code3e.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {

    private final static String TAG="LoginActivity";

    private CallbackManager callbackManager;
    private TwitterLoginButton twitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

callbackManager = CallbackManager.Factory.create();
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);

        LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                // App code
                Log.d(TAG, "onSuccess: ");

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.d(TAG, "onCompleted: "+object.toString());
                                String profileImg = "https://graph.facebook.com/"+loginResult.getAccessToken().getUserId();
                                Log.d(TAG, "onCompleted: "+profileImg);

                                Intent intent = new Intent(loginActivity.this, MenuActivity.class);
                                startActivity(intent);
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, email, gender, birthday, first_name, last_name");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App codef
                Log.d(TAG, "onCancel: ");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(TAG, "onError: ", exception);
            }
        });

        twitterLoginButton = (TwitterLoginButton)findViewById(R.id.twitter_login_button);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, "success: ");
                Intent intent = new Intent(loginActivity.this, MenuActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, "failure: ", exception);
            }
        });
        /*
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent(loginActivity.this, MenuActivity.class);
                startActivity(intent);
            //TODO Login e iniciar intent de menu si login exitoso y finish, o re-intentar
            }
        });
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: Request code: "+requestCode);

        if(FacebookSdk.isFacebookRequestCode(requestCode)){
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        if (TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE == requestCode){
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);

        }


    }
}
