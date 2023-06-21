package haetaekey.com;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    Button btn_cafe;
    Button fast_1;
    Button btn_movie;
    Button btn_bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cafe = (Button) findViewById(R.id.cafe_1);
        fast_1 = (Button) findViewById(R.id.fast_1);
        btn_movie = (Button) findViewById(R.id.movie_1);
        btn_bank = (Button) findViewById(R.id.bank_1);

        btn_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Intro_Screen.class);
                startActivity(intent);
            }
        });

        fast_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, FF_Main.class);
                startActivity(intent);
            }
        });

        btn_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main.this, "현재 개발 진행 중입니다", Toast.LENGTH_SHORT).show();
            }
        });

        btn_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main.this, "현재 개발 진행 중입니다", Toast.LENGTH_SHORT).show();
            }
        });



        // 액션바 없애기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}