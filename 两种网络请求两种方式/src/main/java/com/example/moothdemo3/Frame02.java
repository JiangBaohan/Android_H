package com.example.moothdemo3;

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

import com.google.gson.Gson;
import com.sn.xlistviewlibrary.XListView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.R.attr.data;


public class Frame02 extends Fragment {


    private String path = "http://v.juhe.cn/toutiao/index?type=top&key=e76b62dbe5ce78645516fe866dc7058b";
    private String str_is;
    private List<Bean.ResultBean.DataBean> data;
    private XListView xlist;

    private Bean bean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02, container, false);
        xlist = (XListView) view.findViewById(R.id.fragment02_list);
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
                urlConnection.setRequestMethod("POST");
                //POST请求必须设置如下两行
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
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
            bean = gson.fromJson(str_is, Bean.class);
            data = bean.getResult().getData();
            xlist.setAdapter(new MyAdapter());

            super.onPostExecute(s);
        }
    }

    class MyAdapter extends BaseAdapter {
        final int FristType = 0;
        final int TwoType = 1;

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0) {
                return FristType;
            } else {
                return TwoType;
            }
        }


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
            int type = getItemViewType(i);
            ViewHolder01 holder01 = null;
            ViewHolder02 holder02 = null;
            if (view == null) {
                switch (type) {
                    case FristType:

                        holder01 = new ViewHolder01();
                        view = View.inflate(getActivity(), R.layout.fragment02_holder, null);
                        holder01.tv1 = (TextView) view.findViewById(R.id.fagment02_text1);
                        holder01.iv1 = (ImageView) view.findViewById(R.id.fragment02_image1);
                        view.setTag(holder01);
                        break;
                    case TwoType:

                        holder02 = new ViewHolder02();
                        view = View.inflate(getActivity(), R.layout.fragment02_holder2, null);
                        holder02.tv2 = (TextView) view.findViewById(R.id.fagment02_text2);
                        holder02.iv2 = (ImageView) view.findViewById(R.id.fragment02_image2);
                        view.setTag(holder02);
                        break;
                    default:
                        break;
                }
            } else {
                switch (type) {
                    case FristType:
                        holder01 = (ViewHolder01) view.getTag();
                        break;
                    case TwoType:
                        holder02 = (ViewHolder02) view.getTag();
                        break;
                    default:
                        break;
                }
            }
            switch (type) {
                case FristType:
                    holder01.tv1.setText(data.get(i).getTitle());
                    ImageLoderUtils.setImageView(data.get(i).getThumbnail_pic_s(), getActivity(), holder01.iv1);
                    break;
                case TwoType:
                    holder02.tv2.setText(data.get(i).getTitle());
                    ImageLoderUtils.setImageView(data.get(i).getThumbnail_pic_s(), getActivity(), holder02.iv2);
                    break;
                default:
                    break;
            }

            return view;
        }

        class ViewHolder01 {
            TextView tv1;
            ImageView iv1;
        }

        class ViewHolder02 {
            TextView tv2;
            ImageView iv2;
        }
    }
}