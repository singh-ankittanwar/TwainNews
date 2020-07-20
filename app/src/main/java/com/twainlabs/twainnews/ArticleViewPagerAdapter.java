package com.twainlabs.twainnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ArticleViewPagerAdapter extends PagerAdapter {

    Context aContext;
    List<ArticleModel> aListScreen;

    public ArticleViewPagerAdapter(Context aContext, List<ArticleModel> aListScreen) {
        this.aContext = aContext;
        this.aListScreen = aListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inf = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View aLayoutScreen = inf.inflate(R.layout.layout_main, null);

        ImageView aImg = aLayoutScreen.findViewById(R.id.articleImg);
        TextView aHeadline = aLayoutScreen.findViewById(R.id.articleHeadline);
        TextView aSource = aLayoutScreen.findViewById(R.id.articleSource);
        TextView aDate = aLayoutScreen.findViewById(R.id.articleDate);

        Picasso.get().load(aListScreen.get(position).getArticleImg()).into(aImg);
        aHeadline.setText(aListScreen.get(position).getArticleHL());
        aSource.setText(aListScreen.get(position).getArticleSource());
        aDate.setText(aListScreen.get(position).getArticleDate());


        container.addView(aLayoutScreen);

        return aLayoutScreen;
    }

    @Override
    public int getCount() {
        return aListScreen.size();
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
