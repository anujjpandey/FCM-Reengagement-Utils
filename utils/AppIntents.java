package c.testfcm.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by wildcoder on 10/08/16.
 */
public class AppIntents {

    public static String PLAY_STORE_BASE_URL = "https://play.google.com/store/apps/details?id=";

    /**
     * @param activity
     * @param title    Sharing Title
     * @param message  Share Message
     */
    public static void shareApp(Activity activity, String title, String message) {
        if (message == null)
            message = "";
        message = message.trim() + " " + PLAY_STORE_BASE_URL + activity.getPackageName();
        Intent _share_intent = new Intent(Intent.ACTION_SEND);
        _share_intent.setType("text/plain");
        _share_intent.putExtra(Intent.EXTRA_SUBJECT, title);
        _share_intent.putExtra(Intent.EXTRA_TEXT, "\n" + message);
        activity.startActivity(_share_intent);
    }

    /**
     * @param activity
     * @param accountName
     */
    public static void moreApps(Activity activity, String accountName) {
        String publisherId = "pub:" + accountName;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://search?q=" + publisherId));
            activity.startActivity(intent);
        } catch (Exception e) { //google play app is not installed
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/search?q=" + publisherId));
            activity.startActivity(intent);
        }
    }


    /**
     * @param activity
     * @param pName
     */
    public static void openAppInPlayStore(Activity activity, String pName) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + pName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                    PLAY_STORE_BASE_URL + pName)));
        }
    }

    /**
     * @param activity
     * @param pageId
     */
    public static void openFBPage(Activity activity, String pageId) {
        String url = "";
        PackageManager packageManager = activity.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                url = "fb://facewebmodal/f?href=https://www.facebook.com/" + pageId;
            } else { //older versions of fb app
                url = "fb://page/" + pageId;
            }
        } catch (PackageManager.NameNotFoundException e) {
            url = "fb://facewebmodal/f?href=https://www.facebook.com/" + pageId;
        }

        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        facebookIntent.setData(Uri.parse(url));
        activity.startActivity(facebookIntent);
    }
}
