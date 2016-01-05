package com.jash.lollipop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by jash
 * Date: 15-5-6
 * Time: 上午10:32
 */
public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {
    private Context context;
    private List<String> list;

    public RAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(context.getAssets().open(list.get(position)));
            ((ImageView) holder.itemView).setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void clear(){
        int size = list.size();
        list.clear();
        notifyItemRangeRemoved(0, size);
    }
    public void addAll(Collection<? extends String> collection){
        int size = list.size();
        list.addAll(collection);
        notifyItemRangeInserted(size, collection.size());
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
