## Concultant-Plus_Chat-Bot  
### Проект "Чат-бот для самозанятых"

![loonapix_1664530400757228287](https://user-images.githubusercontent.com/90005421/195982326-d8b25ef3-9683-41c2-be3d-5a414312e22c.png)

Привет! 

Меня зовут бот Иван, я помогу разобраться с особенностями статуса самозанятого, расскажу о налогах  и предоставлю договоры, которые можно использовать самозанятому. 

Чтобы пообщаться со мной, выберите последний релиз из [данного раздела](https://github.com/uzorov/Concultant-Plus_Chat-Bot/releases) и скачайте APK файл.



## Сборка

### Сборка проекта в Android Studio 
Для разработки использовалась последняя версия [Android Studio](http://developer.android.com/sdk/installing/studio.html). 
1. Склонируйте репозиторий из GitHub
2. Android Studio: File -> Import Project -> Select -> Выбрать папку с клонированным репозиторием.
3. При сборке проекта необходимо использовать Gradle. 

## Детали проекта 
С 2017 г. был введен статус самозанятого, но этот статус вызывает много вопросов у граждан, желающих заниматься предпринимательской деятельностью.
Цель нашего проекта: 
* помочь гражданам разобраться в законодательстве о самозанятых,
* узнать, какие налоги необходимо платить самозанятому,
* узнать о плючах и минусах статуса самозанятого,
* получить готовые шаблоны договоров, которые можно использовать самозанятому

Напоминаем:
* Наш проект не “панацея” и не может охватить все вопросы граждан, мы освещаем только основные особенности статуса самозанятого.  
* Информация из чат-бота основана на актуальных законах.
* Материалы носят рекомендательный характер, и мы не несем ответственность за действия граждан на основе материала из чат-бота. 


## Описание архитектуры приложения

Архитектура приложения построена по шаблону MVC. 

Приложение подключено к облачной базе данных Cloud Firestore.

## Описание особенностей проекта

Данный проект может быть интегрирован в любое приложение и изначально разрабатывался с акцентом на данную возможность. В приложении существует один основной экран для общения с пользователем, а также фрагмент, который может быть отображён на любой странице приложения с использованием асинхронной задачи. 

## Технологии, использованные при разработке

### Android SDK
### Java
### Gradle
### Cloud Firestore

Для хранения поведения приложения выбрана база данных [Firebase](https://console.firebase.google.com/u/0/project/cp-project-87030/firestore/data/~2Fchatbot%20behaviour~2FEvwgm2X0oHYjHjOhDZs6). 

Преимущества:
1. NoSQL: имеем возможность хранить данные не в виде жестко типизированных строк, а в виде документов, которые могут иметь различные поля.
2. Одна из стандартных баз данных: её настройка в приложении не требует самостоятельного парсинга, уже написано множество библиотек, легко конфигурирующих приложение под работу с Firebase, что позволяет не заботиться об отказоустойчивости, а также сводит boilerplate к минимуму.
3. Стоимость: использование данной БД не требует никакой платы.

Структура БД представляет собой набор документов из четырех полей:
1. clientsAnswer: ответ пользователя на предыдущий вопрос (в самом начале для работы приложения выбирается значение “старт”)
2. previousQuestion: предыдущий вопрос, на который пользователь дал ответ (clientsAnswer)
3. answer: наш ответ пользователю на его действие (сообщение, которое будет на экране)
4. variants: возможные действия пользователя на наш answer (кнопки, которые он сможет нажать для продолжения прохождения теста или возвращения назад).

## Описание классов проекта

* ChattingActivity - класс, представляющий экран чата с ботом.
* MainActivity - класс, представляющий пустой экран для отображения фрагмента.
* LegacyCompatFileProvider - класс, позволяющий работать с документами.
* AddMessageTask - класс, выполнябщий асинхронную задачу добавления сообщений и вариантов ответа на экран чата.
* NodeModel - класс, моделирующий структуру объектов, хранимых в базе данных.
* InitDatabase - класс, реализующий логику взаимодействия с базой данной.
* ClosedMenuFragment - класс, реализующий логику меню в закрытом состоянии
* OpenMenuFragment - класс, реализующий логику меню в открытом состоянии 
* InvitationToTheChatFragment - класс, реализующий логику приглашения перейти к диалогу с чат-ботом
* MessageAndAnswer - класс, моделирующий структуру сообщений, отображающихся на экране чата.
* MessagesAdapter - Recycle View Adapter, динамически отображающий сообщения на экране чата.
* RecycleItemClickListener - класс, позволяющий обрабатывать нажатия на элементы RecycleView.
* Variant - класс, моделирующий структуру вариантов, отображающихся на экране.
* VariantsAdapter -  Recycle View Adapter, динамически отображающий варианты на экране чата.

## Описание методов класса ChattingActivity

* setPrevLastNodeModel(NodeModel prevLastNodeModel) - сохраняет предпоследний узел NodeModel, необходим для работы функции возвращения к предыдущему вопросу.
* getPrevLastNodeModel() - геттер prevLastNodeModel.
* setLastNodeModel(NodeModel lastNodeModel) - сохраняет последний узел NodeModel, необходим для работы функции возвращения к предыдущему вопросу.
* getLastNodeModel() - геттер lastNodeModel.
* getMessagesAndAnswersList() - возвращает список всех сообщений, отображаемых на экране чата.
* getMessageAndAnswer(int position) - метод, возвращающий сообщение из списка по позиции.
* getOpenMenuFragment() - геттер объекта OpenMenuFragment.
* getClosedMenuFragment() - геттер объекта ClosedMenuFragment.
* getVariants() - метод, возвращающий список вариантов, отображаемых на экране чата.
getVariantObject(int position) - метод, возвращающий вариант из списка по позиции.
* internetIsConnected() - метод, проверяющий наличие интернет соединения.
* isExternalStorageWritable() - метод, проверяющий доступно ли внешнее хранилище устройства для записи, необходим для работы функции сохранения договоров в памяти устройства.
* getFileStorageDir(String fileName) - метод, создающий директорию в разделе "Документы" устройства, для сохранения документов.
* addMessageToList(MessageAndAnswer messageAndAnswer) - метод, добавляющий сообщение в список.
* clearVariants() - метод, удаляющий все варианты из списка.
* addVariantToList(Variant variant) - метод, добавляющий новый вариант в список.
* scrollDown() - метод, выполняющий прокруту списка RecycleView вниз.
* copy(InputStream in, File dst) - метод, выполняющий копирование файла в переданную директорию устройства.
* LoadPdfFile(String fileName) - метод, позволяющий выполнять предпросмотр файлов с расширением .docx.
* requestPermission(Activity context) - метод, запрашивающий разрешение на запись во внешнее хранилище устройства.
* GetDocxFile(File fileDir, String fileName) - метод, копирующий файл в переданную директорию.

## Контакты

Для связи с командой проекта используйте почту: chat.bot.pomoshchnik.ivan@bk.ru
