package com.example.techshop.views.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.techshop.R;
import com.example.techshop.databinding.ActivityMainBinding;
import com.example.techshop.reponse.UserReponse;
import com.example.techshop.views.fragment.CartFragment;
import com.example.techshop.views.fragment.HomeFragment;
import com.example.techshop.views.fragment.LoginFragment;
import com.example.techshop.views.fragment.ProductFragment;
import com.example.techshop.views.fragment.UserFragment;


public class MainActivity extends AppCompatActivity  {
    ActivityMainBinding binding;
    boolean isCheck = false;
    String token ="";
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ac = getSupportActionBar();
        ac.hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        getFragment(HomeFragment.newInstance(token));
        binding.acHome.setOnClickListener(view -> {
            getFragment(HomeFragment.newInstance(token));
        });
        binding.acProduct.setOnClickListener(view -> {
            getFragment(ProductFragment.newInstance(token));
        });
        binding.acCart.setOnClickListener(view -> {
            if(token.equals("")){
                Toast.makeText(getBaseContext(), "Bạn cần đăng nhập", Toast.LENGTH_LONG).show();
                getFragment(UserFragment.newInstance(token,name));
            }else {
                getFragment(CartFragment.newInstance(token));
            }

        });
        binding.acUser.setOnClickListener(view -> {
            getFragment(UserFragment.newInstance(token,name));
        });


    }

    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==1){
                token = data.getStringExtra("token");
                name = data.getStringExtra("name");
                Toast.makeText(getBaseContext(), name, Toast.LENGTH_SHORT).show();
                getFragment(UserFragment.newInstance(token,name));
            }
        }
    }

    public String getToken() {
        return token;
    }
}