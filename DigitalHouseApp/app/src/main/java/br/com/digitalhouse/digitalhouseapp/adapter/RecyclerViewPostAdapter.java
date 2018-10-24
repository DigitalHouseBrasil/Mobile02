package br.com.digitalhouse.digitalhouseapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.digitalhouse.digitalhouseapp.R;
import br.com.digitalhouse.digitalhouseapp.model.Post;

public class RecyclerViewPostAdapter extends RecyclerView.Adapter<RecyclerViewPostAdapter
        .ViewHolder> {

    public interface OnCardClickListener {

        void onShareClick(Post post);

    }

    private List<Post> postList;

    private OnCardClickListener listener;

    public RecyclerViewPostAdapter(List<Post> postList, OnCardClickListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_post_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView descricao;
        private ImageView share;
        private ImageView imagem;

        public ViewHolder(View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.post_item_title_id);
            descricao = itemView.findViewById(R.id.post_item_description_id);
            share = itemView.findViewById(R.id.image_share_post_id);
            imagem = itemView.findViewById(R.id.imagePost);
        }

        // Gerencia conteudo da celula baseado num objeto Post
        public void bind(final Post post) {
            titulo.setText(post.getTitle());
            descricao.setText(post.getDescription());
            Picasso.get().load(post.getImageUrl()).into(imagem);

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShareClick(post);
                }
            });
        }
    }

    public void update(List<Post> posts){
        postList.clear();
        postList.addAll(posts);
        notifyDataSetChanged();
    }
}
