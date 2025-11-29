package com.example.oreoclicker;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    Button shop;
    TextView textViewCookies, textViewName, textViewCookiesPerSecond;
    ImageView cookie, settings, shine;

    MediaPlayer mediaPlayer;
    ConstraintLayout constraintLayout;

    AtomicInteger cookies = new AtomicInteger(0);
    Integer cookiesPerClick = 1;

    ActivityResultLauncher<Intent> activityResultLauncher;

    List<Oreos> oreos;

    PopupWindow popupWindow;
    Boolean pressed = false;

    public synchronized void incrementCookies(int amount) {
        cookies.addAndGet(amount);
    }

    float cpsTimer;
    public static final String STRING_KEY = "cookies";

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = findViewById(R.id.textView4);
        textViewCookies = findViewById(R.id.textView);
        textViewCookiesPerSecond = findViewById(R.id.textView2);
        cookie = findViewById(R.id.imageView);
        constraintLayout = findViewById(R.id.constraintLayout);
        shop = findViewById(R.id.button2);
        shine = findViewById(R.id.imageView4);
        settings = findViewById(R.id.imageView2);

        mediaPlayer = MediaPlayer.create(this, R.raw.click);
        oreos = new ArrayList<>();

        Animation animationS = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animationS.setInterpolator(new LinearInterpolator());
        animationS.setRepeatCount(Animation.INFINITE);
        shine.startAnimation(animationS);

        if (oreos.isEmpty()){
            oreos.add(new Oreos("Oreo Minis", 100, 1, R.drawable.oreomini, 0));
            oreos.add(new Oreos("Mint Oreo", 200, 2, R.drawable.mintoreo, 0));
            oreos.add(new Oreos("DOUBLE Stuf Oreo", 500, 5, R.drawable.doubleoreo, 0));
            oreos.add(new Oreos("Golden Oreo", 100000,100, R.drawable.goldenoreo, 0));
            oreos.add(new Oreos("Birthday Cake Oreo", 1000000,1000, R.drawable.bdayoreo, 0));
            oreos.add(new Oreos("MEGA Stuf Oreo", 5000000,5000, R.drawable.megaoreo, 0));
        }

        android.os.Handler customHandler = new android.os.Handler();
        customHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
                cookies.addAndGet(sharedPreferences.getInt("OPS", 0));
                sharedPreferences.edit().putInt("oreos", cookies.get()).apply();
                textViewCookies.setText(cookies.toString() + " Oreos");
                if (textViewCookiesPerSecond.getText() != sharedPreferences.getInt("OPS", 0) + " OPS")
                    textViewCookiesPerSecond.setText(sharedPreferences.getInt("OPS", 0) + " OPS");
                customHandler.postDelayed(this, 1000);
            }
        }, 1000);

        android.os.Handler handler = new android.os.Handler();
        long timer = (int)(Math.random() * 30000) + 50000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setId(View.generateViewId());
                imageView.setImageResource(R.drawable.goldoreo);
                imageView.setAdjustViewBounds(true);
                imageView.setMaxHeight(500);
                imageView.setMaxWidth(500);

                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(params);
                constraintLayout.addView(imageView);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(imageView.getId(), ConstraintSet.TOP, cookie.getId(), ConstraintSet.TOP);

                imageView.setX(cookie.getX() + (new Random().nextInt(700) - 100));
                imageView.setY(cookie.getY() + new Random().nextInt(1000) - 200);

                ObjectAnimator grow = ObjectAnimator.ofFloat(imageView, "scaleX", 0.5f, 1f, 0.9f,
                        1.05f, 0.95f, 1.1f, 0.9f, 1.05f, 0f);
                grow.setDuration(11000);
                ObjectAnimator grow2 = ObjectAnimator.ofFloat(imageView, "scaleY", 0.5f, 1f, 0.9f,
                        1.05f, 0.95f, 1.1f, 0.9f, 1.05f, 0f);
                grow2.setDuration(11000);
                ObjectAnimator fadein = ObjectAnimator.ofFloat(imageView, "alpha", 0.65f, 1f, 1f,
                        1f, 1f, 1f, 1f, 1f, 0f);
                fadein.setDuration(11000);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(imageView, "rotation", 0f, -20f,
                        0f, 20f, 0f, -20f, 0f, 20f, 0f, -20f, 0f, 20f, 0f, -20f,
                        0f, 20f, 0f, -20f, 0f, 20f, 0f, -20f, 0f, 20f, 0f, -20f,
                        0f, 20f, 0f, -20f, 0f, 20f, 0f, -20f, 0f, 20f, 0f, -20f,
                        0f, 20f, 0f, -20f, 0f, 20f, 0f, -20f, 0f, 20f, 0f);
                rotate.setDuration(11000);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(grow, grow2, fadein, rotate);
                animatorSet.start();

                imageView.setOnClickListener(v -> {
                    constraintLayout.removeView(imageView);
                    cookies.addAndGet(1000);
                    textViewCookies.setText(cookies.toString() + " Oreos");
                });

                handler.postDelayed(this, timer);
            }
        }, timer);

        /*
        cpsTimer = System.currentTimeMillis();
        if (cpsTimer % 1000) {
            cookies += cookiesPerSecond;
            textViewCookies.setText(cookies.toString() + " Cookies");
            cpsTimer = System.currentTimeMillis();
        }
        */


        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        textViewName.setText(sharedPreferences.getString("name", "User"));
        if (!sharedPreferences.contains("oreos"))
            editor.putInt("oreos", cookies.get());
        if (!sharedPreferences.contains("OPS"))
            editor.putInt("OPS", 0);

        //cookies = sharedPreferences.getInt(STRING_KEY, 0);

        final ScaleAnimation animation = new ScaleAnimation(0.92f, 1.07f, 0.92f, 1.07f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);

        /*
        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setStartOffset(100);
        fadeOut.setDuration(1000);
        */

        cookie.setOnClickListener(v -> {
            cookie.startAnimation(animation);
            mediaPlayer.start();

            TextView add = new TextView(this);
            add.setId(View.generateViewId());
            add.setText("+" + cookiesPerClick);
            add.setTextSize(20);
            add.setTextColor(getResources().getColor(R.color.white));

            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            add.setLayoutParams(params);
            constraintLayout.addView(add);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(add.getId(), ConstraintSet.TOP, cookie.getId(), ConstraintSet.TOP);

            add.setX(cookie.getX() + (new Random().nextInt(700) + 100));
            add.setY(cookie.getY() + new Random().nextInt(700));

            ObjectAnimator move = ObjectAnimator.ofFloat(add, "translationY", -5f);
            move.setDuration(1000);
            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(add, "alpha", 1f, 0f);
            fadeOut.setDuration(1000);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(move, fadeOut);
            animatorSet.start();

            //constraintLayout.removeView(add);
            new AsyncThread().execute(cookies);

        });

        shop.setOnClickListener(v -> {
            int pos = (int) (Math.random() * 2) + 1;
            if (pos == 1){
                final RotateAnimation rotate = new RotateAnimation(0, 20, Animation.RELATIVE_TO_SELF
                        , 0.5f
                        , Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(500);
                shop.startAnimation(rotate);
            }
            else if (pos == 2) {
                final RotateAnimation rotate = new RotateAnimation(0, -20, Animation.RELATIVE_TO_SELF
                        , 0.5f
                        , Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(500);
                shop.startAnimation(rotate);
            }
            mediaPlayer.start();

            Intent intent = new Intent(MainActivity.this, Shop.class);
            intent.putParcelableArrayListExtra("oreos", (ArrayList<Oreos>) oreos);
            activityResultLauncher.launch(intent);

        });

        settings.setOnClickListener(v -> {
            final RotateAnimation rotateAnim = new RotateAnimation(0.0f, 180f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            rotateAnim.setDuration(250);
            settings.startAnimation(rotateAnim);
            mediaPlayer.start();

            if (!pressed) {
                pressed = true;
                popupWindow = new PopupWindow(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.settings_popup, null);
                popupWindow.setContentView(view);
                popupWindow.showAsDropDown(settings, 0, 0);

                SeekBar seekBar = view.findViewById(R.id.seekBar2);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                Button reset = view.findViewById(R.id.button4);
                reset.setOnClickListener(v1 -> {
                    SharedPreferences sharedPreferences1 = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                    editor1.clear();
                    editor1.apply();
                    cookies.set(0);
                    cookiesPerClick = 1;
                    textViewCookies.setText("0 Oreos");
                    textViewCookiesPerSecond.setText("0 OPS");
                    popupWindow.dismiss();
                    pressed = false;
                });
            }
            else {
                pressed = false;
                popupWindow.dismiss();
            }
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                SharedPreferences sharedPreferences1 = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
                cookies.set(sharedPreferences1.getInt("oreos", 0));
                if (sharedPreferences1.getInt("OPS", 0) != 0)
                    cookiesPerClick = sharedPreferences1.getInt("OPS", 0);
                textViewCookies.setText(sharedPreferences1.getInt("oreos", 0) + " Oreos");
                textViewCookiesPerSecond.setText(sharedPreferences1.getInt("OPS", 0) + " OPS");

                oreos = result.getData().getParcelableArrayListExtra("oreos");
            }
        });

    }

    public class AsyncThread extends android.os.AsyncTask<AtomicInteger, Void, AtomicInteger> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected AtomicInteger doInBackground(AtomicInteger... atomicIntegers) {
            incrementCookies(cookiesPerClick);

            return cookies;
        }

        @Override
        protected void onPostExecute(AtomicInteger add) {
            textViewCookies.setText(add.toString() + " Oreos");

            SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("oreos", add.get());
            editor.apply();

        }
    }
}