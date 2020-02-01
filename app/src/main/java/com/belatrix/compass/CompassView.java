package com.belatrix.compass;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CompassView extends ConstraintLayout {

    ImageView imageViewCompass;

    public CompassView(Context context) {
        super(context);
        inflateView(context);
    }

    private void inflateView(Context context) {
        View v = View.inflate(context, R.layout.compass, this);
        imageViewCompass = v.findViewById(R.id.imageViewCompass);
    }
}