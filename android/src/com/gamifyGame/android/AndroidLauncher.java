package com.gamifyGame.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gamifyGame.gamifyGame;


public class AndroidLauncher extends AndroidApplication {

    private gamifyGame gameProcess;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        gameProcess = new gamifyGame();
        initialize(gameProcess, config);

	}
}
