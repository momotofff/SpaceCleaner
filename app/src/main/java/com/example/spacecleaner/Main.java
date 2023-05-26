package com.example.spacecleaner;

import  com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.spacecleaner.classes.Loading;
import com.example.spacecleaner.scene.MainMenuScene;

public class Main extends CoreFW
{
    Loading loading = new Loading(this, this.getGraphicsFW());
    public SceneFW getStartScene()
    {
        return new MainMenuScene(this);
    }
}