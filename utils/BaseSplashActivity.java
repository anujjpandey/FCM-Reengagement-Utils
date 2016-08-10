package c.testfcm.utils;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import c.testfcm.BuildConfig;


public abstract class BaseSplashActivity extends AppCompatActivity {


    /**
     * @description write your splash screen main functions here
     */
    protected abstract void callWithDelay();

    /**
     * @description this will called when splash is already hold for some action so no need to hold more
     */
    protected abstract void callWithoutDelay();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if (b != null && b.containsKey(Constants.Extras.ACTION)) {
            openAction(b);
        } else {
            callWithDelay();
        }
    }

    /**
     * @param bundle
     */
    private void openAction(Bundle bundle) {
        String type = bundle.getString(Constants.Extras.ACTION);
        switch (type) {
            //Reading specific quote

            //Say user to share your app
            case Constants.Actions.SHARE_APP:
                String title = bundle.getString(Constants.Extras.TITLE, "Hope you love it");
                String shareMessage = bundle.getString(Constants.Extras.MESSAGE, "Check this app");
                AppIntents.shareApp(this, title, shareMessage);
                delayedFinish();
                break;

            // check if notification is for more app from the account
            case Constants.Actions.TRY_OUR_MORE_APP:
                String accountName = bundle.getString(Constants.Extras.ACCOUNT_NAME, "");
                AppIntents.moreApps(this, accountName);
                delayedFinish();
                break;

            //check if notification for rating the app
            case Constants.Actions.RATE_APP:
                AppIntents.openAppInPlayStore(this, getPackageName());
                delayedFinish();
                break;

            //open any app to play store
            case Constants.Actions.TRY_OUR_OTHER_APP:
                String pName = bundle.getString(Constants.Extras.P_NAME, getPackageName());
                AppIntents.openAppInPlayStore(this, pName);
                delayedFinish();
                break;


            case Constants.Actions.FOLLOW_FB_PAGE:
                String pageId = bundle.getString(Constants.Extras.PAGE_ID, "");
                AppIntents.openFBPage(this, pageId);
                delayedFinish();
                break;

            //Send user to update his/her app
            case Constants.Actions.UPDATE_APP:
                try {
                    String stringVersion = bundle.getString(Constants.Extras.UPDATE, "");
                    Version version = new Version(stringVersion);
                    checkForUpdate(version);
                } catch (Exception e) {
                    Log.e("", "Notification error:" + e.getMessage());
                }
                break;


            default:
                callWithDelay();
                break;
        }
    }


    private void checkForUpdate(Version version) {
        if (version == null) {
            callWithDelay();
            return;
        }
        if (version.getVersionCode() > BuildConfig.VERSION_CODE)
            openUpdateDialog(version);
        else {
            //this will be possible if and only if the app is updated
            Toast.makeText(this, "Your app is up to date", Toast.LENGTH_SHORT).show();
            callWithDelay();
        }
    }

    /**
     * @param version
     */
    private void openUpdateDialog(Version version) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(version.getTitle());
        dialog.setCancelable(false);
        if (!version.isMandatory()) {
            dialog.setNegativeButton("Later",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            callWithoutDelay();
                        }
                    });

        }
        dialog.setMessage(version.getMessage());
        dialog.setPositiveButton("Update",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        AppIntents.openAppInPlayStore(BaseSplashActivity.this, getPackageName());
                        finish();
                    }
                });
        AlertDialog alert = dialog.create();
        alert.show();
    }


    /***
     * @description finish this with a short delay
     */
    private void delayedFinish() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 100);
    }
}
