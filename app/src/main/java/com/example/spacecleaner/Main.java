package com.example.spacecleaner;

import  com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.spacecleaner.classes.Loading;
import com.example.spacecleaner.classes.Manager;
import com.example.spacecleaner.scene.MainMenu;

public class Main extends CoreFW
{
    Loading loading;

    public SceneFW getStartScene()
    {
        loading = new Loading(this, this.getGraphicsFW());

        return new MainMenu(this);
    }
}