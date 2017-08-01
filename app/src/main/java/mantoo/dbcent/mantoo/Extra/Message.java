package mantoo.dbcent.mantoo.Extra;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class Message {
    public static void message(Context c, String m) {
        Toast.makeText(c, m, Toast.LENGTH_SHORT).show();
    }

    public static void snackBar(View view, String m) {
        Snackbar.make(view, m, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
}
