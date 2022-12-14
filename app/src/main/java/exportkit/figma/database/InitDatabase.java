package exportkit.figma.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exportkit.figma.ChattingActivity;
import exportkit.figma.assync_tasks.AddMessageTask;

public class InitDatabase {
    ChattingActivity chattingActivity;
    DatabaseReference mDatabase;
    String TAG = "dataTest";
    FirebaseFirestore db;

    public InitDatabase() {
        db = FirebaseFirestore.getInstance();
    }

    public InitDatabase(ChattingActivity chattingActivity, boolean isInternetConnectionAvailable) {
        this.chattingActivity = chattingActivity;

        //Проверяем доступен ли интернет, если нет - выводим сообщение пользователю
        if (isInternetConnectionAvailable)
            db = FirebaseFirestore.getInstance();
        else
            db = null;
        Log.d("dataTest", "DataBase init");
        FirebaseFirestore.setLoggingEnabled(true);
    }

    public void addData() {





        String prevQuestion = "Что Вас интересует?";
        String clientsAnswer = "О нас";
        List<String> answers = new ArrayList<>();
        answers.add("С 2017 г. был введен статус самозанятого, но этот статус вызывает много вопросов у граждан, желающих заниматься предпринимательской деятельностью.");
        answers.add("Цель нашего проекта: \n" +
                "- помочь гражданам разобраться в законодательстве о самозанятых,\n" +
                "- узнать, какие налоги необходимо платить самозанятому,\n" +
                "- узнать о + и - статуса самозанятого,\n" +
                "- получить готовые шаблоны договоров, которые можно использовать самозанятому");
        answers.add("Напоминаем:\n" +
                "Наш проект не “панацея” и не может охватить все вопросы граждан, мы освещаем только основные особенности статуса самозанятого.  \n" +
                "Информация из чат-бота основана на актуальных законах.\n" +
                "Материалы носят рекомендательный характер, и мы не несем ответственность за действия граждан на основе материала из чат-бота.");
        List<String> variants = new ArrayList<>();
        variants.add("Вернуться на главную страницу");



        // Create a new user with a first and last name
        Map<String, Object> nodeModel1 = new HashMap<>();
        nodeModel1.put("prevQuestion", prevQuestion);
        nodeModel1.put("clientsAnswer", clientsAnswer);
        nodeModel1.put("answers", answers);
        nodeModel1.put("variants", variants);


// Add a new document with a generated ID
        db.collection(ChattingActivity.ABOUT_PATH)
                .add(nodeModel1)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });


        Log.d("dataTest", "Methods end");
    }

    public void readData(String clientsAnswer, String previousQuestion, final String path) {

        if (db != null) {
            db.collection(path)
                    .whereEqualTo("clientsAnswer", clientsAnswer)
                    .whereEqualTo("prevQuestion", previousQuestion)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());

                                    String clAnswer = document.getData().get("clientsAnswer").toString();
                                    String prvQuestion = document.getData().get("prevQuestion").toString();
                                    List<String> answrs = (List<String>) document.getData().get("answers");
                                    List<String> variants = (List<String>) document.getData().get("variants");

                                    Log.d(TAG, clAnswer + " " + prvQuestion + " " + answrs + " " + variants);
                                    NodeModel nodeModel = new NodeModel(prvQuestion, clAnswer, answrs, variants);

                                    AddMessageTask addMessageTask = new AddMessageTask(chattingActivity, nodeModel);
                                    addMessageTask.execute();
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        } else {
            String clAnswer = "";
            String prvQuestion = "";
            List<String> answrs = new ArrayList<>();
            answrs.add("Кажется у Вас проблемы с интернетом...");
            answrs.add("Проверьте, не подключёно ли ваше устройство к VPN.");
            answrs.add("Попробуйте проверить подключение ещё раз и перезапустите приложение.");
            answrs.add("Если Вы уверены, что Ваше интернет соединение стабильно, выберите пункт: " +
                    "\"Обратная связь\" и напишите о возникшей проблеме команде разработчиков");
            List<String> variants = new ArrayList<>();

            variants.add("Обратная связь");

            Log.d(TAG, clAnswer + " " + prvQuestion + " " + answrs + " " + variants);
            NodeModel nodeModel = new NodeModel(prvQuestion, clAnswer, answrs, variants);

            AddMessageTask addMessageTask = new AddMessageTask(chattingActivity, nodeModel);
            addMessageTask.execute();
        }
    }


}
