package br.com.digitalhouse.digitalhouseapp.model.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.digitalhouse.digitalhouseapp.model.Post;

public class PostDAO {

	// Mock (simulação) da lista --> poderia vir do SQL ou da API
	public List<Post> getPosts(Context context) {
		List<Post> postList = new ArrayList<>();

		Gson gson = new Gson();

		AssetManager am = context.getAssets();
		try {

			InputStream newJson = am.open("posts.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(newJson));

			Post[] posts = gson.fromJson(br, Post[].class);

			postList = Arrays.asList(posts);
		} catch(IOException e){
			Log.e("LOG", e.getMessage());
			e.printStackTrace();
		}

		return postList;
	}
}
