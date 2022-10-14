package exportkit.figma.fragments;

import static exportkit.figma.ChattingActivity.ABOUT_PATH;
import static exportkit.figma.ChattingActivity.CUT_CHATT;
import static exportkit.figma.ChattingActivity.GROW_CHATT;
import static exportkit.figma.ChattingActivity.MENU_PATH;
import static exportkit.figma.ChattingActivity.REGISTRATION_PATH;
import static exportkit.figma.ChattingActivity.TAX_REGULATION_PATH;
import static exportkit.figma.ChattingActivity.TEMPLATES_PATH;
import static exportkit.figma.ChattingActivity.TEST_PATH;
import static exportkit.figma.ChattingActivity.WHO_IS_SELF_EMPLOYED_PATH;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import exportkit.figma.ChattingActivity;
import exportkit.figma.R;
import exportkit.figma.assync_tasks.AddMessageTask;
import exportkit.figma.database.InitDatabase;
import exportkit.figma.database.NodeModel;
import exportkit.figma.showing_messages.MessageAndAnswer;
import exportkit.figma.showing_variants.RecyclerItemClickListener;
import exportkit.figma.showing_variants.VariantsAdapter;

public class OpenMenuFragment extends Fragment {
    ChattingActivity chattingActivity;
    InitDatabase initDatabase;
    VariantsAdapter variantsAdapter;

    public OpenMenuFragment() {
        // Required empty public constructor
    }

    public OpenMenuFragment(ChattingActivity chattingActivity) {
        this.chattingActivity = chattingActivity;
        initDatabase = new InitDatabase(chattingActivity, chattingActivity.internetIsConnected());
    }

    public static OpenMenuFragment newInstance() {
        OpenMenuFragment fragment = new OpenMenuFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.menu_open_fragment, container, false);


        RecyclerView variantsRecycleView = (RecyclerView) rootView.findViewById(R.id.variants_recycleview);


        variantsRecycleView.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                RecyclerView.VERTICAL,
                false));


        variantsAdapter = new VariantsAdapter(chattingActivity);
        variantsRecycleView.setAdapter(variantsAdapter);


        variantsRecycleView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getContext(),
                        variantsRecycleView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {






                                if (
                                        chattingActivity.getVariantObject(position).getVariantText().equals("Вернуться на главный экран")
                                                ||
                                                chattingActivity.getVariantObject(position).getVariantText().equals("Вернуться на главную страницу")) {

                                    //Вернуться в меню
                                    chattingActivity.currentPath = MENU_PATH;
                                    initDatabase.readData("",
                                            "",
                                            MENU_PATH);

                                }

                                else if (chattingActivity.getVariantObject(position).getVariantText().equals("Сохранить договор"))
                                {
                                    //Сохранить документ во внешнем хранилище
                                    if (chattingActivity.isExternalStorageWritable())
                                    {
                                        File fileDir = chattingActivity.getFileStorageDir(chattingActivity.getMessageAndAnswer(
                                                chattingActivity.getMessagesAndAnswersList().size()-2
                                        ).getReceivedText());

                                        File file = chattingActivity.GetDocxFile(fileDir, chattingActivity.previousQuestion);



                                        if (file.exists())
                                        {
                                            Toast.makeText(getContext(), "Файл успешно сохранён по пути: " + fileDir.toString(), Toast.LENGTH_LONG).show();

                                        }
                                        else
                                        {

                                            Toast.makeText(getContext(), "Произошла какая-то ошибка, попробуйте снова...", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext(), "У приложения нет доступа" +
                                                " ко внешнему хранилищу, " +
                                                "сохранение договора невозможно", Toast.LENGTH_SHORT).show();
                                    }


                                    AddMessageTask addMessageTask = new AddMessageTask(chattingActivity, chattingActivity.getPrevLastNodeModel());
                                    addMessageTask.execute();
                                }

                                else if (chattingActivity.getVariantObject(position).getVariantText().equals("Назад"))
                                {
                                    //Вернуть предыдущий узел
                                    AddMessageTask addMessageTask = new AddMessageTask(chattingActivity, chattingActivity.getPrevLastNodeModel());
                                    addMessageTask.execute();
                                }
                                else if (chattingActivity.getVariantObject(position).getVariantText().equals("Шаблоны документов")
                                        || chattingActivity.getVariantObject(position).getVariantText().equals("Договор аренды нежилого помещения")
                                        || chattingActivity.getVariantObject(position).getVariantText().equals("Договор на оказание репетиторских услуг")
                                        || chattingActivity.getVariantObject(position).getVariantText().equals("Договор по созданию текстовых материалов")) {
                                    //Вывести шаблоны документов
                                    if (chattingActivity.getVariantObject(position).getVariantText().equals("Шаблоны документов")) {
                                        List<String> answerList = new ArrayList<>();
                                            answerList.add("В данном разделе собраны шаблоны договоров для самозанятых в трёх различных сферах.");
                                            answerList.add("Выберите интересующий Вас договор из списка ниже и нажмите на него для предпросмотра или скачайте," +
                                                    " нажав соответствующую кнопку.");
                                            answerList.add("Какой договор Вас интересует?");
                                        List<String> variantList = new ArrayList<>();
                                            variantList.add("Договор аренды нежилого помещения");
                                            variantList.add("Договор на оказание репетиторских услуг");
                                            variantList.add("Договор по созданию текстовых материалов");
                                            variantList.add("Вернуться на главную страницу");
                                        NodeModel nodeModel = new NodeModel("", "", answerList, variantList);
                                        AddMessageTask addMessageTask = new AddMessageTask(chattingActivity, nodeModel);
                                            addMessageTask.execute();
                                    }
                                       else {
                                        List<String> answerList = new ArrayList<>();
                                            answerList.add(chattingActivity.getVariantObject(position).getVariantText() + ".docx");
                                        List<String> variantList = new ArrayList<>();
                                            variantList.add("Сохранить договор");
                                              variantList.add("Назад");
                                            variantList.add("Вернуться на главную страницу");
                                        NodeModel nodeModel = new NodeModel("", "", answerList, variantList, true);
                                        AddMessageTask addMessageTask = new AddMessageTask(chattingActivity, nodeModel);
                                            addMessageTask.execute();
                                       }

                                } else if (chattingActivity.getVariantObject(position).getVariantText().equals("Обратная связь")) {
                                    //Вывести информацию об обратной связи - работает в режиме без интернета
                                    List<String> answerList = new ArrayList<>();
                                    answerList.add("Связаться со мной можно, написав на почту: chat.bot.pomoshchnik.ivan@bk.ru");
                                    answerList.add("Вы можете задать вопросы как по технической части бота, включая вопросы о возникающих ошибках" +
                                            ", так и по информации, присутствующей в боте");
                                    answerList.add("Также буду рад получить от вас общее мнение о продукте.");
                                    answerList.add("Спасибо, что общаетесь со мной!");


                                    List<String> variantList = new ArrayList<>();
                                    variantList.add("Вернуться на главную страницу");
                                    NodeModel nodeModel = new NodeModel("", "", answerList, variantList);

                                    AddMessageTask addMessageTask = new AddMessageTask(chattingActivity, nodeModel);
                                    addMessageTask.execute();
                                } else {
                                    //Получить следующий узел
                                    if (chattingActivity.currentPath.equals(MENU_PATH))
                                        setCurrentPath(chattingActivity.getVariantObject(position).getVariantText());

                                    initDatabase.readData(chattingActivity.getVariantObject(position).getVariantText(),
                                            chattingActivity.previousQuestion,
                                            chattingActivity.currentPath);

                                }
                                String answer = chattingActivity.getVariantObject(position).getVariantText();
                                String time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                                chattingActivity.addMessageToList(new MessageAndAnswer(
                                        answer,
                                        time,
                                        MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE)
                                );
                            }


                            //Выбрать раздел
                            private void setCurrentPath(String menuVariant) {

                                switch (menuVariant) {
                                    case "Пройти тест на самозанятость":
                                        Log.d("dataTest", "Path are changed to TEST");
                                        chattingActivity.currentPath = TEST_PATH;
                                        break;
                                    case "Постановка и снятие с учёта":
                                        Log.d("dataTest", "Path are changed to REGISTRATION");
                                        chattingActivity.currentPath = REGISTRATION_PATH;
                                        break;
                                    case "Кто такой самозанятый?":
                                        Log.d("dataTest", "Path are changed to " + WHO_IS_SELF_EMPLOYED_PATH);
                                        chattingActivity.currentPath = WHO_IS_SELF_EMPLOYED_PATH;
                                        break;
                                    case "Налоговое регулирование":
                                        Log.d("dataTest", "Path are changed to " + TAX_REGULATION_PATH);
                                        chattingActivity.currentPath = TAX_REGULATION_PATH;
                                        break;
                                    case "О нас":
                                        Log.d("dataTest", "Path are changed to " + ABOUT_PATH);
                                        chattingActivity.currentPath = ABOUT_PATH;
                                        break;
                                }

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }
                        })
        );


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void notifyRecycler() {
        variantsAdapter.notifyDataSetChanged();
    }
}
