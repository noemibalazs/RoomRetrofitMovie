package com.example.android.roomretrofitmovie.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.roomretrofitmovie.R;
import com.example.android.roomretrofitmovie.fragment.Favorite;
import com.example.android.roomretrofitmovie.fragment.Popular;
import com.example.android.roomretrofitmovie.fragment.TopRated;


public class PagerAdapter extends FragmentPagerAdapter {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private Context mContext;


    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == ZERO){
            return new TopRated();
        } else if (position == ONE){
            return new Popular();
        }else {
            return new Favorite();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       if (position == ZERO){
          return mContext.getString(R.string.top_rated);
       } else if (position == ONE){
           return mContext.getString(R.string.popular);
       }else {
           return mContext.getString(R.string.favorite);
       }
    }
}
