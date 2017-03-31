package com.airsenze.eaomvp.ui.toolbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airsenze.eaomvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Aidan Laing on 2017-03-21.
 *
 */

public class Toolbar extends RelativeLayout {

    private Context context;
    private StartIconClickListener startIconClickListener;

    public Toolbar(Context context) {
        super(context);
        this.context = context;
        inflateView();
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflateView();
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        inflateView();
    }

    private void inflateView() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toolbarView = layoutInflater.inflate(R.layout.toolbar, this, true);
        ButterKnife.bind(this, toolbarView);
    }

    //TITLE
    @BindView(R.id.toolbar_title_tv)
    TextView titleTv;

    public void setTitle(String title) {
        titleTv.setText(title);
        titleTv.setVisibility(VISIBLE);
    }

    //START ICON
    private interface StartIconClickListener {
        void startIconClicked();
    }

    @BindView(R.id.toolbar_icon_start_iv)
    ImageView startIconIv;

    @OnClick(R.id.toolbar_icon_start_iv)
    void startIconClicked() {
        if(startIconClickListener != null) {
            startIconClickListener.startIconClicked();
        }
    }

    private void setStartIcon(int resId) {
        Drawable iconDrawable = ResourcesCompat.getDrawable(context.getResources(), resId, null);
        startIconIv.setImageDrawable(iconDrawable);
        startIconIv.setVisibility(VISIBLE);
    }

    //BACKING
    public void enableBacking(final Context context) {
        setStartIcon(R.mipmap.ic_launcher_round);
        startIconClickListener = new StartIconClickListener() {
            @Override
            public void startIconClicked() {
                ((AppCompatActivity) context).finish();
            }
        };
    }

    //NAV DRAWER
    private Boolean navDrawerOpened;
    private DrawerLayout drawerLayout;

    public void enableNavDrawer(final DrawerLayout drawerLayout) {

        this.navDrawerOpened = false;
        this.drawerLayout = drawerLayout;

        setStartIcon(R.mipmap.ic_launcher);
        startIconClickListener = new StartIconClickListener() {
            @Override
            public void startIconClicked() {
                if(navDrawerOpened) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        };
    }

    public void openedDrawer() {
        navDrawerOpened = true;
    }

    public void closedDrawer() {
        navDrawerOpened = false;
    }
}
