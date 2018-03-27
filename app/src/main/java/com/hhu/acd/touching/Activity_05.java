package com.hhu.acd.touching;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.allenliu.badgeview.BadgeFactory;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.msg.constant.SystemMessageType;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.netease.nimlib.sdk.uinfo.model.UserInfo;
import com.stfalcon.multiimageview.MultiImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Activity_05 extends AppCompatActivity {


    private ImageButton newfriends;
    private RecyclerView.LayoutManager mLayoutManager;
    private friends_list_05_adapter mAdapter;
    private RecyclerView mRecyclerView;
    EasyRefreshLayout easyRefreshLayout;
    private MyHandler mHandler=new MyHandler(this);
    Toolbar toolbar;
    ImageButton my_portrait;
    List<NimUserInfo> list;
    List<SystemMessageType> types = new ArrayList<SystemMessageType>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_05);
        types.add(SystemMessageType.AddFriend);
        getfriends();
        //initData();
        //listview =  findViewById(R.id.linkman_expand_list);
        //getlinkman();
        //listview.setAdapter(adapter);
        /*listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getApplicationContext(),list.get(i).getChild(i1).getNickName(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });*/
        my_portrait=findViewById(R.id.my_head_portrait05);
        //my_portrait.addImage(BitmapFactory.decodeResource(getResources(),R.drawable.bussiness_man));
        String url = "http://www.guolin.tech/book.png";
        Glide.with(this)
                .load(url)
                .apply(new RequestOptions()
                .placeholder(R.drawable.bussiness_man)
                .circleCrop())
                .into(my_portrait);

        toolbar = findViewById(R.id.toolbar05);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //my_portrait.setShape(MultiImageView.Shape.CIRCLE);
        easyRefreshLayout=findViewById(R.id.friends_easylayout05);
        easyRefreshLayout.setLoadMoreModel(LoadModel.NONE);
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {


            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        List<String> accounts = NIMClient.getService(FriendService.class).getFriendAccounts();
                        NIMClient.getService(UserService.class).fetchUserInfo(accounts)
                                .setCallback(new RequestCallback<List<NimUserInfo>>() {
                                    @Override
                                    public void onSuccess(List<NimUserInfo> param) {
                                        mHandler.obtainMessage(3,param).sendToTarget();
                                    }

                                    @Override
                                    public void onFailed(int code) {
                                        mHandler.obtainMessage(1," 获取信息失败").sendToTarget();
                                    }

                                    @Override
                                    public void onException(Throwable exception) {
                                        mHandler.obtainMessage(1," 可能哪里出现了问题").sendToTarget();
                                    }
                                });
                        easyRefreshLayout.refreshComplete();
                        Toast.makeText(getApplicationContext(), "refresh success", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });



        /**跳转到验证消息界面*/
        newfriends=(ImageButton)findViewById(R.id.newfriends05);
        newfriends.setOnClickListener(new View.OnClickListener() {
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


    private void getfriends(){
        List<String> accounts = NIMClient.getService(FriendService.class).getFriendAccounts();
        NIMClient.getService(UserService.class).fetchUserInfo(accounts)
                .setCallback(new RequestCallback<List<NimUserInfo>>() {
                    @Override
                    public void onSuccess(List<NimUserInfo> param) {
                        mHandler.obtainMessage(2,param).sendToTarget();
                    }

                    @Override
                    public void onFailed(int code) {
                        mHandler.obtainMessage(1," 获取信息失败").sendToTarget();
                    }

                    @Override
                    public void onException(Throwable exception) {
                        mHandler.obtainMessage(1," 可能哪里出现了问题").sendToTarget();
                    }
                });
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new friends_list_05_adapter(this,list);

        mAdapter.setHasStableIds(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_friends05);
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        //String linkmanstr=constant.URL_Linkman;
        //new MyAsyncTask(listview,this).execute(linkmanstr);
        BadgeFactory.createCircle(this).setBadgeCount(NIMClient.getService(SystemMessageService.class)
                .querySystemMessageUnreadCountByType(types)).setBadgeGravity(Gravity .RIGHT|Gravity.TOP).bind(toolbar);
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

    /*public static class MyAsyncTask extends AsyncTask<String, Integer,String > {

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
            *//*linkman_group group1 = new linkman_group("我的关注");
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
*//*
        }

        *//**
         //* @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
         *//*
        @Override
        protected  String doInBackground(String... params) {
            Log.w("WangJ", "task doInBackground()");
            return NetUtils.get(params[0]);
            *//*HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(params[0]); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                *//**//*InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;*//**//*
                int responseCode = connection.getResponseCode();
                *//**//*if(isNetworkAvailable(context)){
                    while ((line = reader.readLine()) != null) {
                    response.append(line);
                    }
                }
                else {
                    return "";
                }*//**//*
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
            return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参*//*
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 如果在doInBackground方法，那么就会立刻执行本方法
            // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用
        }

        *//**
         * 运行在UI线程中，所以可以直接操作UI元素
         * //@param s
         *//*
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

    }*/
    private static class MyHandler extends Handler {

        //对Activity的弱引用
        private final WeakReference<Activity_05> mActivity;

        public MyHandler(Activity_05 activity) {
            mActivity = new WeakReference<Activity_05>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity_05 activity = mActivity.get();
            if (activity == null) {
                super.handleMessage(msg);
                return;
            }
            switch (msg.what) {
                case 1:
                    Toast.makeText(activity, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Glide.with(activity)
                            .load(okhttpurl.url_image)

                            .into(activity.my_portrait);
                    activity.list=(List<NimUserInfo>)msg.obj;
                    //activity.mAdapter = new MyAdapter_03(activity,(List<RecentContact>)msg.obj);
                    break;
                case 3:
                    Glide.with(activity)
                            .load(okhttpurl.url_image)

                            .into(activity.my_portrait);
                    BadgeFactory.createCircle(activity.getApplicationContext()).setBadgeCount(NIMClient.getService(SystemMessageService.class)
                            .querySystemMessageUnreadCountByType(activity.types)).setBadgeGravity(Gravity .RIGHT|Gravity.TOP).bind(activity.toolbar);
                    activity.mAdapter.updateData((List<NimUserInfo>)msg.obj);
                    break;
                /*case 4:
                    activity.list.add((message_data)msg.obj);
                    activity.mAdapter.updateData(activity.list);
                    break;*/
                /*case 2:
                    Toast.makeText(activity, "下载成功", Toast.LENGTH_SHORT).show();
                    Bitmap bitmap = (Bitmap) msg.obj;
                    activity.imageView.setVisibility(View.VISIBLE);
                    activity.imageView.setImageBitmap(bitmap);
                    break;*/
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }
}