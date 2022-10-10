package exportkit.figma.showing_messages;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import exportkit.figma.ChattingActivity;
import exportkit.figma.R;

public class MessagesAdapter
        extends RecyclerView.Adapter {


    ChattingActivity chattingActivity;

    public MessagesAdapter() {
    }

    public MessagesAdapter(ChattingActivity context) {
        this.chattingActivity = context;
    }

    @Override
    public int getItemViewType(int position) {
        // get an item from the recycler view and determine whether it should be a 'left bubble' or 'right bubble'
        MessageAndAnswer messageAndAnswer = chattingActivity.getMessageAndAnswer(position);
        if (messageAndAnswer == null) {
            Log.d("adapter_debug", "message and answer is NULL!");
            return -1;
        }
        switch (messageAndAnswer.getViewType()) {
            case 0:
                Log.d("adapter_debug", "message and answer is type 0!");
                return MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE;
            case 1:
                Log.d("adapter_debug", "message and answer is type 1!");
                return MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE;
            default:
                Log.d("adapter_debug", "message and answer is default type -1!");
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE: // create left bubble
                View leftLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.left_chat_bubble_layout, parent, false);
                return new LeftChatViewHolder(leftLayoutView);
            case MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE: // create right bubble
                View rightLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.right_chat_bubble_layout, parent, false);
                return new RightChatViewHolder(rightLayoutView);
            default:
                Log.d("adapter_debug", "onCreate returning NULL!");
                return null;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageAndAnswer messageAndAnswer = chattingActivity.getMessageAndAnswer(position);

        switch (messageAndAnswer.getViewType()) {
            case MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE:
                LeftChatViewHolder lcViewHolder = (LeftChatViewHolder) holder;
                String senderText = "Чат-бот";
                if (chattingActivity != null) {
                    senderText = chattingActivity.getResources().getString(R.string.bots_name);
                }

                Log.d("adapter_debug", "setting text for left bubble");
                lcViewHolder.setTexts(

                        messageAndAnswer.getReceivedText(),
                        messageAndAnswer.getSendingTime(),
                        senderText);
                break;
            case MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE:
                RightChatViewHolder rcViewHolder = (RightChatViewHolder) holder;
                Log.d("adapter_debug", "setting text for right bubble");
                rcViewHolder.setTexts(messageAndAnswer.getReceivedText(),
                        messageAndAnswer.getSendingTime());
                break;
            default:
                return;
        }
    }


    @Override
    public int getItemCount() {
        Log.d("adapter_debug", "count of items returned: " +
                chattingActivity.getMessagesAndAnswersList().size());
        return chattingActivity.getMessagesAndAnswersList().size();
    }

}


/**
 * Class for ViewHolder for the left chat layout
 */
class RightChatViewHolder extends RecyclerView.ViewHolder {
    // the components in the right bubbles of the recycler view
    View view;
    private CardView chatBubbleCardView;
    private TextView chatBubbleTextView;
    private TextView detailsTextView;

    /**
     * Constructor
     *
     * @param itemView
     */
    public RightChatViewHolder(@NonNull View itemView) {
        super(itemView);
        // find the components
        view = itemView;
        chatBubbleTextView = itemView.findViewById(R.id.right_recycler_textview);
        chatBubbleCardView = itemView.findViewById(R.id.right_recycler_cardview);
        detailsTextView = itemView.findViewById(R.id.right_details_textview);
    }

    /**
     * Set the texts of the right chat bubble
     *
     * @param chatText
     * @param detailsText
     */
    public void setTexts(String chatText, String detailsText) {
        chatBubbleTextView.setText(chatText);
        detailsTextView.setText(detailsText);
    }
}


class LeftChatViewHolder extends RecyclerView.ViewHolder {
    // the components in the left bubbles of the recycler view
    private CardView chatBubbleCardView;
    private TextView chatBubbleTextView;
    private TextView detailsTextView;
    private TextView senderTextView;

    /**
     * Constructor
     *
     * @param itemView
     */
    public LeftChatViewHolder(@NonNull View itemView) {
        super(itemView);
        // find the components
        chatBubbleTextView = itemView.findViewById(R.id.left_recycler_textview);
        chatBubbleCardView = itemView.findViewById(R.id.left_recycler_cardview);
        detailsTextView = itemView.findViewById(R.id.left_details_textview);
        senderTextView = itemView.findViewById(R.id.left_sender_textview);
    }

    /**
     * Set the texts of the left chat bubble
     *
     * @param chatText
     * @param detailsText
     */
    public void setTexts(String chatText, String detailsText, String sender) {
        chatBubbleTextView.setText(chatText);
        detailsTextView.setText(detailsText);
        senderTextView.setText(sender);
    }
}