package com.momotoff.spacecleaner.scene;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.momotoff.my_framework.CoreFW;
import com.momotoff.spacecleaner.R;

public class RegistrationWindow extends LinearLayout
{
    private CoreFW coreFW;
    private EditText fieldName;
    private EditText fieldPassword;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference ref;
    private FirebaseDatabase database;

    public RegistrationWindow(CoreFW coreFW)
    {
        super(coreFW.getApplication());
        this.coreFW = coreFW;
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        initialize();
    }

    public RegistrationWindow(Context context)
    {
        super(context);
    }

    private void initialize()
    {
        LinearLayout.LayoutParams windowParam = new LinearLayout.LayoutParams(coreFW.getDisplaySize().x / 2, WindowManager.LayoutParams.WRAP_CONTENT);
        windowParam.setMargins(20,20,20,20);

        LinearLayout window = new LinearLayout(coreFW.getApplication());
        window.setBackgroundColor(Color.WHITE);
        window.setOrientation(LinearLayout.VERTICAL);

        TextView name = new TextView(coreFW.getApplication());
        name.setText(coreFW.getString(R.string.txtUserName));
        name.setLayoutParams(windowParam);
        window.addView(name);

        fieldName = new EditText(coreFW.getApplication());
        fieldName.setLayoutParams(windowParam);
        window.addView(fieldName);

        TextView password = new TextView(coreFW.getApplication());
        password.setText(coreFW.getString(R.string.txtPassword));
        password.setLayoutParams(windowParam);
        window.addView(password);

        fieldPassword = new EditText(coreFW.getApplication());
        fieldPassword.setLayoutParams(windowParam);
        window.addView(fieldPassword);

        Button buttonNext = new Button(coreFW.getApplication());
        buttonNext.setText(coreFW.getString(R.string.txtNext));
        buttonNext.setLayoutParams(windowParam);
        buttonNext.setOnClickListener((View view) -> onNextClick());
        window.addView(buttonNext);

        this.setLayoutParams(new LinearLayout.LayoutParams(coreFW.getDisplaySize().x, coreFW.getDisplaySize().y));
        this.setGravity(Gravity.CENTER);
        this.addView(window);
    }

    private void onNextClick()
    {
        Application app = coreFW.getApplication();

        if (fieldName.getText().toString().isEmpty() || fieldPassword.getText().toString().isEmpty())
        {
            Toast.makeText(app, coreFW.getString(R.string.txtFillToast), Toast.LENGTH_SHORT).show();
            return;
        }

        String username = fieldName.getText().toString();
        String password = fieldPassword.getText().toString();

        // TODO: We have to check if we are logged in already
        firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(task -> {
            if (!task.isSuccessful())
            {
                if (task.getException() instanceof com.google.firebase.auth.FirebaseAuthUserCollisionException)
                    // TODO: We have log in if account exists: firebaseAuth.signInWithEmailAndPassword()
                    // TODO: And better to log in first, before creation attempt
                    Toast.makeText(app, "Такой пользователь уже есть", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(app, coreFW.getString(R.string.txtInvalidInputToast), Toast.LENGTH_SHORT).show();

                return;
            }

            Toast.makeText(app, coreFW.getString(R.string.txtYouAreLoggedToast) + " " + username, Toast.LENGTH_SHORT).show();
        });

        this.setVisibility(View.GONE);
    }
}
