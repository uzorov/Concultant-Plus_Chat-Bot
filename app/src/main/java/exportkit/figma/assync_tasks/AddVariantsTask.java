package exportkit.figma.assync_tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import exportkit.figma.ChattingActivity;
import exportkit.figma.showing_variants.Variant;

public class AddVariantsTask extends AsyncTask<Void, Void, Void> {

    ChattingActivity chattingActivity;
    List<Variant> variants;

    public AddVariantsTask(ChattingActivity chattingActivity, List<Variant> variants) {
        this.chattingActivity = chattingActivity;
        this.variants = variants;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        chattingActivity.clearVariants();
        for (Variant variant : variants)
        {
            chattingActivity.addVariantToList(variant);
        }
        return null;
    }
}
