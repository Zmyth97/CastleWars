package com.desitum.castleWars.android;


import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.android.vending.billing.IInAppBillingService;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.desitum.castleWars.CastleWars;
import com.desitum.castleWars.GooglePlayServicesInterface;
import com.desitum.castleWars.data.Settings;
import com.desitum.castleWars.world.GameWorld;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AndroidLauncher extends AndroidApplication implements GooglePlayServicesInterface,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private static final int REQUEST_CODE_RESOLVE_ERR = 9000;
    private static final int REQUEST_ACHIEVEMENTS = 20002;

    private static final String TAG = "GooglePlayServicesActivity";

    private static final String KEY_IN_RESOLUTION = "is_in_resolution";

    private static final String FIRE_PACK_SKU = "flame_card_pack_id";
    private static final String JAPANESE_PACK_SKU = "japanese_card_pack_id";
    private static final String EXTRA_SLOT_1_SKU = "extra_slot_1_id";
    private static final String EXTRA_SLOT_2_SKU = "extra_slot_2_id";

    private ConnectionResult mConnectionResult;

    /**
     * Request code for auto Google Play Services error resolution.
     */
    protected static final int REQUEST_CODE_RESOLUTION = 1;

    /**
     * Google API client.
     */
    private GoogleApiClient mGoogleApiClient;
    /**
     * Determines if the client is in a resolution state, and
     * waiting for resolution intent to return.
     */
    private boolean mIsInResolution;

    //In App Purchase Code
    IInAppBillingService mService;
    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {
            System.out.println("In App Purchase Service Connected");
            mService = IInAppBillingService.Stub.asInterface(service);
        }
    };

    protected View gameView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mIsInResolution = savedInstanceState.getBoolean(KEY_IN_RESOLUTION, false);
        }
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        config.useAccelerometer = false;
        config.useCompass = false;
        config.useWakelock = false;

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        //In App Purchases Code
        System.out.println("Start Store Services");
        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);

        View gameView = createGameView(config);
        layout.addView(gameView);

        setContentView(layout);
    }


    @Override
    public void getLeaderBoard() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient, "12345"), 100);
        } else {
            //Nothing!
        }
    }

    @Override
    public void submitScore(int score) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Leaderboards.submitScore(mGoogleApiClient, "12345", score);
        }
        else{
            //Nothing!
        }
    }

    @Override
    public void unlockAchievement(int achievement) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            if (achievement == CastleWars.AN_ERA_BEGINS) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_an_era_begins));
            } else if (achievement == CastleWars.RAIDER) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_raider));
            } else if (achievement == CastleWars.PILLAGER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_pillager), 1);
            } else if (achievement == CastleWars.TEMPLAR) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_templar), 1);
            } else if (achievement == CastleWars.CRUSADER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_crusader), 1);
            }else if (achievement == CastleWars.DESTROYER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_destroyer), 1);
            } else if (achievement == CastleWars.BEGINNER_BUILDER) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_beginner_builder));
            } else if (achievement == CastleWars.NOVICE_BUILDER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_novice_builder), 1);
            } else if (achievement == CastleWars.ADVANCED_BUILDER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_advanced_builder), 1);
            }else if (achievement == CastleWars.EXPERT_BUILDER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_expert_builder), 1);
            } else if (achievement == CastleWars.MASTER_BUILDER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_master_builder), 1);
            } else if (achievement == CastleWars.DO_IT_YOURSELF) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_do_it_yourself));
            } else if (achievement == CastleWars.SILENT_BUT_DEADLY) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_silent_but_deadly));
            }else if (achievement == CastleWars.WORLD_CONQUEST) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_world_conquest));
            } else if (achievement == CastleWars.PILLAGED) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_pillaged));
            } else if (achievement == CastleWars.FLAMING_NINJA) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_flaming_ninja));
            } else if (achievement == CastleWars.BUILDER) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_builder));
            }else if (achievement == CastleWars.ATTACKER) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_attacker));
            } else if (achievement == CastleWars.CLOUD_WATCHER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_cloud_watcher), 1);
            } else if (achievement == CastleWars.DIDNT_SEE_THAT_COMING) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_didnt_see_that_coming));
            } else if (achievement == CastleWars.CASTLE_MASTER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_castle_master), 1);
            }else if (achievement == CastleWars.FEUDAL_JAPAN) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_feudal_japan));
            } else if (achievement == CastleWars.DEATH_BY_FIRE) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_death_by_fire));
            } else if (achievement == CastleWars.NIGHT_KILLER) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_night_killer), 1);
            } else if (achievement == CastleWars.HONORABLE_SACRIFICE) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_honorable_sacrifice), 1);
            }else if (achievement == CastleWars.JAPANESE_MASTER) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_japanese_master));
            } else if (achievement == CastleWars.BURN_IT_ALL) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_burn_it_all));
            } else if (achievement == CastleWars.DEATH_FROM_ABOVE) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_death_from_above));
            } else if (achievement == CastleWars.REBORN) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_reborn), 1);
            }else if (achievement == CastleWars.LET_IT_FLOW) {
                Games.Achievements.increment(mGoogleApiClient, getString(R.string.achievement_let_it_flow), 1);
            } else if (achievement == CastleWars.ELEMENTALIST) {
                Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_elementalist));
            } else {
                //Nothing!
            }
        }
    }

    @Override
    public void makePurchase(String sku){

        System.out.println("Trying sku: " + sku);
        int value = 1001;
        try {
            Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(), sku, "inapp", "");

            PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
            try {
                startIntentSenderForResult(pendingIntent.getIntentSender(), value, new Intent(), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0));
            } catch (IntentSender.SendIntentException exception) {
                System.out.println(exception);
            }
        } catch (RemoteException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void checkForPurchasesMade(){

    }

    @Override
    public void showAchievements(){
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            startActivityForResult(Games.Achievements.getAchievementsIntent(mGoogleApiClient), REQUEST_ACHIEVEMENTS);
        } else {
           if(mGoogleApiClient != null){
               mGoogleApiClient.connect();
           }
        }
    }

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }


    @Override
    public void shareRegularScore(int score) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "I just got " + score + " on Template! Try to beat me: https://play.google.com/store/apps/details?id=com.desitum.template.android");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }



    private View createGameView(AndroidApplicationConfiguration cfg) {
        gameView = initializeForView(new CastleWars(this), cfg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        gameView.setLayoutParams(params);
        return gameView;
    }


    /**
     * Called when the Activity is made visible.
     * A connection to Play Services need to be initiated as
     * soon as the activity is visible. Registers {@code ConnectionCallbacks}
     * and {@code OnConnectionFailedListener} on the
     * activities itself.
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Games.API)
                    .addScope(Games.SCOPE_GAMES)
                            // Optionally, add additional APIs and scopes if required.
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    /**
     * Called when activity gets invisible. Connection to Play Services needs to
     * be disconnected as soon as an activity is invisible.
     */
    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    /**
     * Saves the resolution state.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IN_RESOLUTION, mIsInResolution);
    }

    /**
     * Handles Google Play Services resolution callbacks.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_RESOLVE_ERR && resultCode == RESULT_OK) {
            mConnectionResult = null;
            mGoogleApiClient.connect();
        }

        if (requestCode == 1001) {
            System.out.println("Finished Purchase, Result Code 1001, Fire Pack");
            int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
            String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
            String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");

            if (resultCode == RESULT_OK) {
                try {
                    JSONObject jo = new JSONObject(purchaseData);
                    String sku = jo.getString("productId");
                    System.out.println("You have bought the " + sku + ". Excellent choice!");
                    if (sku.equals(FIRE_PACK_SKU)) {
                        Settings.saveCardPacks(true, Settings.BOUGHT_JAPANESE_PACK);
                        Settings.savePackSettings(2, true, Settings.WANTS_JAPANESE_CARDS);
                    }
                    else if (sku.equals(JAPANESE_PACK_SKU)){
                        Settings.saveCardPacks(Settings.BOUGHT_FlAME_PACK, true);
                        Settings.savePackSettings(3, Settings.WANTS_FLAME_CARDS, true);
                    }
                    else if (sku.equals(EXTRA_SLOT_1_SKU)) {
                        Settings.saveExtraSlots(true, false);
                    }
                    else if (sku.equals(EXTRA_SLOT_2_SKU)) {
                        Settings.saveExtraSlots(true, true);
                    }
                }
                catch (JSONException e) {
                    System.out.println("Failed to parse purchase data.");
                    e.printStackTrace();
                }
            }
        }
    }

    private void retryConnecting() {
        mIsInResolution = false;
        if (!mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }

    /**
     * Called when {@code mGoogleApiClient} is connected.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "GoogleApiClient connected");
        // TODO: Start making API requests.
    }

    /**
     * Called when {@code mGoogleApiClient} connection is suspended.
     */
    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "GoogleApiClient connection suspended");
        retryConnecting();
    }

    /**
     * Called when {@code mGoogleApiClient} is trying to connect but failed.
     * Handle {@code result.getResolution()} if there is a resolution
     * available.
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
            } catch (IntentSender.SendIntentException e) {
                mGoogleApiClient.connect();
            }
        }
        // Save the result and resolve the connection failure upon a user click.
        mConnectionResult = result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            unbindService(mServiceConn);
        }
    }
}


