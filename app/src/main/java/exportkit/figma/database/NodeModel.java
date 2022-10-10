package exportkit.figma.database;



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


    public String previousQuestion;

    public String clientsAnswer;

    public List<String> answer;

    public List<String> variants;

    public NodeModel(String previousQuestion, String clientsAnswer, List<String> answer, List<String> variants) {
        this.previousQuestion = previousQuestion;
        this.clientsAnswer = clientsAnswer;
        this.answer = answer;
        this.variants = variants;
    }

    public NodeModel() {}




    public List<String> getVariants() {
        return variants;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public String getClientsAnswer() {
        return clientsAnswer;
    }

    public String getPreviousQuestion() {
        return previousQuestion;
    }

    public void setAnswer(List<String> answer) {
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


    @Override
    public String toString() {
        return "NodeModel{" +
                ", previousQuestion='" + previousQuestion + '\'' +
                ", clientsAnswer='" + clientsAnswer + '\'' +
                ", answer='" + answer + '\'' +
                ", variants=" + variants +
                '}';
    }
}
