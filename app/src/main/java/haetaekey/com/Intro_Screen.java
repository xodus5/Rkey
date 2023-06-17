package haetaekey.com;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Intro_Screen extends AppCompatActivity {

    private ImageView fingerImageView;
    private boolean animationRunning = false;
    private ViewPager viewPager;
    private Button btn_next;
    private int[] images = {R.drawable.intro_1, R.drawable.intro_2, R.drawable.intro_3};
    private static final long SLIDE_DELAY = 2000; //2초

    private Handler handler;
    private Runnable runnable;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        fingerImageView = findViewById(R.id.finger1);
        viewPager = findViewById(R.id.viewPager);
        btn_next = findViewById(R.id.btn_next);

        ImageAdapter imageAdapter = new ImageAdapter();
        viewPager.setAdapter(imageAdapter);

        for (int imageResource : images) {
            imageAdapter.addImage(imageResource);
        }

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                if (currentPage == images.length - 1) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                viewPager.setCurrentItem(currentPage);
                handler.postDelayed(this, SLIDE_DELAY);
            }
        };

        // 이미지 자동 전환 시작
        handler.postDelayed(runnable, SLIDE_DELAY);

        // 이미지 클릭 시 다음 Activity로 이동
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intro_Screen.this, Cafe_menu.class);
                startActivity(intent);
            }
        });

        // 액션바 없애기
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private class ImageAdapter extends PagerAdapter {
        private int[] imageResources;

        public ImageAdapter() {
            imageResources = new int[images.length * 2];
            for (int i = 0; i < images.length; i++) {
                imageResources[i] = images[i];
                imageResources[i + images.length] = images[i];
            }
        }

        public void addImage(int imageResource) {
            // 이미지 추가
        }

        @Override
        public int getCount() {
            return imageResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(Intro_Screen.this);
            imageView.setImageResource(imageResources[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            if (!animationRunning) {
                fingerImageView.post(new Runnable() {
                    @Override
                    public void run() {
                        // finger 초기 위치 설정
                        float startX = fingerImageView.getX();
                        float startY = fingerImageView.getY();

                        // 버튼(btn_next)의 가운데 좌표 계산
                        int[] btnLocation = new int[2];
                        btn_next.getLocationOnScreen(btnLocation);
                        float destinationX = btnLocation[0] + btn_next.getWidth() / 2 - fingerImageView.getWidth() / 2;
                        float destinationY = btnLocation[1] + btn_next.getHeight() / 2 - fingerImageView.getHeight() / 2;

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
        }
    }
}