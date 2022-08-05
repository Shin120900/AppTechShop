package com.example.techshop.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.techshop.R;
import com.example.techshop.databinding.FragmentRegisterBinding;
import com.example.techshop.event.IRegister;
import com.example.techshop.repository.RegisterRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements IRegister {
    FragmentRegisterBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    RegisterRepository registerRepository = new RegisterRepository(this);
    // TODO: Rename and change types of parameters


    public RegisterFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        binding.btnRegister.setOnClickListener(view -> {
            registerRepository.register(binding.edFirstName.getText().toString(),binding.edLastName.getText().toString(),
                    binding.edUser.getText().toString(),binding.edPass.getText().toString());
        });
        return binding.getRoot();
    }

    @Override
    public void getSuccessful() {
        Toast.makeText(getContext(), "Đăng kí thành công", Toast.LENGTH_LONG).show();
        Fragment fragment = LoginFragment.newInstance();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onMessenger(String mes) {
        Toast.makeText(getContext(), mes, Toast.LENGTH_LONG).show();
    }
}