package com.momotoff.spacecleaner;

import com.momotoff.my_framework.CoreFW;
import com.momotoff.my_framework.SceneFW;
import com.momotoff.spacecleaner.classes.Loading;
import com.momotoff.spacecleaner.scene.MainMenu;
import com.momotoff.spacecleaner.utilities.Save;

public class Main extends CoreFW
{
    private final Save save = new Save();

    @Override
    public SceneFW getStartScene()
    {
        Loading loading = new Loading(this, this.getGraphicsFW());
        save.loadDistance(getSharedPreferences());
        return MainMenu.createInstance(this, save);
    }
}