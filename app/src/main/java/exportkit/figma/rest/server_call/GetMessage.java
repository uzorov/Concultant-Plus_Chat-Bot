package exportkit.figma.rest.server_call;

import android.util.Log;

import exportkit.figma.rest.model.NodeModel;
import exportkit.figma.rest.retrofit.NodeModelAPI;
import exportkit.figma.rest.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * класс вызова метода обращения к серверу,
 * для этого необходимо вызвать
 * GetMessage.getMessage();
 * ответ - текущее сообщение в виде объекта NodeModel.
 * Для первого вызова необходимо использовать перегруженный метод без параметров.
 */
public class GetMessage {

    public static NodeModel getMessage(String clientsAnswer, String previousQuestion) {
        final NodeModel[] nodeModelToReturn = new NodeModel[1];
        nodeModelToReturn[0] = new NodeModel();

        RetrofitService retrofitService = new RetrofitService();
        Call<NodeModel> call = retrofitService
                .getRetrofit()
                .create(NodeModelAPI.class)
                .getNode(clientsAnswer, previousQuestion);

        call.enqueue(new Callback<NodeModel>() {
            @Override
            public void onResponse(Call<NodeModel> call, Response<NodeModel> response) {
                if (response.isSuccessful()) {
                    NodeModel nodeModel = response.body();
                    nodeModelToReturn[0] = nodeModel;
                    Log.d("[INFO]:", nodeModel.toString());
                } else {
                    Log.d("[Error]:", "Ошибка ответа сервера");
                }
            }

            @Override
            public void onFailure(Call<NodeModel> call, Throwable t) {
                Log.d("[Error]:", "Ошибка обращения к серверу");
            }
        });
        return nodeModelToReturn[0];
    }


    public static NodeModel getMessage() {
        final NodeModel[] nodeModelToReturn = new NodeModel[1];
        nodeModelToReturn[0] = new NodeModel();

        RetrofitService retrofitService = new RetrofitService();
        Call<NodeModel> call = retrofitService
                .getRetrofit()
                .create(NodeModelAPI.class)
                .getNode("старт", "");

        call.enqueue(new Callback<NodeModel>() {
            @Override
            public void onResponse(Call<NodeModel> call, Response<NodeModel> response) {
                if (response.isSuccessful()) {
                    NodeModel nodeModel = response.body();
                    nodeModelToReturn[0] = nodeModel;
                    Log.d("[INFO]:", nodeModel.toString());
                } else {
                    Log.d("[Error]:", "Ошибка ответа сервера");
                }
            }

            @Override
            public void onFailure(Call<NodeModel> call, Throwable t) {
                Log.d("[Error]:", "Ошибка обращения к серверу");
            }
        });
        return nodeModelToReturn[0];
    }


}
