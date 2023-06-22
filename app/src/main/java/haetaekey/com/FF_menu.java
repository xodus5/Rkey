package haetaekey.com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FF_menu extends AppCompatActivity implements FFRecyclerViewInterface{
    Button btnCash;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FFData> FFList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ff_menu);

        // 액션바 없애기
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        btnCash = findViewById(R.id.btn_cash);
        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FF_menu.this, Public_cash.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.rv_cafe);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        FFList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("FFList");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                FFList.clear();
                for(DataSnapshot snapshot : datasnapshot.getChildren()) {
                    FFData cd = snapshot.getValue(FFData.class);
                    FFList.add(cd);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FF_menu", String.valueOf(error.toException()));
            }
        });

        adapter = new FFAdapter(FFList, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(FF_menu.this, FF_menudetail.class);
        intent.putExtra("menu", FFList.get(position).getMenu());
        intent.putExtra("name", FFList.get(position).getName());
        intent.putExtra("price", FFList.get(position).getPrice());
        startActivity(intent);
    }
}