package exportkit.figma.assync_tasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import exportkit.figma.ChattingActivity;
import exportkit.figma.R;
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

                String time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                MessageAndAnswer messageAndAnswerNode = new MessageAndAnswer(answer, time, nodeModel.thisNodeIsDocument(), MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE);

                Log.d("thisNodeIsDocument", "thisNodeIsDocument value is " + nodeModel.thisNodeIsDocument());

                messageAndAnswer.add(messageAndAnswerNode);

            }





        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        Log.d("addMessage", "enter postExecute");
        // create message from user input data and add to recycler view in MessageActivity

       if (!nodeModel.variants.isEmpty()) chattingActivity.clearVariants();
        for (String variant : nodeModel.variants) {
            chattingActivity.addVariantToList(new Variant(variant));
        }


            chattingActivity.setPrevLastNodeModel(chattingActivity.getLastNodeModel());
            chattingActivity.setLastNodeModel(nodeModel);

        for (MessageAndAnswer messgeAndAns : messageAndAnswer)
            chattingActivity.addMessageToList(messgeAndAns);
       // if (!variants.isEmpty()) new AddVariantsTask(chattingActivity, variants).execute();

    }
}
