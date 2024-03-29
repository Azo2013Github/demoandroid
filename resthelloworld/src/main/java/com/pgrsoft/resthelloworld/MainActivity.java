package com.pgrsoft.resthelloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pgrsoft.resthelloworld.interfaz.JsonPlaceHolderApi;
import com.pgrsoft.resthelloworld.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.idResultado);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getComments();



    }

    private void getComments(){
        Call<List<Comment>> call = jsonPlaceHolderApi.getCommnets(3);

        call.enqueue(new Callback<List<Comment>>() {

            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for (Comment comment: comments) {

                    String content = "";
                    content += "ID: " + comment.getId() + "\n ";
                    content += "POST_ID: " + comment.getPostId() + "\n ";
                    content += "NAME: " + comment.getName() + "\n ";
                    content += "Email: " + comment.getEmail() + "\n ";
                    content += "TEXT: " + comment.getText() + "\n";

                    textViewResult.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                textViewResult.setText(t.getMessage());

            }
        });
    }
}
