package com.example.techshop.views.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.techshop.R;
import com.example.techshop.databinding.FragmentLoginBinding;
import com.example.techshop.event.ILogin;
import com.example.techshop.reponse.UserReponse;
import com.example.techshop.repository.UserRepository;
import com.example.techshop.views.activity.LoginActivity;
import com.example.techshop.views.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements ILogin {
    FragmentLoginBinding binding;
    UserRepository userRepository = new UserRepository(this);
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public LoginFragment() {
        // Required empty public constructor

    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        binding.btnLogin.setOnClickListener(view -> {
            String email = binding.edUser.getText().toString();
            String pass = binding.edPass.getText().toString();
            userRepository.login(email,pass);
        });

        binding.btnRegister.setOnClickListener(view -> {
            Fragment fragment = RegisterFragment.newInstance();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        });



        return binding.getRoot();
    }

    @Override
    public void getSuccessful(UserReponse userReponse) {
        LoginActivity loginActivity = (LoginActivity) getActivity();
        loginActivity.setData(userReponse);
    }

    @Override
    public void onMessenger(String mes) {
        Toast.makeText(getContext(), mes, Toast.LENGTH_SHORT).show();
    }
}