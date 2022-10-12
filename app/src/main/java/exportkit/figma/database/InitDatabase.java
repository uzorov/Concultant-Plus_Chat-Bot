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

    public InitDatabase(ChattingActivity chattingActivity) {
        this.chattingActivity = chattingActivity;
        db = FirebaseFirestore.getInstance();
        Log.d("dataTest", "DataBase init");
        FirebaseFirestore.setLoggingEnabled(true);
    }

    public void addData() {

        String prevQuestion = "Что Вас интересует?";
        String clientsAnswer = "Постановка и снятие с учёта";
        List<String> answers = new ArrayList<>();
        answers.add("В данном разделе собраны наиболее часто встречающиеся вопросы " +
                "по теме постановки и снятия с учёта самозанятого.");
        answers.add("Выберите вопрос из прокручиваемого списка снизу и я на него отвечу.");
        answers.add("Что Вас интересует?");



        List<String> variants = new ArrayList<>();







        variants.add("");
        variants.add("");
        variants.add("");
        variants.add("");


        variants.add("Может ли ИП прекратить свою деятельность и стать самозанятым как физическое лицо?");
        variants.add("Вернуться на главную страницу");





        String prevQuestion1 = "Что Вас интересует?";
        String clientsAnswer1 = "Может ли ИП прекратить свою деятельность и стать самозанятым как физическое лицо?";
        List<String> answers1 = new ArrayList<>();
        answers1.add("Да, может. Для этого необходимо сняться с учета в налоговом органе в качестве ИП и встать на учет в качестве \"самозанятого\". \n");
        answers1.add("Важно: закон не препятствует применению НПД и в статусе ИП (ч. 1 ст. 2 Закона № 422-ФЗ), поэтому можно не сниматься с учета в качестве ИП. При этом вы должны понимать, что нельзя совмещать налог на профессиональный доход (НПД) с другими специальными налоговыми режимами — УСН, ЕНВД и ЕСХН. \n");
       answers1.add("Отказаться от спецрежимов нужно успеть в течение месяца с того дня, как ИП зарегистрировался в качестве самозанятого (ч.4 ст.15 Федерального закона №422-ФЗ). Иначе вам откажут в регистрации.");
        List<String> variants1 = new ArrayList<>();



        // Create a new user with a first and last name
        Map<String, Object> nodeModel1 = new HashMap<>();
        nodeModel1.put("prevQuestion", prevQuestion1);
        nodeModel1.put("clientsAnswer", clientsAnswer1);
        nodeModel1.put("answers", answers1);
        nodeModel1.put("variants", variants1);


// Add a new document with a generated ID
        db.collection(ChattingActivity.REGISTRATION_PATH)
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
    }


}