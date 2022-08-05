package com.example.techshop.views.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.techshop.R;
import com.example.techshop.databinding.ActivityLoginBinding;
import com.example.techshop.databinding.ActivityMainBinding;
import com.example.techshop.reponse.UserReponse;
import com.example.techshop.views.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ac = getSupportActionBar();
        ac.hide();
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getFragment(LoginFragment.newInstance());
    }
    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(UserReponse userReponse){
        //Toast.makeText(getBaseContext(), token, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("token", userReponse.getUser().getToken());
        intent.putExtra("name", userReponse.getUser().getUser().getFirst_name()+ " "+ userReponse.getUser().getUser().getLast_name());
        setResult(1, intent);
        finish();
    }


}