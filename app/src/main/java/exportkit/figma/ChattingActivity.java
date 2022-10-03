
	 
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
import android.widget.GridLayout;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import exportkit.figma.showing_messages.MessagesAdapter;
import exportkit.figma.showing_variants.VariantsAdapter;

	public class ChattingActivity extends Activity {

	


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatting_activity);

		RecyclerView chattingRecycleView = findViewById(R.id.messages_recycleview);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
			@Override
			public boolean canScrollVertically()
			{
				return true;
			}
		};
		chattingRecycleView.setLayoutManager(linearLayoutManager);
		chattingRecycleView.setAdapter(new MessagesAdapter(this));

		RecyclerView variantsRecycleView = findViewById(R.id.variants_recycleview);
		variantsRecycleView.setLayoutManager(new GridLayoutManager(
				this,
				3,
				RecyclerView.HORIZONTAL,
				false));
		variantsRecycleView.setAdapter(new VariantsAdapter());


	}
}
	
	