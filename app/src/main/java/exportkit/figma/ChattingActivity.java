
	 
	/*
     *	This content is generated from the API File Info.
     *	(Alt+Shift+Ctrl+I).
     *
     *	@desc
     *	@file 		android_large___1
     *	@date 		Wednesday 28th of September 2022 08:57:46 AM
     *	@title 		Page 1
     *	@author
     *	@keywords
     *	@generator 	Export Kit v1.3.figma
     *
     */


    package exportkit.figma;

    import static com.google.android.material.internal.ContextUtils.getActivity;

    import android.app.Activity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.view.ViewGroup;
    import android.view.WindowManager;
    import android.widget.LinearLayout;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentActivity;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentTransaction;
    import androidx.recyclerview.widget.DefaultItemAnimator;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    import exportkit.figma.database.InitDatabase;
    import exportkit.figma.fragments.ClosedMenuFragment;
    import exportkit.figma.fragments.OpenMenuFragment;
    import exportkit.figma.showing_messages.MessageAndAnswer;
    import exportkit.figma.showing_messages.MessagesAdapter;
    import exportkit.figma.showing_variants.RecyclerItemClickListener;
    import exportkit.figma.showing_variants.Variant;
    import exportkit.figma.showing_variants.VariantsAdapter;

    public class ChattingActivity extends AppCompatActivity {


        //Пути хранения информации разделов
        static public final String MENU_PATH = "menu";
        static public final String TEST_PATH = "test";
        static public final String REGISTRATION_PATH = "registration_questions";
        static public final String WHO_IS_SELF_EMPLOYED_PATH = "who_is_self_employed_questions";
        static public final String TAX_REGULATION_PATH = "task_regulation_questions";
        static public final String TEMPLATES_PATH = "templates";
        static public final String ABOUT_PATH = "about";

        //Константы для определения, нужно ли уменьшить чат или увеличить
        static public final int GROW_CHATT = 1;
        static public final int CUT_CHATT = 0;

        //Фрагменты, представляющие меню с вариантами в открытом и закрытом состояниях
        OpenMenuFragment openMenuFragment;
        ClosedMenuFragment closedMenuFragment;





        MessagesAdapter messagesAdapter;

        InitDatabase initDatabase;

        public List<MessageAndAnswer> getMessagesAndAnswersList() {
            return messagesAndAnswersList;
        }

        List<MessageAndAnswer> messagesAndAnswersList = new ArrayList<>();
        List<Variant> variants = new ArrayList<>();
        RecyclerView chattingRecycleView;

        public String previousQuestion = "";
        public String currentPath = MENU_PATH;

        public OpenMenuFragment getOpenMenuFragment() {
            return openMenuFragment;
        }

        public ClosedMenuFragment getClosedMenuFragment() {
            return closedMenuFragment;
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.chatting_activity);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            openMenuFragment = new OpenMenuFragment(ChattingActivity.this);
            closedMenuFragment = new ClosedMenuFragment(ChattingActivity.this);
            //Отображаем фрагмент с вариантами

            FragmentTransaction fragmentTransaction;
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setReorderingAllowed(true);
            fragmentTransaction.add(R.id.fragment_container_view_variants, openMenuFragment, null);
            fragmentTransaction.commit();



            //Ставим слушатель на LinearLayout

            LinearLayout linearLayout = findViewById(R.id.top_messages);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction2;
                    fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.setReorderingAllowed(true);
                    fragmentTransaction2.replace(R.id.fragment_container_view_variants, closedMenuFragment, null);
                    fragmentTransaction2.commit();
                }
            });


            chattingRecycleView = findViewById(R.id.messages_recycleview);
            chattingRecycleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction2;
                    fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.setReorderingAllowed(true);
                    fragmentTransaction2.replace(R.id.fragment_container_view_variants, closedMenuFragment, null);
                    fragmentTransaction2.commit();
                }
            });


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);


            chattingRecycleView.setLayoutManager(linearLayoutManager);

            messagesAdapter = new MessagesAdapter(this);
            chattingRecycleView.setAdapter(messagesAdapter);


            chattingRecycleView.addOnItemTouchListener(
                    new RecyclerItemClickListener(
                            this,
                            chattingRecycleView,
                            new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {

                                    if (findViewById(R.id.closed_menu) == null) {
                                        FragmentTransaction fragmentTransaction2;
                                        fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction2.setReorderingAllowed(true);
                                        fragmentTransaction2.replace(R.id.fragment_container_view_variants, closedMenuFragment, null);
                                        fragmentTransaction2.commit();
                                    }


                                }

                                @Override
                                public void onLongItemClick(View view, int position) {
                                    if (findViewById(R.id.closed_menu) == null) {
                                        FragmentTransaction fragmentTransaction2;
                                        fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction2.setReorderingAllowed(true);
                                        fragmentTransaction2.replace(R.id.fragment_container_view_variants, closedMenuFragment, null);
                                        fragmentTransaction2.commit();
                                    }


                                }
                            }));


            //Вызываем метод, который загрузит все варианты и вопрос: "Что вас интересует?" -
            // пока неправильно - переделать
            initDatabase = new InitDatabase(ChattingActivity.this);
            initDatabase.readData("", "", MENU_PATH);


        }


        public MessageAndAnswer getMessageAndAnswer(int position) {
            return messagesAndAnswersList.get(position);
        }

        public void addMessageToList(MessageAndAnswer messageAndAnswer) {
            messagesAndAnswersList.add(messageAndAnswer);
            if (messageAndAnswer.getViewType() == MessageAndAnswer.LEFT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE) {
                previousQuestion = messageAndAnswer.getReceivedText();
            }
            messagesAdapter.notifyDataSetChanged();
            chattingRecycleView.scrollToPosition(messagesAndAnswersList.size() - 1); // scroll to bottom
        }

        public void clearVariants() {
            variants.clear();
        }

        public void addVariantToList(Variant variant) {
            variants.add(variant);
            openMenuFragment.notifyRecycler();

        }

        public List<Variant> getVariants() {
            return variants;
        }

        public Variant getVariantObject(int position) {
            return variants.get(position);
        }

        public void scrollDown() {
            chattingRecycleView.scrollToPosition(messagesAndAnswersList.size() - 1);
        }

        //public void sendMessage(View v) {
        //new AddMessageTask(this, nodeModel[0]).execute();
        //}
    }
	
	