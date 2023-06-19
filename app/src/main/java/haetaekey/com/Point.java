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
import android.widget.ImageView;

public class Point extends AppCompatActivity {

    private ImageView fingerImageView;
    private Button no;
    private Button yes;

    private boolean animationRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        fingerImageView = findViewById(R.id.finger1);
        no = findViewById(R.id.no);
        yes = findViewById(R.id.yes);


        fingerImageView.post(new Runnable() {
            @Override
            public void run() {
                // finger 초기 위치 설정
                float startX = fingerImageView.getX();
                float startY = fingerImageView.getY();

                // 버튼(btn_next)의 가운데 좌표 계산
                int[] btnLocation = new int[2];
                no.getLocationOnScreen(btnLocation);
                float destinationX = btnLocation[0] + no.getWidth() / 2 - fingerImageView.getWidth() ;
                float destinationY = btnLocation[1] + no.getHeight()  - fingerImageView.getHeight() * 7;

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

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Point.this, Main.class);
                startActivity(intent);
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Point.this, Main.class);
                startActivity(intent);
            }
        });

        // 액션바 없애기
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}