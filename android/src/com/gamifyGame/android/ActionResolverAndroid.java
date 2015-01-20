package com.gamifyGame.android;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.gamifyGame.ActionResolver;


public class ActionResolverAndroid implements ActionResolver {
    Handler handler;
    Context context;

    public ActionResolverAndroid(Context context) {
        handler = new Handler();
        this.context = context;
    }

    public void scanAct(final CharSequence text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Zxing.class);
                context.startActivity(intent);
            }
        });
    }

}

