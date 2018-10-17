package br.com.digitalhouse.digitalhouseapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.digitalhouse.digitalhouseapp.fragments.CommentsFragment;
import br.com.digitalhouse.digitalhouseapp.fragments.PostsFragment;
import br.com.digitalhouse.digitalhouseapp.interfaces.FragmentClick;
import br.com.digitalhouse.digitalhouseapp.model.Post;

public class HomeActivity2 extends AppCompatActivity implements FragmentClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_2);

        // Chama o replace passando uma instancia do fragmento de posts
        replaceFragment(new PostsFragment(), R.id.content_one, "POSTS");
    }


    // Substitui o conteiner com o fragment passado e adiciona a pilha
    public void replaceFragment(Fragment fragment, int container, String stack) {
        //Inicia uma transação
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Substitu o container com o fragmento
        transaction.replace(container, fragment);

        // Adiciona a pilha de fragmentos
        transaction.addToBackStack(stack);

        // Comita/Finaliza a transação
        transaction.commit();
    }

    @Override
    public void onItemClick(Post post) {
        // Chama o replace passando uma instancia do fragmento de comentários
        Fragment fragment = new CommentsFragment();

        Bundle bundle = new Bundle();
        bundle.putString("TEXT", post.getDescription());

        fragment.setArguments(bundle);

        replaceFragment(fragment, R.id.content_one, "COMMENTS");
    }
}
