package com.test.playvideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements videoAdapter.ListItemClickListener {


    /*ArrayList<Message> list = new ArrayList<>();
    private Button bn_play;

    private String[] conver_urls = {"https://jzvd.nathen.cn/snapshot/f402a0e012b14d41ad07939746844c5e00005.jpg",
            "https://jzvd.nathen.cn/snapshot/8bd6d06878fc4676a62290cbe8b5511f00005.jpg",
            "https://jzvd.nathen.cn/snapshot/371ddcdf7bbe46b682913f3d3353192000005.jpg",
            "https://jzvd.nathen.cn/snapshot/dabe6ca3c71942fd926a86c8996d750f00005.jpg",
            "https://jzvd.nathen.cn/snapshot/edac56544e2f43bb827bd0e819db381000005.jpg",
            "https://jzvd.nathen.cn/snapshot/531f1e488eb84b898ae9ca7f6ba758ed00005.jpg",
            "https://jzvd.nathen.cn/snapshot/ad0331e78393457d88ded2257d9e47c800005.jpg",
            "https://jzvd.nathen.cn/snapshot/6ae53110f7fd470683587746f027698400005.jpg",
            "https://jzvd.nathen.cn/snapshot/ef384b95897b470c80a4aca4dd1112a500005.jpg",
            "https://jzvd.nathen.cn/snapshot/86a055d08b514c9ca1e76e76862105ec00005.jpg"};

    private String[] urls  =   {"https://jzvd.nathen.cn/video/1137e480-170bac9c523-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/e0bd348-170bac9c3b8-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/2f03c005-170bac9abac-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/7bf938c-170bac9c18a-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/47788f38-170bac9ab8a-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/633e0ce-170bac9ab65-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/51f7552c-170bac98718-0007-1823-c86-de200.mp4",
            "https://jzvd.nathen.cn/video/2a101070-170bad88892-0007-1823-c86-de200.mp4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<urls.length;i++){
            //list.add(new Message(urls[i],conver_urls[i]));
        }
        bn_play = findViewById(R.id.bn_play);
        bn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PlayerActivity.class);
                intent.putExtra("videoList",list);
                intent.putExtra("position",3);
                startActivity(intent);
            }
        });
    }*/

    private static final String TAG = "videoviewactivity";
    //private static final int NUM_LIST_ITEMS=20;
    private int num_list;


    private RecyclerView mListVideo;
    private ArrayList<Message> list;
    private videoAdapter vadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videolist);

        Log.d(TAG,"123");
        mListVideo = findViewById(R.id.videolist1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListVideo.setLayoutManager(layoutManager);


        mListVideo.setHasFixedSize(true);

        //data.xml文件资源读取
        try {
            list = (ArrayList<Message>) PullParser.pull2xml(getResources().getAssets().open("data.xml"));
            List<Message> l = PullParser.pull2xml(getResources().getAssets().open("data.xml"));
            Log.d(TAG,list.toString());
            Log.d(TAG,l.toString());
            for(int i=0;i<list.size();i++)
            {
                Message ob= list.get(i);
                Log.d(TAG,ob.getId());
                Log.d(TAG,ob.getFeedurl());
                Log.d(TAG,ob.getNickname());
                Log.d(TAG,String.valueOf(ob.getLikecount()));
                Log.d(TAG,ob.getAvactar());
                Log.d(TAG,ob.getAvactar());
            }
            num_list= list.size();
            Log.d(TAG, String.valueOf(num_list));
            vadapter = new videoAdapter(num_list,list,this);
            mListVideo.setAdapter(vadapter);
            Log.d(TAG,"123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onlistItemClick(String description){
        int pos;
        for(pos=0;pos<list.size();pos++){
            Message ob = list.get(pos);
            if(ob.getDescription() == description)
                break;
        }
        Log.d(TAG,String.valueOf(pos));
        Intent intent = new Intent(getApplicationContext(),PlayerActivity.class);
        intent.putExtra("videoList",list);
        intent.putExtra("position",pos);

        startActivity(intent);

    }


}
