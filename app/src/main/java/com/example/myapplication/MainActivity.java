package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    ExecutorService executor = Executors.newSingleThreadExecutor();

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

    public void onButtonClickGET(final View view) throws IOException {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("https://reqres.in/api/users?page=2")
                        .build();
                OkHttpClient client = new OkHttpClient();
                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        System.out.println("GGG");

    }

    public void onButtonClickPOST(final View view) throws IOException {

        executor.execute(new Runnable() {
            @Override
            public void run() {

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, "{\"jsonExample\":\"value\"}");

                Request request = new Request.Builder()
                        .url("https://reqres.in/api/users?page=2")
                        .post(body)
                        .build();
                OkHttpClient client = new OkHttpClient();
                try {
                    okhttp3.Response response = client.newCall(request).execute();
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        System.out.println("GGG");

    }

    public void onButtonClickPostSync(final View view) throws IOException {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "{\"jsonExample\":\"value\"}");

        Request request = new Request.Builder()
                .url("https://reqres.in/api/users?page=2")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();

        okhttp3.Call call = client.newCall(request);

        call.enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
                System.out.println(response.body().string());
            }

            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("GGG");

    }
}