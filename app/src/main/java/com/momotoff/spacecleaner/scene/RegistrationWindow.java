package com.momotoff.spacecleaner.scene;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.my_framework.StaticTextFW;
import com.momotoff.spacecleaner.R;

public class RegistrationWindow extends SceneFW
{
    Rect window = new Rect();
    StaticTextFW name = new StaticTextFW(coreFW.getString(R.string.txtGameOver), new Point(300,200), Color.WHITE, 100);;
    StaticTextFW password = new StaticTextFW(coreFW.getString(R.string.txtGameOver), new Point(300,200), Color.WHITE, 100);;

    public RegistrationWindow(CoreFW coreFW)
    {
        super(coreFW);
        window.left = (int) coreFW.scale.x;
        window.top = (int) coreFW.scale.y;
    }

    @Override
    public void update() {

    }

    @Override
    public void drawing()
    {
        graphicsFW.drawWindow(new Point(window.left,window.top));
        graphicsFW.drawText(name);
        //graphicsFW.drawLine();
        graphicsFW.drawText(password);
        //graphicsFW.drawLine();

    }
}
