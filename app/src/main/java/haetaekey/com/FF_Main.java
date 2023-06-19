package haetaekey.com;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FF_Main extends AppCompatActivity {

    ImageButton intro_ff;
    private ImageView fingerImageView;
    private boolean animationRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ff_main);

        intro_ff = (ImageButton) findViewById(R.id.intro_ff);
        fingerImageView = findViewById(R.id.finger1);

        intro_ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FF_Main.this, FF_Cash.class);
                startActivity(intent);
            }
        });

        if (!animationRunning) {
            fingerImageView.post(new Runnable() {
                @Override
                public void run() {
                    // finger 초기 위치 설정
                    float startX = fingerImageView.getX();
                    float startY = fingerImageView.getY();

                    // 버튼(btn_next)의 가운데 좌표 계산
                    int[] btnLocation = new int[2];
                    intro_ff.getLocationOnScreen(btnLocation);
                    float destinationX = btnLocation[0] + intro_ff.getWidth() / 2 - fingerImageView.getWidth() / 2;
                    float destinationY = btnLocation[1] + intro_ff.getHeight() / 2 - fingerImageView.getHeight() / 2;

                    ObjectAnimator animatorX = ObjectAnimator.ofFloat(fingerImageView, "x", startX, destinationX);
                    ObjectAnimator animatorY = ObjectAnimator.ofFloat(fingerImageView, "y", startY, destinationY);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(animatorX, animatorY);
                    animatorSet.setDuration(1500);
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            // 애니메이션 시작 시 클릭 막기
                            animationRunning = true;
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            // 애니메이션 종료 시 다시 실행
                            animationRunning = false;
                        }
                    });
                    animatorSet.start();
                }
            });
        }

        // 액션바 없애기
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}