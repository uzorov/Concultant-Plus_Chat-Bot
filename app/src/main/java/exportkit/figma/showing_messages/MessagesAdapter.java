package exportkit.figma.showing_messages;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Node;

import exportkit.figma.ChattingActivity;
import exportkit.figma.R;
import exportkit.figma.rest.model.NodeModel;

public class MessagesAdapter
        extends RecyclerView.Adapter {

    Context context;

    public MessagesAdapter() {
    }

    public MessagesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NodeModel.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE: // create left bubble
                View leftLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.left_chat_bubble_layout, parent, false);
                return new LeftChatViewHolder(leftLayoutView);
            case NodeModel.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE: // create right bubble
                View rightLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.right_chat_bubble_layout, parent, false);
                return new RightChatViewHolder(rightLayoutView);
            default:
                return null;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Answer answer = Answer.SAMPLE_DATA[position];

        switch (answer.viewType) {
            case NodeModel.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE:
                LeftChatViewHolder lcViewHolder = (LeftChatViewHolder) holder;
                String senderText = "Чат-бот";
                if (context != null) {
                    senderText = context.getResources().getString(R.string.bots_name);
                }
                lcViewHolder.setTexts(answer.answer, answer.sendingTime, senderText);
                break;
            case NodeModel.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE:
                //RightChatViewHolder rcViewHolder = (RightChatViewHolder) holder;
                //rcViewHolder.setTexts(chatBubbleText, detailsText);
                // only able to delete own messages
                //rcViewHolder.setTrashIconHandler(recyclerItem.getMsgId());
                break;
            default:
                return;
        }
    }


    @Override
    public int getItemCount() {
        return Answer.SAMPLE_DATA.length;
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