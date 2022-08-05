package com.example.techshop.views.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.techshop.R;
import com.example.techshop.databinding.FragmentUserBinding;
import com.example.techshop.views.activity.LoginActivity;
import com.example.techshop.views.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    FragmentUserBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    String token;
    String name;

    // TODO: Rename and change types of parameters


    public UserFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String token, String name) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString("token", token);
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString("token");
            name = getArguments().getString("name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(getLayoutInflater());
        if(token.equals("")){
            binding.txtUserName.setOnClickListener(view -> {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.getLoginActivity();
            });
        }else {
            binding.txtUserName.setText(name);
        }

        return binding.getRoot();
    }
}