package com.gamifyGame.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.view.View;
import android.widget.Toast;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.utils.Array;
import com.gamifyGame.gamifyGame;

import java.io.File;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class AndroidLauncher extends AndroidApplication {

    private final String GAMIFY_VERSION = "0.1.0a";
    //Sprivate gamifyGame gameProcess;
    //ActionResolverAndroid actionResolverAndroid;
    private Preferences pref;
    HashMap<String,String> stringPref;
    HashMap<String,Integer> intPref;
    HashMap<String,Boolean> boolPref;

	@Override
	protected void onCreate (Bundle savedInstanceState)
    {
        //Toast.makeText(this,"Comps",Toast.LENGTH_SHORT).show();
		super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        ActionResolverAndroid actionResolverAndroid = new ActionResolverAndroid(this);
        gamifyGame gameProcess = gamifyGame.getGamifyGame(actionResolverAndroid);

        Bundle extras = this.getIntent().getExtras();
        try{String userID = (String) extras.get("ID");}
        catch(Exception e){String userID = "4321";}

        // Make a fake ID, Replace when userID is implemented
        pref = this.getPreferences("Bitfitpref");
        double ID = Math.random()*(Math.pow(10d,15d))%Math.pow(10d,15d)+Math.pow(10d,16d);
        String fakeID = pref.getString("userID",String.valueOf(ID));

        // Replace fakeID with userID when userID is implemented
        pref.putString("userID",fakeID);
        pref.flush();

        AccelAlarm alarm = new AccelAlarm();
        alarm.setPref(pref);
        alarm.setVersion(GAMIFY_VERSION);
        alarm.setAlarm(this, GAMIFY_VERSION, fakeID);

        setContentView(R.layout.loginscreenres);

        // Preferences file stores data for later running of app
        gameProcess.setPref(pref);
        initialize(gameProcess, config);
        /*
        if (stringPref == null){
            stringPref = new HashMap<String, String>();
            intPref = new HashMap<String, Integer>();
            boolPref = new HashMap<String, Boolean>();
        }
        else {
            Set<String> keysS = stringPref.keySet();
            for(Iterator i = keysS.iterator(); i.hasNext();){
                pref.putString((String)i.next(),stringPref.get(i.next()));
            }
            stringPref.clear();
            Set<String> keysI = intPref.keySet();
            for(Iterator i = keysI.iterator(); i.hasNext();){
                pref.putInteger((String)i.next(),intPref.get(i.next()));
            }
            intPref.clear();
            Set<String> keysB = boolPref.keySet();
            for(Iterator i = keysB.iterator(); i.hasNext();){
                pref.putBoolean((String)i.next(),boolPref.get(i.next()));
            }
            boolPref.clear();
            pref.flush();
        }*/

	}

    public Preferences getPref(){
        return pref;
    }

    public HashMap<String, String> getStringPref(){
        return stringPref;
    }
    public HashMap<String, Integer> getIntPref(){
        return intPref;
    }
    public HashMap<String, Boolean> getBoolPref(){
        return boolPref;
    }

}
