package br.com.digitalhouse.digitalhouseapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.digitalhouseapp.R;
import br.com.digitalhouse.digitalhouseapp.adapter.RecyclerViewPostAdapter;
import br.com.digitalhouse.digitalhouseapp.interfaces.FragmentClick;
import br.com.digitalhouse.digitalhouseapp.interfaces.ServiceListener;
import br.com.digitalhouse.digitalhouseapp.model.Post;
import br.com.digitalhouse.digitalhouseapp.model.dao.PostDAO;

public class PostsFragment extends Fragment implements RecyclerViewPostAdapter.OnCardClickListener, ServiceListener{

    private FragmentClick listener;
    private RecyclerViewPostAdapter adapter;
    private List<Post> posts = new ArrayList<>();

    public PostsFragment() {
        // Construtor padrão
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (FragmentClick) context;
        } catch (ClassCastException e) {
            Log.i("LOG", "Não pode converter class: " + e.getMessage());
            throw new ClassCastException("Não pode converter class: ");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_posts_id);

        PostDAO dao = new PostDAO();

        adapter = new RecyclerViewPostAdapter(posts, this);
        recyclerView.setAdapter(adapter);

        dao.getPosts(getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button btnSend = view.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post();
                post.setDescription("Descrição do post enviado");

                listener.onItemClick(post);
            }
        });


        return view;
    }



    @Override
    public void onShareClick(Post post) {
        listener.onItemClick(post);
    }


    @Override
    public void onSucess(Object object) {
      posts = (List<Post>) object;
      adapter.update(posts);


    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(), "Error " + throwable.getMessage(),Toast.LENGTH_SHORT).show();
    }
}

