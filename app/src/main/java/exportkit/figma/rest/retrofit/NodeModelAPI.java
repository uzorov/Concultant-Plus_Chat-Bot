package exportkit.figma.rest.retrofit;


import exportkit.figma.rest.model.NodeModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NodeModelAPI {

    /**
     * Производство POST запроса по получению следующего сообщения.
     * Для первого обращения вызвать с параметроами: getNode("старт", "")
     * @param clientsAnswer - ответ пользователя на предыдущий вопрос
     * @param previousQuestion - вопрос, который был занят предыдущим
     * @return объект класса NodeModel, соответсвующий полученному сообщению
     */

    @FormUrlEncoded
    @POST("/getNextMessage")
    Call<NodeModel> getNode(@Field("clientsAnswer") String clientsAnswer,
                           @Field("previousQuestion") String previousQuestion);
}
