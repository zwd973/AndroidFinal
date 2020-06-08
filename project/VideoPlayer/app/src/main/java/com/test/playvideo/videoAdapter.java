package com.test.playvideo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.List;

public class videoAdapter extends RecyclerView.Adapter<videoAdapter.NumberViewHolder>{

    private  Context context;
    private static final String TAG = "videoAdapter";
    private int mNumberItems;
    private final ListItemClickListener mOnClickListener;
    private List<Message> message;
    private static int viewHolderCount;
    

    public videoAdapter(int numListItems, List<Message> messages, ListItemClickListener listener) {
        mNumberItems = numListItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
        this.message = messages;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutID_ListItem = R.layout.videoitem;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachtoparent = false;

        View view = inflater.inflate(layoutID_ListItem, viewGroup, attachtoparent);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        numberViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView photo_v;
        private final TextView title_v;



        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            photo_v = itemView.findViewById(R.id.videocut);
            title_v = itemView.findViewById(R.id.videotitle);
            itemView.setOnClickListener(this);
        }

        public void bind(int position){
            Message messages = message.get(position);
            title_v.setText(messages.getDescription());
            //预览图加载
            Glide.with(context).setDefaultRequestOptions(
                    new RequestOptions()
                        .frame(1000)
                        .centerCrop()
                        .error(R.drawable.ic_launcher_background)
                        .placeholder(R.drawable.ic_launcher_background)
            ).load(messages.getFeedurl())
                    .into(photo_v);


        }

        @Override
        public void onClick(View v) {
            if (mOnClickListener != null) {
                mOnClickListener.onlistItemClick((String) title_v.getText());
            }
        }
    }

    public interface ListItemClickListener {
        void onlistItemClick(String description);
    }





}
