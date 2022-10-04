package exportkit.figma.assync_tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;

import exportkit.figma.ChattingActivity;
import exportkit.figma.showing_messages.MessageAndAnswer;

public final class AddMessageTask extends AsyncTask<String, Integer, Boolean> {

    private final ChattingActivity chattingActivity;
    MessageAndAnswer messageAndAnswer;

    public AddMessageTask(ChattingActivity chattingActivity) {

        messageAndAnswer = new MessageAndAnswer();
        this.chattingActivity = chattingActivity;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        String time = java.text.DateFormat.getTimeInstance().format(new Date());

        //Здесь нужно получить данные с сервера
        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        Log.d("addMessage", "enter postExecute");
        // create message from user input data and add to recycler view in MessageActivity
        MessageAndAnswer messageAndAnswer = new MessageAndAnswer();
        chattingActivity.addMessageToList(messageAndAnswer);
    }
}
