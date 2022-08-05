package com.example.techshop.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.techshop.R;
import com.example.techshop.adapter.CartAdapter;
import com.example.techshop.adapter.ProductAdapter;
import com.example.techshop.databinding.FragmentCartBinding;
import com.example.techshop.event.ICartAdapter;
import com.example.techshop.event.IGetCart;
import com.example.techshop.models.Product;
import com.example.techshop.reponse.CartReponse;
import com.example.techshop.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements IGetCart, ICartAdapter {
    FragmentCartBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    String token;
    CartRepository cartRepository = new CartRepository(this);

    // TODO: Rename and change types of parameters


    public CartFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String token) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString("token", token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString("token");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(getLayoutInflater());
        cartRepository.getCart(token);
        return binding.getRoot();
    }

    @Override
    public void getSuccessful(CartReponse cartReponse, int total) {
        if(cartReponse.getDataCart().getCarts()!=null) {
            CartAdapter adapter = new CartAdapter(cartReponse.getDataCart().getCarts(), this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
            binding.rcCart.setAdapter(adapter);
            binding.rcCart.setLayoutManager(layoutManager);
            binding.txtTotal.setText(total+ "$");
            binding.txtClearCart.setOnClickListener(view -> {
                cartRepository.clearCart(token);
            });
        }else {
            Toast.makeText(getContext(), "Không có sản phẩm trong giỏ hàng", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onMessenger(String mes) {
        Toast.makeText(getContext(), mes, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addCount(int id) {
        cartRepository.addToCart(token, id);
    }

    @Override
    public void subCount(int id) {
        cartRepository.removeFromCart(token, id);
    }

    @Override
    public void deleteProduct(int id) {
        cartRepository.deleteProductFromCart(token, id);
    }
}