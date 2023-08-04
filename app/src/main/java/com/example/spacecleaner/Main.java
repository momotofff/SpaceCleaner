package com.example.spacecleaner;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.spacecleaner.classes.Loading;
import com.example.spacecleaner.scene.MainMenu;
import com.example.spacecleaner.utilities.Save;

public class Main extends CoreFW
{
    Loading loading;
    private final Save save = new Save();

    @Override
    public SceneFW getStartScene()
    {
        loading = new Loading(this, this.getGraphicsFW());
        save.load(getSharedPreferences());

        return new MainMenu(this, save);
    }
}