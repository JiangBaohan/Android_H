package com.example.classdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static android.view.View.inflate;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<DataBean> list;
    //设置三种类型 对应三种不同类型的item,数字随意生成，主要是为了区分
    private static final int FristType = 0;
    private static final int TwoType = 1;
    private static final int ThreeType = 2;
    //const  随机生成一个随机数
    String[] texts = {"仙族", "八戒", "神族", "二郎神", "妖族", "女娲", "鬼族", "白无常", "魔族", "魔女幼熙"};
    int images[] = {R.drawable.jx_left_listitem_1, R.drawable.jx_left_listitem_5,
            R.drawable.jx_left_listitem_2, R.drawable.jx_left_listitem_3,
            R.drawable.jx_left_listitem_4, R.drawable.jx_left_listitem_5,
            R.drawable.jx_left_listitem_6, R.drawable.jx_left_listitem_6,
            R.drawable.jx_left_listitem_4, R.drawable.jx_left_listitem_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        listView = (ListView) findViewById(R.id.list);

        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataBean bean = new DataBean();
            //第一种类型，也就是偶数
            if (i % 2 == 0) {
                bean.setType(FristType);
                bean.setTexts(texts[i]);
            }
            //第二种类型
            else if (i % 3 == 0) {
                bean.setType(TwoType);
                bean.setImages(images[i]);
                bean.setTexts(texts[i]);
            }
            //第三种类型
            else {
                bean.setType(ThreeType);
                bean.setImages(images[i]);
                bean.setTexts(texts[i]);
            }
            list.add(bean);
            System.out.println("第" + i + "个:" + list.get(i).toString());
        }
    }

    /*listview 多条目的Adapter,比普通适配器多了两个方法
    Ctrl+O 快捷键  找到 getViewTypeCount()，getItemViewType(int position)这两个方法。
    * */
    class MyAdapter extends BaseAdapter {
        //有几种类型Item,有几种就写几
        @Override
        public int getViewTypeCount() {
            return 3;
        }

        //返回ListView所加载的类型
        @Override
        public int getItemViewType(int position) {
            return list.get(position).getType();
        }

        @Override
        public int getCount() {
            return list.size();
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
            ViewHolder1 holder1 = null;
            ViewHolder2 holder2 = null;
            ViewHolder3 holder3 = null;
            //拿到ListView 当前Item 所对应的DataBean数据的Type
            int type = getItemViewType(i);
            if (view == null) {
                switch (type) {
                    case FristType:
                        holder1 = new ViewHolder1();
                         view = View.inflate(MainActivity.this, R.layout.holder_item1, null);
                        holder1.tv1 = (TextView) view.findViewById(R.id.text1);
                        view.setTag(holder1);
                        break;
                    case TwoType:
                        holder2 = new ViewHolder2();
                         view = View.inflate(MainActivity.this, R.layout.holder_item2, null);
                        holder2.tv2 = (TextView) view.findViewById(R.id.text2);
                        holder2.iv2 = (ImageView) view.findViewById(R.id.image2);
                        view.setTag(holder2);
                        break;
                    case ThreeType:
                        holder3 = new ViewHolder3();
                         view = View.inflate(MainActivity.this, R.layout.holder_item3, null);
                        holder3.tv3 = (TextView) view.findViewById(R.id.text3);
                        holder3.iv3 = (ImageView) view.findViewById(R.id.image3);
                        view.setTag(holder3);
                        break;
                    default:
                        break;
                }
            } else {
                switch (type) {
                    case FristType:
                        holder1 = (ViewHolder1) view.getTag();
                        break;
                    case TwoType:
                        holder2 = (ViewHolder2) view.getTag();
                        break;
                    case ThreeType:
                        holder3 = (ViewHolder3) view.getTag();
                        break;
                    default:
                        break;
                }
            }
            switch (type) {
                case FristType:
                    DataBean bean = list.get(i);
                    holder1.tv1.setText(bean.getTexts());
                    break;
                case TwoType:
                    holder2.tv2.setText(list.get(i).getTexts());
                    holder2.iv2.setImageResource(list.get(i).getImages());
                    break;
                case ThreeType:
                    holder3.tv3.setText(list.get(i).getTexts());
                    holder3.iv3.setImageResource(list.get(i).getImages());
                    break;
                default:
                    break;
            }
           /* if (view == null) {
                holder = new ViewHolder();
                view = View.inflate(MainActivity.this, R.layout.list_item, null);
                holder.tv1 = (TextView) view.findViewById(R.id.list_text);
                holder.iv= (ImageView) view.findViewById(R.id.image);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.tv1.setText(list.get(i).getTexts());
            //Resource只显示本地图片
            holder.iv.setImageResource(list.get(i).getImages());*/
            return view;
        }

        class ViewHolder {
            TextView tv1;
            ImageView iv;
        }

        class ViewHolder1 {
            TextView tv1;
        }

        class ViewHolder2 {
            TextView tv2;
            ImageView iv2;
        }

        class ViewHolder3 {
            TextView tv3;
            ImageView iv3;
        }

    }
}
