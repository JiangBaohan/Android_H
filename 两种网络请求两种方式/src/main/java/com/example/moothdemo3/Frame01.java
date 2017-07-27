package com.example.moothdemo3;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sn.xlistviewlibrary.XListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoaderInterface;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Frame01 extends Fragment {
    private String path = "http://v.juhe.cn/toutiao/index?type=top&key=e76b62dbe5ce78645516fe866dc7058b";
    private String str_is;
    private List<Bean.ResultBean.DataBean> data;
    private XListView xlist;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, container, false);
        xlist = (XListView) view.findViewById(R.id.list_xlist);

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.a1);
        images.add(R.drawable.a2);
        images.add(R.drawable.a3);
        images.add(R.drawable.a4);
        images.add(R.drawable.a5);
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            titles.add("Number" + (i + 1));
        }

        Banner banner = (Banner) view.findViewById(R.id.banner01);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(new Utils())  //这个
                .setImages(images) //这个
                .setBannerTitles(titles)
                .start(); //这个
        new MyAsyncTask().execute();
        return view;
    }


    //自定义的AsyncTask
    private class MyAsyncTask extends AsyncTask<Void, Void, String> {
        //这个方法运行在主线程,在doInBackground之前运行,我们一般做初始化
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //这个方法运行在子线程,我们可以做一个耗时操作
        @Override
        protected String doInBackground(Void... voids) {
            ////////////////////////////////网络请求的操作,注意用return返回获取到的字符串,加网络权限////////////////////////////

            try {
                URL url = new URL(path);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //设置请求模式
                urlConnection.setRequestMethod("GET");
                //设置连接的超时时间，单位毫秒
                urlConnection.setConnectTimeout(5000);
                //设置读取的超时时间
                urlConnection.setReadTimeout(5000);
                //同步请求
                int code = urlConnection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = urlConnection.getInputStream();
                    //字节流转换成字符串
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int ch = -1;
                    byte[] buffer = new byte[1024 * 4];
                    while ((ch = inputStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, ch);
                    }
                    baos.flush();
                    str_is = baos.toString();
                    //ui不能直接更新界面，handler,runOnUiThread
                    return str_is;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        //运行在主线程,这个方法在doInBackground执行之后执行.我们一般做从网络拿到数据,使用的数据的操作
        @Override
        protected void onPostExecute(String s) {
            ////////////////////拿到处理后的数据,更新UI///////////////////////////////
            Gson gson = new Gson();
            Bean bean = gson.fromJson(str_is, Bean.class);
            data = bean.getResult().getData();
            xlist.setAdapter(new MyAdapter());

            super.onPostExecute(s);
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                holder = new ViewHolder();
                view = View.inflate(getActivity(), R.layout.fragment01_item, null);
                holder.iv = (ImageView) view.findViewById(R.id.fragment01_image);
                holder.tv = (TextView) view.findViewById(R.id.fagment01_text);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.tv.setText(data.get(i).getTitle());
            ImageLoderUtils.setImageView(data.get(i).getThumbnail_pic_s(), getActivity(), holder.iv);
            return view;
        }

        class ViewHolder {
            ImageView iv;
            TextView tv;
        }


    }

}
