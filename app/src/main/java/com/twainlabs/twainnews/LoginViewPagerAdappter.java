package com.twainlabs.twainnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class LoginViewPagerAdappter extends PagerAdapter {

    Context mContext;
    List<LoginModel> mListScreen;

    public LoginViewPagerAdappter(Context mContext, List<LoginModel> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutscreen = inflater.inflate(R.layout.layout_login, null);

        ImageView img = layoutscreen.findViewById(R.id.img_login);
        TextView txt = layoutscreen.findViewById(R.id.txt_login);

        txt.setText(mListScreen.get(position).getLogintxt());
        img.setImageResource(mListScreen.get(position).getLoginImg());

        container.addView(layoutscreen);

        return layoutscreen;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
