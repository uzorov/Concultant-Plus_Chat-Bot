package exportkit.figma.fragments;

import static exportkit.figma.ChattingActivity.CUT_CHATT;
import static exportkit.figma.ChattingActivity.GROW_CHATT;
import static exportkit.figma.ChattingActivity.MENU_PATH;
import static exportkit.figma.ChattingActivity.REGISTRATION_PATH;
import static exportkit.figma.ChattingActivity.TEST_PATH;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import exportkit.figma.ChattingActivity;
import exportkit.figma.R;
import exportkit.figma.database.InitDatabase;
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
        initDatabase = new InitDatabase(chattingActivity);
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
        ;



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
                                        !chattingActivity.getVariantObject(position).getVariantText().equals("Вернуться на главный экран")
                                                &&
                                                !chattingActivity.getVariantObject(position).getVariantText().equals("Вернуться на главную страницу")) {
                                    //Получить следующий узел

                                    if (chattingActivity.currentPath.equals(MENU_PATH))
                                        setCurrentPath(chattingActivity.getVariantObject(position).getVariantText());

                                    initDatabase.readData(chattingActivity.getVariantObject(position).getVariantText(),
                                            chattingActivity.previousQuestion,
                                            chattingActivity.currentPath);
                                } else {
                                    //Вернуться в меню
                                    chattingActivity.currentPath = MENU_PATH;
                                    initDatabase.readData("",
                                            "",
                                            MENU_PATH);
                                }
                                String answer = chattingActivity.getVariantObject(position).getVariantText();
                                String time = java.text.DateFormat.getTimeInstance().format(new Date());
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
