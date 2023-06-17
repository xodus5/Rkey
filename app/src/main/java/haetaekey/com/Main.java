package haetaekey.com;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    Button btn_cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cafe = (Button) findViewById(R.id.cafe_1);

        btn_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Intro_Screen.class);
                startActivity(intent);
            }
        });


        // 액션바 없애기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}