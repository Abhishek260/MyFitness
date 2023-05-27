package com.example.myfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddPostActivity extends AppCompatActivity {

    EditText titleBlog, descriptionBlog;
    Button upload;
    ImageView blog_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

//        initialization
        titleBlog = findViewById(R.id.title_blog);
        descriptionBlog = findViewById(R.id.description_blog);
        upload = findViewById(R.id.upload);
        blog_image = findViewById(R.id.post_image_blog);

    }

    private void permission(){

    }
}