package traversoft.com.animationsample1;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView imgMarshmallows;
    MainActivity mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        imgMarshmallows = (ImageView)findViewById(R.id.imgMarshmallow);

        mInstance = this;
        startUpAnimation();
    }

    private void startUpAnimation() {

        final AnimatorSet setMoveUp = new AnimatorSet();
        setMoveUp.play(ObjectAnimator.ofFloat(imgMarshmallows, "translationY", 0, -100).setDuration(3000));

        final AnimatorSet setFadeIn = new AnimatorSet();
        setFadeIn.play(ObjectAnimator.ofFloat(imgMarshmallows, "alpha", 0, 1).setDuration(5000));

        final AnimatorSet fullAnimation = new AnimatorSet();
        fullAnimation.playTogether(setMoveUp, setFadeIn);

        fullAnimation.start();

        fullAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                Intent i = new Intent(mInstance, SecondActivity.class);
                startActivity(i);
                MainActivity.this.finish();
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
