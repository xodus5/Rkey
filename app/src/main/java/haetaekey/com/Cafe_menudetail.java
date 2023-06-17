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

import java.util.ArrayList;

public class Cafe_menudetail extends AppCompatActivity {

    Button btn_put;
    ImageView fingerImageView;
    boolean animationRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_menudetail);

        fingerImageView = findViewById(R.id.finger2);
        btn_put = findViewById(R.id.btn_put);

        fingerImageView.bringToFront();

        fingerImageView.post(new Runnable() {
            @Override
            public void run() {
                // finger 초기 위치 설정
                float startX = fingerImageView.getX();
                float startY = fingerImageView.getY();

                // 버튼 1로 finger 이동하는 위치 설정
                float destinationX = btn_put.getX() + btn_put.getWidth() / 2 - fingerImageView.getWidth() / 10;
                float destinationY = btn_put.getY() + btn_put.getHeight() * 10  - fingerImageView.getHeight() / 4 ;

                ObjectAnimator animatorX = ObjectAnimator.ofFloat(fingerImageView, "x", startX, destinationX);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(fingerImageView, "y", startY, destinationY);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animatorX, animatorY);
                animatorSet.setDuration(1500);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        // 애니메이션 시작시 클릭 막기
                        animationRunning = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // 애니메이션 종료시 다시 실행
                        animationRunning = false;
                    }
                });
                animatorSet.start();
            }
        });

        btn_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!animationRunning) {
                    // 애니메이션 실행중일때 클릭 막기
                    fingerImageView.animate().cancel();
                    fingerImageView.setVisibility(View.INVISIBLE);

                    Intent intent2 = new Intent(Cafe_menudetail.this, Public_cash.class);
                    startActivity(intent2);
                    finish();
                }
            }
        });

        // 액션바 없애기
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }
}