
	 
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

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exportkit.figma.assync_tasks.AddMessageTask;
import exportkit.figma.showing_messages.MessageAndAnswer;
import exportkit.figma.showing_messages.MessagesAdapter;
import exportkit.figma.showing_variants.RecyclerItemClickListener;
import exportkit.figma.showing_variants.Variant;
import exportkit.figma.showing_variants.VariantsAdapter;

	public class ChattingActivity extends Activity {

		VariantsAdapter variantsAdapter;
		MessagesAdapter messagesAdapter;


		public List<MessageAndAnswer> getMessagesAndAnswersList() {
			return messagesAndAnswersList;
		}

		List<MessageAndAnswer> messagesAndAnswersList = new ArrayList<>();
		List<Variant> variants = new ArrayList<>();
		RecyclerView chattingRecycleView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatting_activity);

		chattingRecycleView = findViewById(R.id.messages_recycleview);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		chattingRecycleView.setLayoutManager(linearLayoutManager);
		messagesAdapter = new MessagesAdapter(this);
		chattingRecycleView.setAdapter(messagesAdapter);

		RecyclerView variantsRecycleView = findViewById(R.id.variants_recycleview);
		variantsRecycleView.setLayoutManager(new GridLayoutManager(
				this,
				1,
				RecyclerView.HORIZONTAL,
				false));

		variantsAdapter = new VariantsAdapter(this);
		variantsRecycleView.setAdapter(variantsAdapter);



		//Ручное заполнение для демонстрации - после удалить
		addMessageToList(MessageAndAnswer.SAMPLE_DATA[0]);
		addMessageToList(MessageAndAnswer.SAMPLE_DATA[1]);
		addMessageToList(MessageAndAnswer.SAMPLE_DATA[2]);
		addMessageToList(MessageAndAnswer.SAMPLE_DATA[3]);
		addMessageToList(MessageAndAnswer.SAMPLE_DATA[4]);
		addMessageToList(MessageAndAnswer.SAMPLE_DATA[5]);
		addMessageToList(MessageAndAnswer.SAMPLE_DATA[6]);
		addMessageToList(MessageAndAnswer.SAMPLE_DATA[7]);

		addVariantToList(Variant.SAMPLE_DATA[0]);
		addVariantToList(Variant.SAMPLE_DATA[1]);
		addVariantToList(Variant.SAMPLE_DATA[2]);
		addVariantToList(Variant.SAMPLE_DATA[3]);
		addVariantToList(Variant.SAMPLE_DATA[4]);

		variantsRecycleView.addOnItemTouchListener(
				new RecyclerItemClickListener(
						this,
						variantsRecycleView,
						new RecyclerItemClickListener.OnItemClickListener() {
							@Override
							public void onItemClick(View view, int position) {
								String time = java.text.DateFormat.getTimeInstance().format(new Date());
								addMessageToList(new MessageAndAnswer(
										getVariantObject(position).getVariantText(),
										time,
										MessageAndAnswer.RIGHT_CHAT_BUBBLE_LAYOUT_VIEW_TYPE)

								);
							}

							@Override
							public void onLongItemClick(View view, int position) {

							}
						})
		);
	}

		public MessageAndAnswer getMessageAndAnswer(int position)
		{
			return messagesAndAnswersList.get(position);
		}

		public void addMessageToList(MessageAndAnswer messageAndAnswer) {
			messagesAndAnswersList.add(messageAndAnswer);
			messagesAdapter.notifyDataSetChanged();
			chattingRecycleView.scrollToPosition(messagesAndAnswersList.size() - 1); // scroll to bottom
		}

		public void addVariantToList(Variant variant) {
			variants.add(variant);
			variantsAdapter.notifyDataSetChanged();
		}

		public List<Variant> getVariants() {
			return variants;
		}

		public Variant getVariantObject(int position) {
			return variants.get(position);
		}

		public void sendMessage(View v) {
			new AddMessageTask(this).execute();
		}
	}
	
	