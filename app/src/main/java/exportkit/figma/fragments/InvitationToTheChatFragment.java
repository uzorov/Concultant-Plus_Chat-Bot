package exportkit.figma.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import exportkit.figma.ChattingActivity;
import exportkit.figma.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvitationToTheChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvitationToTheChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InvitationToTheChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvitationToTheChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InvitationToTheChatFragment newInstance(String param1, String param2) {
        InvitationToTheChatFragment fragment = new InvitationToTheChatFragment();
        return fragment;
    }

    ImageView logoIcon;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invitation_to_the_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logoIcon = requireActivity().findViewById(R.id.konsul_tantplyus_1);
        if (logoIcon != null)
        {
            logoIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToChattingIntent = new Intent(getActivity(), ChattingActivity.class);
                    startActivity(goToChattingIntent);
                }
            });
        }
    }
}