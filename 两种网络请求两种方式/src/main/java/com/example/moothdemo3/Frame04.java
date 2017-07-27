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
import android.widget.Toast;

import com.google.gson.Gson;
import com.sn.xlistviewlibrary.XListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;


public class Frame04 extends Fragment {
    private String path = "http://v.juhe.cn/toutiao/index?type=top&key=e76b62dbe5ce78645516fe866dc7058b";
    private String str_is;
    private List<Bean.ResultBean.DataBean> data;
    private XListView xlist;

    private Bean bean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment04, container, false);

        xlist = (XListView) view.findViewById(R.id.fragment04_list);
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

            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
          //  HttpGet httpGet = new HttpGet(path);
            HttpPost httpPost=new HttpPost(path);
            HttpResponse response = null;
            try {
                response = defaultHttpClient.execute(httpPost);
                //POST请求必须设置如下两行
                StatusLine line = response.getStatusLine();
                int code = line.getStatusCode();
                if (code == 200) {
                    //5.服务器通过流写给客户端的数据,把它成一个实体
                    HttpEntity entity = response.getEntity();
                    //获取输入流
                    InputStream inputStream = entity.getContent();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int len = 0;
                    byte[] buffer = new byte[1024];

                    while ((len = inputStream.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                        str_is= baos.toString();
                    }
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
                        view = View.inflate(getActivity(), R.layout.fragment04_holder, null);
                        holder01.tv1 = (TextView) view.findViewById(R.id.fagment04_text1);
                        holder01.iv1 = (ImageView) view.findViewById(R.id.fragment04_image1);
                        view.setTag(holder01);
                        break;
                    case TwoType:

                        holder02 = new ViewHolder02();
                        view = View.inflate(getActivity(), R.layout.fragment04_holder2, null);
                        holder02.tv2 = (TextView) view.findViewById(R.id.fagment04_text2);
                        holder02.iv2 = (ImageView) view.findViewById(R.id.fragment04_image2);
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
