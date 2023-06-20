package haetaekey.com;

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

    private ArrayList<CafeData> cafeList;
    private Context context;

    public CafeAdapter(ArrayList<CafeData> cafeList, Context context) {
        this.cafeList = cafeList;
        this.context = context;
    }

    @NonNull
    @Override
    public CafeAdapter.CafeCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        CafeCustomViewHolder holder = new CafeCustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CafeAdapter.CafeCustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(cafeList.get(position).getMenu())
                .into(holder.gv_menu);
        holder.gv_name.setText(cafeList.get(position).getName());
        holder.gv_price.setText(cafeList.get(position).getPrice());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menuName = holder.gv_name.getText().toString();
                Toast.makeText(view.getContext(), menuName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (cafeList != null ? cafeList.size() : 0);
    }

    public class CafeCustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView gv_menu;
        protected TextView gv_name;
        protected TextView gv_price;

        public CafeCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.gv_menu = (ImageView) itemView.findViewById(R.id.gv_menu);
            this.gv_name = (TextView) itemView.findViewById(R.id.gv_name);
            this.gv_price = (TextView) itemView.findViewById(R.id.gv_price);
        }
    }
}
