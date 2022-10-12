package exportkit.figma.fragments;

import static exportkit.figma.ChattingActivity.GROW_CHATT;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import exportkit.figma.ChattingActivity;
import exportkit.figma.R;

public class ClosedMenuFragment extends Fragment {
    ChattingActivity chattingActivity;
    public ClosedMenuFragment() {
        // Required empty public constructor
    }

    public ClosedMenuFragment(ChattingActivity chattingActivity) {
        this.chattingActivity = chattingActivity;
    }

    public static ClosedMenuFragment newInstance() {
        ClosedMenuFragment fragment = new ClosedMenuFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.menu_closed_fragment, container, false);

        LinearLayout linearLayout = rootView.findViewById(R.id.closed_menu);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction2;
                fragmentTransaction2 = chattingActivity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.setReorderingAllowed(true);
                fragmentTransaction2.replace(R.id.fragment_container_view_variants, chattingActivity.getOpenMenuFragment(), null);
                fragmentTransaction2.commit();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}

