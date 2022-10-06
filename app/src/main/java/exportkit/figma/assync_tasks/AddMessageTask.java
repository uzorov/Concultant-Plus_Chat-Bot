package exportkit.figma.assync_tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exportkit.figma.ChattingActivity;
import exportkit.figma.showing_messages.MessageAndAnswer;
import exportkit.figma.showing_variants.Variant;

public final class AddMessageTask extends AsyncTask<String, Integer, Boolean> {

    private final ChattingActivity chattingActivity;
    MessageAndAnswer messageAndAnswer;
    List<Variant> variants = new ArrayList<>();

    public AddMessageTask(ChattingActivity chattingActivity) {
        this.chattingActivity = chattingActivity;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        String time = java.text.DateFormat.getTimeInstance().format(new Date());

        String variant = strings[0];

        switch (variant) {
            case "Что ты умеешь?":
                messageAndAnswer = new MessageAndAnswer("Здравствуйте! \n" +
                        "Я чат-бот, созданный с целью помощи регистрации физическим лицам в качестве самозанятых. \n " +
                        "Мои функции: \n" +
                        "- Выберите пункт \"Могу ли я стать самозанятым?\", чтобы пройти тест на самозанятость\n" +
                        "- Выберите пункт \"Про постановку и снятие с учёта\", чтобы задать вопрос в этой области\n" +
                        "- Выберите пункт \"Шаблоны документов для самозанятых\", чтобы получить формы договоров для самозанятых\n" +
                        "- Выберите пункт \"Обратная связь\", чтобы узнать контакты для связи с создателями чат-бота\n" +
                        "- Выберите пункт \"О нас\", чтобы узнать информацию о создателях чат-бота", time, 0);
                break;

            case "Могу ли я стать самозанятым?":
                messageAndAnswer = new MessageAndAnswer("Выберите свой правовой статус:", time, 0);
                variants.add(new Variant("Юридическое лицо"));
                variants.add(new Variant("Физическое лицо"));
                variants.add(new Variant("Индивидуальный предприниматель"));
                break;

            case "Физическое лицо":
                messageAndAnswer = new MessageAndAnswer("Вы гражданин России?", time, 0);
                variants.add(new Variant("Да"));
                variants.add(new Variant("Нет"));
                break;

            case "Да":
                messageAndAnswer = new MessageAndAnswer("Вы ведёте предпринимательскую деятельность на территории РФ?", time, 0);
                variants.add(new Variant("Да"));
                variants.add(new Variant("Нет"));
                break;

            case "Нет":
                messageAndAnswer = new MessageAndAnswer("В соответствии с п.1 ст.2 Федерального закона" +
                        " N 422-ФЗ от 27.11.2018 местом ведения деятельности самозанятых является территория любого из " +
                        "субъектов Российской Федерации, включённых в эксперимент и указанных в части 1 статьи 1" +
                        "привидённого Федерального закона", time, 0);
                variants.add(new Variant("Вернуться на главную страницу"));
                break;
            case "Вернуться на главную страницу":
                messageAndAnswer = new MessageAndAnswer("Что вас интересует?", time, 0);
                variants.add(new Variant("Что ты умеешь?"));
                variants.add(new Variant("Могу ли я стать самозанятым?"));
                variants.add(new Variant("Про постановку и снятие с учёта"));
                variants.add(new Variant("Шаблоны документов для самозанятых"));
                variants.add(new Variant("Обратная связь"));
                variants.add(new Variant("О нас"));
                break;
            default:
                break;
        }
        //Здесь нужно получить данные с сервера
        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        Log.d("addMessage", "enter postExecute");
        // create message from user input data and add to recycler view in MessageActivity
        chattingActivity.addMessageToList(messageAndAnswer);

        if (!variants.isEmpty()) new AddVariantsTask(chattingActivity, variants).execute();

    }
}
