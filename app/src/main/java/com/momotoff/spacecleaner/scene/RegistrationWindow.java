package com.momotoff.spacecleaner.scene;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
        if(fieldName.getText().toString().isEmpty() || fieldPassword.getText().toString().isEmpty()) {
            Toast.makeText(coreFW.getApplication(), coreFW.getString(R.string.txtFillToast), Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(fieldName.getText().toString(), fieldPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (!task.isSuccessful())
                {
                    Toast.makeText(coreFW.getApplication(), coreFW.getString(R.string.txtInvalidInputToast), Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = fieldName.getText().toString().substring(0, fieldName.getText().toString().indexOf('@'));
                Toast.makeText(coreFW.getApplication(), coreFW.getString(R.string.txtYouAreLoggedToast) + name, Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("", "User: " + fieldName.getText() + ", pass: " + fieldPassword.getText());
        this.setVisibility(View.GONE);
    }
}
