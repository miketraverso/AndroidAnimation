package traversoft.com.animationsample1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

public class SecondActivity extends Activity {

    ImageView imgMarshmallows;
    Button mButton;
    private static double TENSION = 800;
    private static double DAMPER = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imgMarshmallows = (ImageView)findViewById(R.id.imgMarshmallow);
        imgMarshmallows.setTranslationY(-100);

        mButton = (Button)findViewById(R.id.btnPressMe);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create a system to run the physics loop for a set of springs.
                SpringSystem springSystem = SpringSystem.create();
                SpringConfig config = new SpringConfig(TENSION, DAMPER);

                // Add a spring to the system.
                Spring spring = springSystem.createSpring();
                spring.setSpringConfig(config);
                spring.setCurrentValue(1);

                // Add a listener to observe the motion of the spring.
                spring.addListener(new SimpleSpringListener() {

                    @Override
                    public void onSpringUpdate(Spring spring) {

                        // You can observe the updates in the spring
                        // state by asking its current value in onSpringUpdate.
                        float value = (float) spring.getCurrentValue();
                        float scale = 1f - (value * 0.5f);
                        mButton.setScaleX(scale);
                        mButton.setScaleY(scale);
                    }

                    @Override
                    public void onSpringAtRest(Spring spring) {
                        mButton.setScaleX(1);
                        mButton.setScaleY(1);
                    }
                });

                spring.setEndValue(0);
            }
        });
    }
}
