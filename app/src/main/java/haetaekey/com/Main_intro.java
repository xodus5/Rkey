package haetaekey.com;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class Main_intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intro);

        // 액션바 없애기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final ImageView introImageView = findViewById(R.id.intro_key);
        introImageView.setAlpha(1f);

        // 천천히 나타나는 애니메이션
        Animation fadeInAnimation = new AlphaAnimation(0, 1);
        fadeInAnimation.setDuration(3000); // 애니메이션 지속 2초

        // 애니메이션 종료 후 MainActivity로 전환
        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 메인 전환
                Intent intent = new Intent(Main_intro.this, Main.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        introImageView.startAnimation(fadeInAnimation);

    }
}