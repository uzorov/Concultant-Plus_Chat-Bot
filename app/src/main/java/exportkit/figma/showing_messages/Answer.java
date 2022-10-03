package exportkit.figma.showing_messages;

import exportkit.figma.rest.model.NodeModel;

public class Answer {

    String answer;
    String sendingTime;

    int viewType = 0;

    public static final Answer[] SAMPLE_DATA = {
            new Answer("Статья 1", "15.04"),
            new Answer("Статья 2", "16.04"),
            new Answer("Статья 3", "18.04"),
            new Answer("Статья 4", "17.04"),
            new Answer("Статья 5", "14.04"),
            new Answer("Статья 6", "12.04"),
            new Answer("Статья 7", "15.34"),
            new Answer("Статья 8", "11.44"),
            new Answer("Статья 9", "15.54"),
            new Answer("Статья 10", "03.44"),
            new Answer("Статья 11", "02.14"),
            new Answer("Статья 12", "01.04"),
            new Answer("Статья 13", "00.04"),
            new Answer("Статья 14", "00.00"),
            new Answer("Статья 15", "08.03")
    };

    public Answer(){
    }


    public Answer(String answer, String sendingTime)
    {
        this.answer = answer;
        this.sendingTime = sendingTime;
        this.viewType = NodeModel.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE;
    }
}
