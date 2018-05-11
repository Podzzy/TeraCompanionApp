package com.jjpods.teracompanionapp.Helper;


import android.support.v4.app.FragmentManager;

import com.jjpods.teracompanionapp.Broker;
import com.jjpods.teracompanionapp.Interface.NavigationManager;

/**
 * Created by jjpod on 5/11/2018.
 */

public class FragmentNavigationManager implements NavigationManager {
    private static FragmentNavigationManager mInstance;

    private FragmentManager mFragmentManager;
    private Broker broker;

    public static FragmentNavigationManager getmInstance(Broker broker){
        if(mInstance == null)
            mInstance = new FragmentNavigationManager();
        mInstance.configure(broker);
        return mInstance;
    }

    private void configure(Broker broker) {
        broker = broker;
        mFragmentManager = broker.getSupportFragmentManager();
    }


    @Override
    public void showFragment(String title) {

    }
}
