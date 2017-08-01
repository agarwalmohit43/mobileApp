package mantoo.dbcent.mantoo.Animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by dbcent91 on 24/7/17.
 */

public class AnimationUtil {

    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown) {

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown == true ? 200 : -200, 0);
        animatorTranslateY.setDuration(1000);

        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -50, 50, -30, 30, -20, 20, -5, 5, 0);
        animatorTranslateX.setDuration(1000);

        //animatorSet.playTogether(animatorTranslateY,animatorTranslateX);

        animatorSet.play(animatorTranslateY);
        animatorSet.start();
    }
}
