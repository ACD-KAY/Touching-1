package com.hhu.acd.touching;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stfalcon.multiimageview.MultiImageView;

import java.util.ArrayList;

public class Activity_05 extends AppCompatActivity {

    private ImageView btn;
    private ImageButton btn2;

    private ExpandableListView listview;
    //private ArrayList<linkman_group> list = new ArrayList<linkman_group>();
    int[] img = new int[6];
    MultiImageView my_portrait;
    linkman_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_05);
        //initData();
        listview =  findViewById(R.id.linkman_expand_list);
        getlinkman();
        //listview.setAdapter(adapter);
        /*listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getApplicationContext(),list.get(i).getChild(i1).getNickName(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });*/
        my_portrait=findViewById(R.id.my_head_portrait);
        my_portrait.addImage(BitmapFactory.decodeResource(getResources(),R.drawable.bussiness_man));
        my_portrait.setShape(MultiImageView.Shape.CIRCLE);


        /**跳转到个人信息界面*/
        btn=(ImageButton)findViewById(R.id.my_head_portrait);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_05.this, Activity_12.class);
                startActivity(it);
            }
        });

        /**跳转到添加好友界面*/
        btn2=(ImageButton)findViewById(R.id.addfriends);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Activity_05.this, Activity_14.class);
                startActivity(it);
            }
        });
    }
    /*private void initData() {
        for (int i = 0; i < img.length; i++) {
            try {
                //img[i] = R.drawable.class.getField("img0"+(i+1)).getInt(null);
                img[i]=R.drawable.bussiness_man;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        linkman_group group1 = new linkman_group("我的关注");
        group1.addUser(new linkman_users(img[0], "张翰", true));
        group1.addUser(new linkman_users(img[1], "郑爽", false));
        group1.addUser(new linkman_users(img[2], "胡彦斌", true));
        group1.addUser(new linkman_users(img[5], "撒贝宁", true));
        group1.addUser(new linkman_users(img[3], "杨幂", false));

        linkman_group group2 = new linkman_group("我的好友");
        group2.addUser(new linkman_users(img[4], "林志炫", true));

        list.add(group1);
        list.add(group2);

    }*/
    private void getlinkman(){
        String linkmanstr=constant.URL_Linkman;
        new MyAsyncTask(listview,this).execute(linkmanstr);
    }
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager manger = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manger.getActiveNetworkInfo();
            //return (info!=null && info.isConnected());//
            if(info != null){
                return info.isConnected();
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static class MyAsyncTask extends AsyncTask<String, Integer,String > {

        //private TextView tv; // 举例一个UI元素，后边会用到
        private mylinkman mylinkman;
        private ArrayList<linkman_group> list = new ArrayList<linkman_group>();
        private ExpandableListView listview;
        Context context;
        linkman_adapter adapter;
        Gson gson=new Gson();
        public MyAsyncTask(ExpandableListView ev,Context context) {
            this.listview=ev;
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            Log.w("WangJ", "task onPreExecute()");
            /*linkman_group group1 = new linkman_group("我的关注");
            group1.addUser(new linkman_users(R.drawable.bussiness_man, "张翰", true));
            list.add(group1);
            adapter = new linkman_adapter(context,list);
            listview.setAdapter(adapter);
            listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                    Toast.makeText(context,list.get(i).getChild(i1).getNickName(), Toast.LENGTH_SHORT).show();

                    return true;
                }
            });
*/
        }

        /**
         * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
         */
        @Override
        protected  String doInBackground(String... params) {
            Log.w("WangJ", "task doInBackground()");
            return NetUtils.get(params[0]);
            /*HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(params[0]); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                *//*InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;*//*
                int responseCode = connection.getResponseCode();
                *//*if(isNetworkAvailable(context)){
                    while ((line = reader.readLine()) != null) {
                    response.append(line);
                    }
                }
                else {
                    return "";
                }*//*
                if (responseCode == 200) {

                    InputStream is = connection.getInputStream();
                    String response = getStringFromInputStream(is);
                    return response;
                } else {
                    throw new NetworkErrorException("response status is "+responseCode);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参*/
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 如果在doInBackground方法，那么就会立刻执行本方法
            // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用
        }

        /**
         * 运行在UI线程中，所以可以直接操作UI元素
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            Log.w("WangJ", "task onPostExecute()");
            if (s!=null){
                list=(gson.fromJson(s,mylinkman.class).getlist());
                // adapter.notifyDataSetChanged();
                adapter = new linkman_adapter(context,list);

                listview.setAdapter(adapter);
                listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                        Toast.makeText(context,list.get(i).getChild(i1).getNickName(), Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });}
            else {
                list.add(new linkman_group(""));
                adapter = new linkman_adapter(context,list);

                listview.setAdapter(adapter);
                listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                        Toast.makeText(context,list.get(i).getChild(i1).getNickName(), Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });
            }

        }

    }

}