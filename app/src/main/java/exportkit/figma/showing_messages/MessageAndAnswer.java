package exportkit.figma.showing_messages;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import exportkit.figma.R;
import exportkit.figma.showing_variants.Variant;

public class MessageAndAnswer {

    String receivedText;
    String sendingTime;
    public static final int LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE = 0;
    public static final int RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE = 1;

    private boolean isFileImageExist;



    public boolean thisNodeIsDocument()
    {
        return isFileImageExist;
    }



  //  public static final MessageAndAnswer[] FILES = {
    //        new MessageAndAnswer("Договор аренды нежилого помещения", false , LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
      //      new MessageAndAnswer("Договор на оказание репетиторских услуг", Drawable.createFromPath("drawable://" + R.drawable.file_ic), LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE),
        //    new MessageAndAnswer("Договор по созданию текстовых материалов", Drawable.createFromPath("drawable://" + R.drawable.file_ic), LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE)
   // };

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
        this.isFileImageExist = false;
    }

    public MessageAndAnswer(String receivedText, String sendingTime, boolean isFileImageExist, int viewType) {
        this.receivedText = receivedText;
        this.sendingTime = sendingTime;
        this.isFileImageExist = isFileImageExist;
        this.viewType = viewType;
    }

    public void setReceivedText(String receivedText) {
        this.receivedText = receivedText;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }
}
