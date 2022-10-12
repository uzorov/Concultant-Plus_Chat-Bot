package exportkit.figma.showing_variants;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.installations.Utils;

import exportkit.figma.ChattingActivity;
import exportkit.figma.R;





public class VariantsAdapter
        extends RecyclerView.Adapter<VariantsAdapter.ViewHolder> {



    ChattingActivity chattingActivity;

    public VariantsAdapter(ChattingActivity chattingActivity) {
        this.chattingActivity = chattingActivity;

    }






    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View variantsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.variants_bubble, parent, false);
        variantsView.getLayoutParams().width = (int) getScreenWidth() - 100;
        return new ViewHolder(variantsView);
    }

    public int getScreenWidth() {

        WindowManager wm = (WindowManager) chattingActivity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.variant.setText(chattingActivity.getVariantObject(position).getVariantText());

    }


    @Override
    public int getItemCount() {

        return chattingActivity.getVariants().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private Button variant;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            variant = (Button) itemView.findViewById(R.id.variant_bubble_button);

        }


    }
}
