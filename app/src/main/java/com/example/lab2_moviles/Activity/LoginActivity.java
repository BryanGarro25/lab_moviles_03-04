package com.example.lab2_moviles.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
*/
import com.example.lab2_moviles.R;
import com.example.lab2_moviles.LogicaNegocio.Usuario;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity{
    private Usuario usuario;
    private EditText EmailView;
    private EditText PasswordView;
    private View ProgressView;
    private View LoginFormView;
    private UserLoginTask AuthTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EmailView = (EditText) findViewById(R.id.username);
        PasswordView = (EditText) findViewById(R.id.password);
        PasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int id, KeyEvent event) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    tryLogin();
                    return true;
                }
                return false;
            }
        });
        Button signButton = (Button) findViewById(R.id.login);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    tryLogin();
            }
        });
    }
    private void tryLogin(){
        this.EmailView.setError(null);
        this.PasswordView.setError(null);
        // se obtienen los valores de la vista
        String email = EmailView.getText().toString();
        String password = PasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            PasswordView.setError(getString(R.string.invalid_password));
            focusView = PasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            EmailView.setError(getString(R.string.invalid_username));
            focusView = EmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            EmailView.setError(getString(R.string.invalid_email));
            focusView = EmailView;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }
        else{
            AuthTask = new UserLoginTask(email, password);
            AuthTask.execute((Void) null);
        }

    }
    private boolean isPasswordValid(String password){
        return password.length() > 4;
    }

    private boolean isEmailValid(String email){
            return email.contains("@");
    }
    private Usuario getUsuario(){
        Usuario user = new Usuario("@lab02", "lab02");
        return user;
    }
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            Usuario user = getUsuario();
            if (user.getCorreo().equals(mEmail)) {
                return user.getContraseña().equals(mPassword);
            }
            return false;
        }
        @Override
        protected void onPostExecute(final Boolean success) {
            AuthTask = null;
            //showProgress(false);

            if (success) {
                finish();
                //putting user on shared preferences
                //prefs.edit().putString((getString(R.string.preference_user_key)), usuario.getPrivilegio()).apply();
                Intent intent = new Intent(LoginActivity.this, NavDrawerActivity.class);

                LoginActivity.this.startActivity(intent);
            } else {
                PasswordView.setError(getString(R.string.error_incorrect_password));
                PasswordView.requestFocus();
            }
        }
        @Override
        protected void onCancelled() {
            AuthTask = null;
            //showProgress(false);
        }
    }
}
