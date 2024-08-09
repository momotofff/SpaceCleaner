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
import com.momotoff.spacecleaner.utilities.Save;

public class RegistrationWindow extends LinearLayout
{
    private CoreFW coreFW;
    private Save save;
    private EditText fieldName;
    private EditText fieldPassword;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference ref;
    private FirebaseDatabase database;


    public RegistrationWindow(CoreFW coreFW, Save save)
    {
        super(coreFW.getApplication());
        this.coreFW = coreFW;
        this.save = save;
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
        if (checkInitialize(save))
            return;

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
    private boolean checkInitialize(Save save)
    {
        if (save.getLogoPass() != null)
        {
            Application app = coreFW.getApplication();
            Toast.makeText(app, coreFW.getString(R.string.txtYouAreLoggedToast) + " " + save.getLogoPass().get("login"), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
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
            save.setLogoPass(username, password);
            save.save(coreFW.getSharedPreferences());
        });

        this.setVisibility(View.GONE);
    }
}
