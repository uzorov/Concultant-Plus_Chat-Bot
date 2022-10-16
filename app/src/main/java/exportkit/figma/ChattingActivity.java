
	 
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

    import android.Manifest;
    import android.app.Activity;
    import android.content.Intent;
    import android.content.pm.PackageManager;
    import android.content.res.AssetManager;
    import android.os.Bundle;
    import android.os.Environment;
    import android.util.Log;
    import android.view.View;
    import android.view.WindowManager;
    import android.widget.LinearLayout;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.app.ActivityCompat;
    import androidx.core.content.ContextCompat;
    import androidx.core.content.FileProvider;
    import androidx.fragment.app.FragmentTransaction;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.util.ArrayList;
    import java.util.List;

    import exportkit.figma.database.InitDatabase;
    import exportkit.figma.database.NodeModel;
    import exportkit.figma.fragments.ClosedMenuFragment;
    import exportkit.figma.fragments.OpenMenuFragment;
    import exportkit.figma.showing_messages.MessageAndAnswer;
    import exportkit.figma.showing_messages.MessagesAdapter;
    import exportkit.figma.showing_variants.RecyclerItemClickListener;
    import exportkit.figma.showing_variants.Variant;

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

        public void setPrevLastNodeModel(NodeModel prevLastNodeModel) {
            this.prevLastNodeModel = prevLastNodeModel;
        }

        public NodeModel getPrevLastNodeModel() {
            return prevLastNodeModel;
        }

        NodeModel prevLastNodeModel = null;

        public NodeModel getLastNodeModel() {
            return LastNodeModel;
        }

        public void setLastNodeModel(NodeModel lastNodeModel) {
            LastNodeModel = lastNodeModel;
        }

        NodeModel LastNodeModel = null;

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

                                    if (messagesAndAnswersList.get(position).thisNodeIsDocument()) {

                                        LoadPdfFile(messagesAndAnswersList.get(position).getReceivedText());

                                    } else {
                                        FragmentTransaction fragmentTransaction2;
                                        fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction2.setReorderingAllowed(true);
                                        fragmentTransaction2.replace(R.id.fragment_container_view_variants, closedMenuFragment, null);
                                        fragmentTransaction2.commit();
                                    }

                                }

                                @Override
                                public void onLongItemClick(View view, int position) {

                                    FragmentTransaction fragmentTransaction2;
                                    fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction2.setReorderingAllowed(true);
                                    fragmentTransaction2.replace(R.id.fragment_container_view_variants, closedMenuFragment, null);
                                    fragmentTransaction2.commit();


                                }
                            }));


//Вызываем метод, который загрузит все варианты и вопрос: "Что вас интересует?" -
            initDatabase = new InitDatabase(ChattingActivity.this, internetIsConnected());
            initDatabase.readData("", "", MENU_PATH);

        }

        //Метод проверяет интернет соединение
        public boolean internetIsConnected() {
            try {
                String command = "ping -c 1 google.com";
                return (Runtime.getRuntime().exec(command).waitFor() == 0);
            } catch (Exception e) {
                return false;
            }
        }

        /* Checks if external storage is available for read and write */
        public boolean isExternalStorageWritable() {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                return true;
            }
            return false;
        }

        //Получить директорию для сохранения документов
        public File getFileStorageDir(String fileName) {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), fileName);

            if (!file.mkdirs()) {
                Log.e("saving_files", "Directory not created");
            }
            return file;
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


        private static final String AUTHORITY = "exportkit.figma";

        static public void copy(InputStream in, File dst) throws IOException {
            FileOutputStream out = new FileOutputStream(dst);
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            in.close();
            out.close();
        }


        //Позволяет просмотреть файл с расширением .docx по переданному названию файла
        //Файлы данного приложения хранятся в папке assets
        private void LoadPdfFile(String fileName) {

            File f = new File(getFilesDir(), fileName);

            if (!f.exists()) {
                AssetManager assets = getAssets();

                try {
                    copy(assets.open(fileName), f);
                } catch (IOException e) {
                    Log.e("FileProvider", "Exception copying from assets", e);
                }
            }

            Intent i =
                    new Intent(Intent.ACTION_VIEW,
                            FileProvider.getUriForFile(this, AUTHORITY, f));

            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(i);
            //finish();


        }

        public static final int REQUEST_WRITE_STORAGE = 112;

        public boolean requestPermission(Activity context) {
            boolean hasPermission = (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
            if (!hasPermission) {
                ActivityCompat.requestPermissions(context,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_STORAGE);
                hasPermission = (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
            }

            return hasPermission;
        }

        public File GetDocxFile(File fileDir, String fileName) {

            File f = new File(fileDir, fileName);

            if (!f.exists()) {
                AssetManager assets = getAssets();

                try {
                    copy(assets.open(fileName), f);
                } catch (IOException e) {
                    Log.e("FileProvider", "Exception copying from assets", e);
                }
            }

            return f;
        }

    }