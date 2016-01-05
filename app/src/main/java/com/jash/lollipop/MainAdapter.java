package com.jash.lollipop;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by jash
 * Date: 15-5-6
 * Time: 上午11:03
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context context;
    private List<Class<? extends Activity>> activities;
    private View.OnClickListener listener;
    public MainAdapter(Context context, List<Class<? extends Activity>> activities) {
        this.context = context;
        this.activities = activities;
        if (context instanceof View.OnClickListener){
            listener = (View.OnClickListener) context;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = null;
        try {
            Field field = activities.get(position).getField("name");
            name = (String) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        ((TextView) holder.itemView).setText(name);
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public Class<? extends Activity> getItem(int position){
        return activities.get(position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
