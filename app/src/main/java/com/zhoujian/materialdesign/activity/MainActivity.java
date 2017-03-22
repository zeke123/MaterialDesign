package com.zhoujian.materialdesign.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.zhoujian.materialdesign.R;
import com.zhoujian.materialdesign.adapter.PictureAdapter;
import com.zhoujian.materialdesign.bean.Picture;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private  Toolbar toolbar;

    private   DrawerLayout  mDrawerLayout;

    private NavigationView mNavigationView;

    private List<Picture> fruitList = new ArrayList<>();

    private PictureAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;


    private Picture[] pictures = {new Picture("水漫金山", R.mipmap.aa), new Picture("湖光山色", R.mipmap.bb),
            new Picture("郁郁葱葱", R.mipmap.cc), new Picture("草长莺飞", R.mipmap.dd),
            new Picture("春山如笑", R.mipmap.ee), new Picture("柳绿花红 ", R.mipmap.ff),
            new Picture("江山如画", R.mipmap.xx), new Picture("青山不老", R.mipmap.yy),
            new Picture("大好河山", R.mipmap.zz)};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar mActionBar = getSupportActionBar();

        if(mActionBar!=null)
        {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }

        mNavigationView = (NavigationView)findViewById(R.id.nav_view);


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                mDrawerLayout.closeDrawers();

                return true;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Toast.makeText(MainActivity.this, "悬浮按钮被点击了", Toast.LENGTH_SHORT).show();

                Snackbar.make(view, "你好！", Snackbar.LENGTH_SHORT)
                        .setAction("发送", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "消息已发送！", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });




        initDatas();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PictureAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDatas();
            }
        });

    }

    private void refreshDatas() {

        //网络获取数据
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 获取数据
                initDatas();
                swipeRefresh.setRefreshing(false);
            }
        },2000);

    }

    private void initDatas() {

        for (int i = 0; i < pictures.length; i++) {

            fruitList.add(pictures[i]);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "按钮被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "按钮被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                break;

            default:
        }
        return true;
    }
}
