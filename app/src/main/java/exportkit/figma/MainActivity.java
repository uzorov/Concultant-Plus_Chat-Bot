package exportkit.figma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import exportkit.figma.database.InitDatabase;
import exportkit.figma.fragments.InvitationToTheChatFragment;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        ShowFragmentTask showFragmentTask = new ShowFragmentTask();
        showFragmentTask.execute();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {

            try {
                String command = "ping -c 1 google.com";
                ChattingActivity.isInternetAvailable = (Runtime.getRuntime().exec(command).waitFor() == 0);
            } catch (Exception e) {
                Log.d("TEST", "Exception: " + e);
            }

            handler.post(() -> {
                //UI Thread work here
            });
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.gray));
        //???????????????????? ???????? ????????????
        //new InitDatabase().addData();
    }




    public class ShowFragmentTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Fragment invitFragment = new InvitationToTheChatFragment();

            // Create new fragment and transaction
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);

            transaction.add(R.id.fragment_container_view, invitFragment, null);

// Commit the transaction

            transaction.commit();

        }
    }
}



