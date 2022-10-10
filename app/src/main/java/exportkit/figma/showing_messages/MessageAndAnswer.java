package exportkit.figma.showing_messages;

public class MessageAndAnswer {

    String receivedText;
    String sendingTime;
    public static final int LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE = 0;
    public static final int RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE = 1;




    // the type of view
    private int viewType;


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
