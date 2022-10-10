package exportkit.figma.assync_tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exportkit.figma.ChattingActivity;
import exportkit.figma.database.NodeModel;
import exportkit.figma.showing_messages.MessageAndAnswer;
import exportkit.figma.showing_variants.Variant;

public final class AddMessageTask extends AsyncTask<String, Integer, Boolean> {

    private final ChattingActivity chattingActivity;
    List<MessageAndAnswer> messageAndAnswer = new ArrayList<>();
    List<Variant> variants = new ArrayList<>();
    NodeModel nodeModel = new NodeModel();

    public AddMessageTask(ChattingActivity chattingActivity, NodeModel nodeModel) {
        this.chattingActivity = chattingActivity;
        this.nodeModel = nodeModel;
    }

    @Override
    protected Boolean doInBackground(String... strings) {


        for (String answer : nodeModel.answer) {
            String time = java.text.DateFormat.getTimeInstance().format(new Date());
           messageAndAnswer.add(new MessageAndAnswer(answer, time, MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE));

        }

        for (String variant : nodeModel.variants)
        {
            variants.add(new Variant(variant));
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        Log.d("addMessage", "enter postExecute");
        // create message from user input data and add to recycler view in MessageActivity
        for (MessageAndAnswer messgeAndAns : messageAndAnswer)
        chattingActivity.addMessageToList(messgeAndAns);
        if (!variants.isEmpty()) new AddVariantsTask(chattingActivity, variants).execute();

    }
}
