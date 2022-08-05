package com.example.techshop.views.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.techshop.R;
import com.example.techshop.adapter.ProductAdapter;
import com.example.techshop.databinding.FragmentHomeBinding;
import com.example.techshop.event.IGetHomeData;
import com.example.techshop.event.IProductAdapter;
import com.example.techshop.models.Product;
import com.example.techshop.reponse.ProductResponse;
import com.example.techshop.repository.HomeProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements IGetHomeData, IProductAdapter {
    FragmentHomeBinding binding;
    List<Product> productList = new ArrayList<>();
    HomeProductRepository homeProductRepository = new HomeProductRepository(this);
    String token;
    public HomeFragment() {
        // Required empty public constructor

    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String token) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        args.putString("token", token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            token = getArguments().getString("token");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        setSilde();
        homeProductRepository.getProductLapTop();





        return binding.getRoot();
    }

    private void setSilde() {
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.laptop));
        slideModels.add(new SlideModel(R.drawable.iphone));
        slideModels.add(new SlideModel(R.drawable.chuot));
        binding.slider.setImageList(slideModels, true);
    }



    @Override
    public void getLapTop(ProductResponse productResponse) {
        productList.addAll(productResponse.getData().getListProduct());
        List<Product> list = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            list.add(productList.get(i));
        }
        ProductAdapter adapter = new ProductAdapter(getContext(), list, this, token);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rcLaptop.setAdapter(adapter);
        binding.rcLaptop.setLayoutManager(layoutManager);
        homeProductRepository.getProductSmartPhone();
    }

    @Override
    public void getSmartPhone(ProductResponse productResponse) {
        productList.clear();
        productList.addAll(productResponse.getData().getListProduct());
        List<Product> list = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            list.add(productList.get(i));
        }
        ProductAdapter adapter = new ProductAdapter(getContext(), list, this, token);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rcSmartPhone.setAdapter(adapter);
        binding.rcSmartPhone.setLayoutManager(layoutManager);
        homeProductRepository.getProductPart();
    }

    @Override
    public void getPart(ProductResponse productResponse) {
        productList.clear();
        productList.addAll(productResponse.getData().getListProduct());
        List<Product> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            list.add(productList.get(i));
        }
        ProductAdapter adapter = new ProductAdapter(getContext(), list, this, token);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rcPart.setAdapter(adapter);
        binding.rcPart.setLayoutManager(layoutManager);
    }

    @Override
    public void onMessenger(String mes) {
        Toast.makeText(getContext(), mes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getID(int id) {
        Fragment fragment = DetailFragment.newInstance(id, token);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setUserFragment() {
        Fragment fragment = UserFragment.newInstance(token, "");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void addToCart(int id) {
        homeProductRepository.addToCart(token, id);
    }
}