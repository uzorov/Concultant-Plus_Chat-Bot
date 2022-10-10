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

        String prevQuestion = "•\tРеализация подакцизных товаров\n" +
                "•\tПерепродажа товаров и имущественных прав (кроме перепродажи личных вещей)\n" +
                "•\tДобыча полезных ископаемых?\n";
        String clientsAnswer = "Да";
        List<String> answers = new ArrayList<>();
        answers.add("Люди, занимающиеся подобной деятельностью, не могут иметь статус самозанятого");
        answers.add("Источник");


        List<String> variants = new ArrayList<>();
        variants.add("Вернуться на главную страницу");





        // Create a new user with a first and last name
        Map<String, Object> nodeModel = new HashMap<>();
        nodeModel.put("prevQuestion", prevQuestion);
        nodeModel.put("clientsAnswer", clientsAnswer);
        nodeModel.put("answers", answers);
        nodeModel.put("variants", variants);


// Add a new document with a generated ID
        db.collection("test")
                .add(nodeModel)
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
