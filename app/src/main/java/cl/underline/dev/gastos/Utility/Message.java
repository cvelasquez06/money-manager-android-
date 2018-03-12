package cl.underline.dev.gastos.Utility;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by cvelasquez@underline.cl developer on 14-02-2018.
 */

public class Message {
    public static Toast messagesToast(Activity activity, String message){
        Context context = activity.getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        return toast;
    }
}
