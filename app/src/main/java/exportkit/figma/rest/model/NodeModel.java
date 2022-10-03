package exportkit.figma.rest.model;



import java.util.List;

/**
 * Объект сообщения. Поля:
 * @previousQuestion - предыдущий вопрос, на который ответил пользователь,
 * @clientsAnswer - ответ пользователя на этот вопрос
 * @answer - наш ответ (новое сообщений, которое мы покажем пользователю)
 * @variants - список доступных действий для пользователя (Да/Нет выбор из нескольких и т.д.)
 * Если в @variants только одно значение "main page", то вепнуть пользователя в самое начало приложения
 */


public class NodeModel {
    public static final int LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE = 0;
    public static final int RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE = 1;


    // the type of view
    private int viewType;

    public String previousQuestion;

    public String clientsAnswer;

    public String answer;

    public List<String> variants;

    public NodeModel() {}




    public List<String> getVariants() {
        return variants;
    }

    public String getAnswer() {
        return answer;
    }

    public String getClientsAnswer() {
        return clientsAnswer;
    }

    public String getPreviousQuestion() {
        return previousQuestion;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setClientsAnswer(String clientsAnswer) {
        this.clientsAnswer = clientsAnswer;
    }

    public void setPreviousQuestion(String previousQuestion) {
        this.previousQuestion = previousQuestion;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

}
