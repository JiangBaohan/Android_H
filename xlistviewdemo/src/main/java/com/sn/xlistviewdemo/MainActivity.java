package com.sn.xlistviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.sn.xlistviewdemo.xListView.XListView;

import java.util.ArrayList;

/**
 *  官网:https://github.com/Maxwin-z/XListView-Android/tree/master/src/me/maxwin/view
 *  XlistView:就是一个实现了上拉刷新,下拉加载的自定义控件,我们如果集成他,就可以很轻松实现这些效果
 *  1.搭建XlistView的环境(拷贝资源:java代码,XML布局,strings资源,图片)
 *  2.布局编写,初始化控件
 *  3.初始化数据(一般网络的数据,进行解析封装到集合中,去使用)
 *  4.相当于listVIew的使用,创建适配器,设置适配器等等,(此时效果不佳)
 *  5.要进行XlistVIew设置下拉刷新,上拉加载的开启操作
 *  6.设置XlistVIew设置下拉刷新,上拉加载的监听操作
 *  7.编写下拉刷新,上拉加载的业务逻辑代码
 *  8.关闭下拉刷新,上拉加载的业务逻辑
 *  注意:如果你想把数据放到listVIew顶部,那么这个数据就放到集合的0的位置就可以了.
 *  提示你改布局,不要动控件的ID,你直接改控件内容即可
 */
public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView lv;
    private ArrayList<String> list;
    private ArrayAdapter<String> mAdapter;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //刷新listView
            mAdapter.notifyDataSetChanged();
            //隐藏头和尾
            closeXlistView();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (XListView) findViewById(R.id.xListView);

        //初始化数据
        initData();

        //开启下拉刷新
        lv.setPullRefreshEnable(true);
        //开启加载更多可用
        lv.setPullLoadEnable(true);

        //listView的适配器
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text1, list);
        //设置适配器
        lv.setAdapter(mAdapter);

        //设置XlistView的上拉加载,下拉刷新的接口
        lv.setXListViewListener(this);

    }

    private void initData() {
        list = new ArrayList<>();
        for(int x=0; x<10; x++){
            list.add("我要软妹子!!!!!!");
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
//        System.out.println("我有没有刷新");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                list.add(0,"给你一个刘亦菲");
                handler.sendEmptyMessage(0);
            }
        },2000);

    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
//        System.out.println("我有没有加载");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int x=0; x<10; x++){
                    list.add("给10个苍井空老师");
                }
                handler.sendEmptyMessage(0);
            }
        },2000);
    }

    //刷新给关闭的逻辑代码
    private void closeXlistView() {
        //停止加载更多
        lv.stopLoadMore();
        //停止刷新
        lv.stopRefresh();
        //更新时间
        lv.setRefreshTime("2017/7/19");
    }
}
