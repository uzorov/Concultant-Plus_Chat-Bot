package exportkit.figma.rest.server_call;

import android.util.Log;

import exportkit.figma.ChattingActivity;
import exportkit.figma.assync_tasks.AddMessageTask;
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

    public static NodeModel getMessage(String clientsAnswer, String previousQuestion, ChattingActivity chattingActivity) {

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
                    new AddMessageTask(chattingActivity, nodeModel).execute();
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
        return new NodeModel();
    }


    public static NodeModel getMessage(ChattingActivity chattingActivity) {
        NodeModel[] nodeModelToReturn = new NodeModel[1];
        nodeModelToReturn[0] = new NodeModel();
        NodeModel[] nodeModel = new NodeModel[1];
        RetrofitService retrofitService = new RetrofitService();
        Call<NodeModel> call = retrofitService
                .getRetrofit()
                .create(NodeModelAPI.class)
                .getNode("старт", "");

        call.enqueue(new Callback<NodeModel>() {
            @Override
            public void onResponse(Call<NodeModel> call, Response<NodeModel> response) {
                if (response.isSuccessful()) {
                    nodeModel[0] = response.body();
                    AddMessageTask addMessageTask = new AddMessageTask(chattingActivity, nodeModel[0]);
                    addMessageTask.execute();

                } else {
                    Log.d("[Error]:", "Ошибка ответа сервера");
                }
            }

            @Override
            public void onFailure(Call<NodeModel> call, Throwable t) {
                Log.d("[Error]:", "Ошибка обращения к серверу");
            }
        });
        Log.d("testing:", nodeModelToReturn[0].toString());
       // Log.d("testing:", nodeModel[0].toString());
        return nodeModelToReturn[0];
    }


}
