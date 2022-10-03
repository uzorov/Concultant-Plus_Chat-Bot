package exportkit.figma.showing_variants;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import exportkit.figma.R;


public class VariantsAdapter
        extends RecyclerView.Adapter<VariantsAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View variantsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.variants_bubble, parent, false);
        return new ViewHolder(variantsView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.variant.setText(Variant.SAMPLE_DATA[position].getVariant());
    }


    @Override
    public int getItemCount() {
        return Variant.SAMPLE_DATA.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button variant;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            variant = (Button) itemView.findViewById(R.id.variant_bubble_button);
        }
    }
}
