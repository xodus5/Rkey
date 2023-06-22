package haetaekey.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FFAdapter extends RecyclerView.Adapter<FFAdapter.FFCustomViewHolder> {
    private FFRecyclerViewInterface itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    private OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnLongItemClickListener {
        void onLongItemClick(int pos);
    }

    private ArrayList<FFData> FFList;
    private Context context;

    public FFAdapter(ArrayList<FFData> FFList, Context context, FFRecyclerViewInterface itemClickListener) {
        this.FFList = FFList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public FFAdapter.FFCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        FFCustomViewHolder holder = new FFCustomViewHolder(view, itemClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FFAdapter.FFCustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(FFList.get(position).getMenu())
                .into(holder.gv_menu);
        holder.gv_name.setText(FFList.get(position).getName());
        holder.gv_price.setText(FFList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return (FFList != null ? FFList.size() : 0);
    }

    public class FFCustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView gv_menu;
        protected TextView gv_name;
        protected TextView gv_price;

        public FFCustomViewHolder(@NonNull View itemView, FFRecyclerViewInterface FFRecyclerViewInterface) {
            super(itemView);
            this.gv_menu = (ImageView) itemView.findViewById(R.id.gv_menu);
            this.gv_name = (TextView) itemView.findViewById(R.id.gv_name);
            this.gv_price = (TextView) itemView.findViewById(R.id.gv_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(FFRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION) {
                            FFRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
