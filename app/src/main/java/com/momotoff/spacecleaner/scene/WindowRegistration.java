package com.momotoff.spacecleaner.scene;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.momotoff.my_framework.CoreFW;

@SuppressLint("ViewConstructor")
public class WindowRegistration extends View
{
    private CoreFW coreFW;
    public LinearLayout view;
    EditText fieldName;
    EditText fieldPassword;
    Button buttonNext;

    public WindowRegistration(CoreFW coreFW)
    {
        super(coreFW.getApplication());
        this.coreFW = coreFW;
        view = initializeWindowRegistration();

    }

    public void update()
    {
        View.OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (view.getId() == buttonNext.getId())
                {
                    setWindowRegistrationVisibility(View.GONE);
                }
            }
        };
    }


    public void setWindowRegistrationVisibility(int visibility)
    {

    }

    public boolean checkRegistration()
    {
        return true;
    }

    private LinearLayout initializeWindowRegistration()
    {
        LinearLayout.LayoutParams windowParam = new LinearLayout.LayoutParams(coreFW.getDisplaySize().x / 2, WindowManager.LayoutParams.WRAP_CONTENT);
        windowParam.setMargins(20,20,20,20);

        LinearLayout window = new LinearLayout(coreFW.getApplication());
        window.setBackgroundColor(Color.WHITE);
        window.setOrientation(LinearLayout.VERTICAL);

        TextView name = new TextView(coreFW.getApplication());
        name.setText("Name");
        name.setLayoutParams(windowParam);
        window.addView(name);

        fieldName = new EditText(coreFW.getApplication());
        fieldName.setLayoutParams(windowParam);
        window.addView(fieldName);

        TextView password = new TextView(coreFW.getApplication());
        password.setText("Password");
        password.setLayoutParams(windowParam);
        window.addView(password);

        fieldPassword = new EditText(coreFW.getApplication());
        fieldPassword.setLayoutParams(windowParam);
        window.addView(fieldPassword);

        buttonNext = new Button(coreFW.getApplication());
        buttonNext.setText("далее");
        buttonNext.setLayoutParams(windowParam);
        window.addView(buttonNext);

        LinearLayout screen = new LinearLayout(coreFW.getApplication());
        screen.setLayoutParams(new LinearLayout.LayoutParams(coreFW.getDisplaySize().x, coreFW.getDisplaySize().y));
        screen.setGravity(Gravity.CENTER);
        screen.addView(window);

        return screen;
    }

}
