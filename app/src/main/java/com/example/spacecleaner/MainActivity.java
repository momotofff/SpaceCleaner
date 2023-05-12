package com.example.spacecleaner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.my_framework.LoopFW;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoopFW loopFW = new LoopFW();
        loopFW.startGame();
    }
}