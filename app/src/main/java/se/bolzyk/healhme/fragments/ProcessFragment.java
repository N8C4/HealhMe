package se.bolzyk.healhme.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.Set;

import me.itangqi.waveloadingview.WaveLoadingView;
import se.bolzyk.healhme.R;

/**
 * Created by andreasbolzyk on 2016-03-01.
 */
public class ProcessFragment  extends Fragment {


    private Set<String> listArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_process, container, false);


        WaveLoadingView mWaveLoadingView1 = (WaveLoadingView) rootView.findViewById(R.id.waveLoadingView1);
        mWaveLoadingView1.setProgressValue(80);

        WaveLoadingView mWaveLoadingView2 = (WaveLoadingView) rootView.findViewById(R.id.waveLoadingView2);
        mWaveLoadingView2.setProgressValue(40);

        loadData();

        return rootView;
    }


    private void loadData() {

        SharedPreferences sp = getContext().getSharedPreferences("db", 0);
        listArray = sp.getStringSet("set", new HashSet<String>());


        Log.d("Set", listArray.toString());
        //Log.d("ALL", sp.getAll().toString());
    }
}
