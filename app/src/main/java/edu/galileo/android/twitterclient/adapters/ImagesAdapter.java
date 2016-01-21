package edu.galileo.android.twitterclient.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.twitterclient.R;
import edu.galileo.android.twitterclient.content.OnItemClickListener;
import edu.galileo.android.twitterclient.entities.TweetEntity;
import edu.galileo.android.twitterclient.lib.ImageLoading;

/**
 * Created by ykro.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private List<TweetEntity> items;
    private ImageLoading imageLoadingHelper;
    private OnItemClickListener clickListener;
Fragment fragment;
    public ImagesAdapter(Fragment fragment, List<TweetEntity> items, OnItemClickListener clickListener) {
        this.items = items;
        this.clickListener = clickListener;

        this.fragment = fragment;
        //imageLoadingHelper = new ImageLoading(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TweetEntity tweet = items.get(position);
        holder.setClickListener(tweet, clickListener);
        holder.txtTweet.setText(tweet.getTweetText());

        //imageLoadingHelper.loadImage(tweet.getImageURL(), holder.imgMedia);

        Glide.with(fragment)
                .load(tweet.getImageURL())
                .into(holder.imgMedia);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtTweet) TextView txtTweet;
        @Bind(R.id.imgMedia) ImageView imgMedia;

        private View view;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        public void setClickListener(final TweetEntity tweet,
                                     final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(tweet);
                }
            });

        }
    }
}