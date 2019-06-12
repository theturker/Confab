package com.example.wansa.gpmessage;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsAdapter extends FragmentPagerAdapter {
    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ChatsFragment chatsFragment=new ChatsFragment();
                //ChatsFragment chatsFragment=new ChatsFragment();
                return chatsFragment;
            case 1:
                beslenmeprogram beslenmeprogram=new beslenmeprogram();
                return beslenmeprogram;
            case 2:
                antrenmanprogram antrenmanprogram=new antrenmanprogram();
                return antrenmanprogram;


                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Günün Motivasyon şarkısı";
            case 1:
                return "Beslenme Programı";
            case 2:
                return "Antrenman Programı";

                default:
                    return null;
        }

    }
}
