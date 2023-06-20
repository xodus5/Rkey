package haetaekey.com;

import android.app.UiAutomation;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.CafeCustomViewHolder> {
    private CafeRecyclerViewInterface itemClickListener;

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

    private ArrayList<CafeData> cafeList;
    private Context context;

    public CafeAdapter(ArrayList<CafeData> cafeList, Context context, CafeRecyclerViewInterface itemClickListener) {
        this.cafeList = cafeList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CafeAdapter.CafeCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        CafeCustomViewHolder holder = new CafeCustomViewHolder(view, itemClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CafeAdapter.CafeCustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(cafeList.get(position).getMenu())
                .into(holder.gv_menu);
        holder.gv_name.setText(cafeList.get(position).getName());
        holder.gv_price.setText(cafeList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return (cafeList != null ? cafeList.size() : 0);
    }

    public class CafeCustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView gv_menu;
        protected TextView gv_name;
        protected TextView gv_price;

        public CafeCustomViewHolder(@NonNull View itemView, CafeRecyclerViewInterface cafeRecyclerViewInterface) {
            super(itemView);
            this.gv_menu = (ImageView) itemView.findViewById(R.id.gv_menu);
            this.gv_name = (TextView) itemView.findViewById(R.id.gv_name);
            this.gv_price = (TextView) itemView.findViewById(R.id.gv_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cafeRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION) {
                            cafeRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
