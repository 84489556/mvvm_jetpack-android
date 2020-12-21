package com.yd.huixuangu.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.yd.huixuangu.R;
import com.yd.huixuangu.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private UserViewModel mModel;
    private int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mModel = new ViewModelProvider(this).get(UserViewModel.class);
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.textview.setText(s);
            }
        };
        mModel.getName().observe(this, observer);

    }


    public void button(View view) {
        String newName = String.valueOf(++i);
        mModel.getName().setValue(newName);
    }
}
