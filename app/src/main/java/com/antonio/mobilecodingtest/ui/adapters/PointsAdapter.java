package com.antonio.mobilecodingtest.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antonio.mobilecodingtest.R;
import com.antonio.mobilecodingtest.data.models.Point;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.ViewHolder>{
    private List<Point> pointsList;
    PointListener listener;

    public PointsAdapter(PointListener listener) {
        this.listener = listener;
    }

    public void setData(List<Point> pointsList) {
        this.pointsList = pointsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_point, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        holder.title.setText(pointsList.get(pos).getTitle());
    }

    @Override
    public int getItemCount() {
        if (pointsList==null) return 0;
        return pointsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.card_view)
        void OnClick(){
            listener.onItemClick(pointsList.get(getAdapterPosition()).getId());
        }
    }

    public interface PointListener{
        void onItemClick(String id);
    }
}
