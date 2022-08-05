package com.example.techshop.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.techshop.databinding.FragmentDetailBinding;
import com.example.techshop.event.IGetDetail;
import com.example.techshop.reponse.DetailProductResponse;
import com.example.techshop.repository.DetailProductRepository;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment implements IGetDetail {
    FragmentDetailBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    final static String IP = "192.168.1.4";//192.168.1.164 192.168.1.5
    private int id ;
    String token;
    private DetailProductRepository detailProductRepository = new DetailProductRepository(this);




    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(int id, String token) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putString("token", token);

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(getLayoutInflater());
        if(getArguments() != null){
            id = getArguments().getInt("id");
            token = getArguments().getString("token");
        }else{
            Toast.makeText(getContext(), "Loi", Toast.LENGTH_SHORT).show();
        }

        detailProductRepository.getDetailProduct(id);
        binding.txtAddCart.setOnClickListener(view -> {
            detailProductRepository.addToCart(token, id);
        });

        return binding.getRoot();
    }

    @Override
    public void getSuccessful(DetailProductResponse detailProductResponse) {
        Picasso.get().load("http://"+IP+":8000"+detailProductResponse.getDataDetail().getProduct().getPicture()).into(binding.imgProductDetail);
        binding.txtNameProductDetail.setText(detailProductResponse.getDataDetail().getProduct().getName());
        binding.txtDescriptionProductDetail.setText(detailProductResponse.getDataDetail().getProduct().getDescription());
        binding.txtPrice.setText("Giá : " + detailProductResponse.getDataDetail().getProduct().getPrice() + " $");
    }

    @Override
    public void onMessenger(String mes) {
        Toast.makeText(getContext(), mes, Toast.LENGTH_SHORT).show();
    }
}