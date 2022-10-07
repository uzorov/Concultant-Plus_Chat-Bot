package exportkit.figma.showing_messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exportkit.figma.rest.model.NodeModel;

public class MessageAndAnswer {

    String receivedText;
    String sendingTime;
    public static final int LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE = 0;
    public static final int RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE = 1;




    // the type of view
    private int viewType;


    public static final MessageAndAnswer[] SAMPLE_DATA = {
            new MessageAndAnswer("Статья 1", "15.04", MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
            new MessageAndAnswer("Статья 2", "16.04", MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
            new MessageAndAnswer("Статья 3", "18.04", MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
            new MessageAndAnswer("Статья 4", "17.04", MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
            new MessageAndAnswer("Статья 5", "14.04", MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
            new MessageAndAnswer("Сообщение 1", "12.04", MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
            new MessageAndAnswer("Сообщение 2", "12.04", MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
            new MessageAndAnswer("Сообщение 3", "12.04", MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
            new MessageAndAnswer("Сообщение 4", "12.04", MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
            new MessageAndAnswer("Сообщение 5", "12.04", MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE)
    };

    public MessageAndAnswer(){
    }


    public String getReceivedText() {
        return receivedText;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public int getViewType() {
        return viewType;
    }

    public MessageAndAnswer(String receivedText, String sendingTime, int viewType)
    {
        this.receivedText = receivedText;
        this.sendingTime = sendingTime;
        this.viewType = viewType;
    }


    public void setReceivedText(String receivedText) {
        this.receivedText = receivedText;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }
}
