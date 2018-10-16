package br.com.digitalhouse.digitalhouseapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.digitalhouse.digitalhouseapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsFragment extends Fragment {


    public CommentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        TextView textViewTitle = view.findViewById(R.id.textview_title);

       // String message = getArguments().getString("TEXT");

       // textViewTitle.setText(message);

        return view;
    }

}
