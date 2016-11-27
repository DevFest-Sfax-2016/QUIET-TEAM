package com.it_skills.ramzi.faamily.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.it_skills.ramzi.faamily.R;

/**
 * Created by ramzi on 26/11/16.
 */

public class SplashFragment3 extends Fragment {

    private TextView mTextview;

    public static SplashFragment3 newInstance() {
        SplashFragment3 fragment = new SplashFragment3();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_splash_3, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        mTextview =  (TextView) view.findViewById(R.id.splach_3_title);
        mTextview.setText("Trouver l localisation de votre ami/e ou b1 famille avec Map ");
    }
}