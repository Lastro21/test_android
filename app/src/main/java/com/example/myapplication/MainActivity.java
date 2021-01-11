package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("222222222222222222222222222222222 Косяк 222222222222222222222222222222222");
                }
                List<Post> posts = response.body();

                for (Post post : posts) {
                    System.out.println(post.getBody());
                    System.out.println(post.getId());
                    System.out.println(post.getTitle());
                    System.out.println(post.getUserId());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                System.out.println("Косяк !!!!!!!!!!!!!!!!!!!!!!! Косяк !!!!!!!!!!!!!!!!!!!!!!!");
            }
        });


        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    }

    public void onButtonClick22(final View view) {




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts2("posts");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("222222222222222222222222222222222 Косяк 222222222222222222222222222222222");
                }
                List<Post> posts = response.body();

                for (Post post : posts) {
                    System.out.println(post.getBody() + "!!!!!!!!");
                    System.out.println(post.getId() + "!!!!!!!!");
                    System.out.println(post.getTitle() + "!!!!!!!!");
                    System.out.println(post.getUserId() + "!!!!!!!!");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                System.out.println("Косяк !!!!!!!!!!!!!!!!!!!!!!! Косяк !!!!!!!!!!!!!!!!!!!!!!!");
            }
        });







        final EditText el1 = findViewById(R.id.Num1);
        final EditText el2 = findViewById(R.id.Num2);
        final TextView resText = findViewById(R.id.Result);

        final int num1 = Integer.parseInt(el1.getText().toString());
        final int num2 = Integer.parseInt(el2.getText().toString());

        int resSum = num1 + num2;
        resText.setText(Integer.toString(resSum));

    }

}