package com.eduid.EduIdApp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.eduid.EduIdApp.controller.ActivitiesManager;
import com.eduid.EduIdApp.R;
import com.eduid.EduIdApp.controller.Profile.*;
import com.eduid.EduIdApp.model.EduIdDB;

import dmax.dialog.SpotsDialog;


/**
 * Created by usi on 14.04.16.
 */
public class LoginActivity extends Activity {

    private LoginManagement loginManagement;

    private EditText editTextEmail;
    private EditText editTextPass;
    private Button showPassButton;
    private ImageView barEmail, barPassword;
    private CheckBox rememberCheckBox;
    private AlertDialog mDialog;

    private Boolean saveThisAccount;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set Shared Preferences to check saved username
        sharedPref = this.getPreferences(MODE_PRIVATE);


        loginManagement = new LoginManagement(this.getApplicationContext());

        editTextEmail = findViewById(R.id.emailEditText);
        editTextPass = findViewById(R.id.passwordEditText);

        showPassButton = findViewById(R.id.hideShowPassword);
        showPassButton.setTag(R.drawable.ico_eye_hidden);

        barEmail = findViewById(R.id.inputBarEmail);
        barPassword = findViewById(R.id.inputBarPass);

        rememberCheckBox = findViewById(R.id.rememberCheckBox);

        rememberCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Log.d("EDUID", "Check button status: ON!");
                    saveThisAccount = true;
                } else {
                    //Log.d("EDUID", "Check button status: OFF!");
                    saveThisAccount = false;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.clear().apply();
                }
            }
        });

        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    barEmail.setBackgroundResource(R.color.eduIdBlue);
                }else{
                    barEmail.setBackgroundResource(R.color.eduIdGray);
                }
            }
        });

        editTextPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    barPassword.setBackgroundResource(R.color.eduIdBlue);
                }else {
                    barPassword.setBackgroundResource(R.color.eduIdGray);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        /**
         * Check if username saved and available
         */
        String saveUsername = sharedPref.getString(getString(R.string.rememberMyAccountKey), getString(R.string.rememberMyAccountDefault));
        if(saveUsername != getString(R.string.rememberMyAccountDefault)){
            editTextEmail.setText(saveUsername);
            rememberCheckBox.setChecked(true);
        }


        /**
         * Login button click
         */
        Button loginButton = (Button) this.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**Loading indicator
                */
                mDialog = new SpotsDialog(LoginActivity.this);
                mDialog.show();

                /**
                 * Get data of user
                 */

                final String username = ((EditText) LoginActivity.this.findViewById(R.id.emailEditText)).getText().toString();
                final String password = ((EditText) LoginActivity.this.findViewById(R.id.passwordEditText)).getText().toString();

                /**
                 * Save the username in sharedPreference if the checkBox ticked
                 */
                SharedPreferences.Editor  editor = sharedPref.edit();
                editor.putString(getString(R.string.rememberMyAccountKey), username);
                editor.apply();

                /**
                 * Authenticate
                 */
                new Authentication(LoginActivity.this.getApplicationContext()).authenticate(username, password, new AuthenticationCallback() {
                    @Override
                    public void onAuthenticationFinish(boolean authenticateSuccessful) {

                        Context ctx = LoginActivity.this.getApplicationContext();
                        mDialog.dismiss();
                        if(authenticateSuccessful){

                            /**
                             * Login successful -> Verify profile -> Open home activity
                             */
//                            new UserProfileVerification(LoginActivity.this.getApplicationContext()).checkUserProfile(new UserProfileVerification.UserProfileVerificationCallback() {
//                                @Override
//                                public void onUserProfileVerificationFinish(boolean verified) {
//                                    if(verified){
//                                        ActivitiesManager.startHomeActivity(LoginActivity.this.getApplicationContext());
//                                        LoginActivity.this.finish();
//
////                                        String name = new EduIdDB(LoginActivity.this.getApplicationContext()).getUserParam("name");
////                                        Toast.makeText(LoginActivity.this.getApplicationContext(), "Profile checked success! Welcome " + name, Toast.LENGTH_LONG).show();
//                                    }
//                                    else{
//                                        Toast.makeText(LoginActivity.this.getApplicationContext(), "Error on check profile, logout!", Toast.LENGTH_LONG).show();
//                                        ActivitiesManager.startLoginActivity(LoginActivity.this.getApplicationContext());
//                                    }
//                                }
//                            });
                            /**
                             * Check the intent, open SelectService if the app is invoked by third party app(ex. Moodle)
                             */
                            Log.d("EDUID" , "LOGIN SUCCESFULL : " + getIntent().getAction());
                            if(getIntent().getAction() != Intent.ACTION_VIEW) {
                                ActivitiesManager.startHomeActivity(LoginActivity.this.getApplicationContext());
                                LoginActivity.this.finish();
                            } else {
                                ActivitiesManager.startSelectServiceActivity(LoginActivity.this.getApplicationContext(), getIntent());
                            }

                        }
                        else{
                            /**
                             * Login error
                             */
                            Toast.makeText(ctx, ctx.getString(R.string.loginError), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                /**
                 * END Button OnClickListener
                */
            }
        });

        //Check intent if it comes from launcher or third party app
        String intentAction = getIntent().getAction();

        /**
         * If the user is logged, go on home page
         */
        if(loginManagement.isLogged() && intentAction != Intent.ACTION_VIEW){
            ActivitiesManager.startHomeActivity(this.getApplicationContext());
        }else if( loginManagement.isLogged() && intentAction == Intent.ACTION_VIEW ) {
            ActivitiesManager.startSelectServiceActivity(this.getApplicationContext(), getIntent());
        }

    }

    @Override
    public void onBackPressed() {
        /**
         * Disable back button on activity_login view
         */
    }

    public void hideShowPass(View v){
        Button btn = (Button) v;
        Log.d("EDUID", "button background..." + btn.getBackground().toString());
        if( (Integer) btn.getTag() == R.drawable.ico_eye_hidden ){
            //Toast.makeText(this, "show the pass", Toast.LENGTH_SHORT).show();
            editTextPass.setInputType(InputType.TYPE_CLASS_TEXT);
            btn.setBackgroundResource(R.drawable.ico_eye);
            btn.setScaleX(0.75f);
            btn.setScaleY(0.55f);
            btn.setTag(R.drawable.ico_eye);

        }else if ( (Integer)btn.getTag() == R.drawable.ico_eye ){

            //Toast.makeText(this, "hide the pass", Toast.LENGTH_SHORT).show();
            editTextPass.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
            btn.setBackgroundResource(R.drawable.ico_eye_hidden);
            btn.setScaleY(0.8f);
            btn.setScaleX(0.8f);
            btn.setTag(R.drawable.ico_eye_hidden);
        }
    }

    public void forgotPassword(View v){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
        startActivity(browserIntent);
    }


}
