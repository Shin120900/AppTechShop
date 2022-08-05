package com.example.techshop.views.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.techshop.R;
import com.example.techshop.adapter.ProductAdapter;
import com.example.techshop.databinding.FragmentProductBinding;
import com.example.techshop.event.IProductAdapter;
import com.example.techshop.event.IGetProductData;
import com.example.techshop.models.Product;
import com.example.techshop.reponse.ProductResponse;
import com.example.techshop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment implements IGetProductData , IProductAdapter {
    FragmentProductBinding binding;
    ProductRepository productRepository = new ProductRepository(this);
    List<Product> productList = new ArrayList<>();
    String token;
    public ProductFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String token) {
        ProductFragment fragment = new ProductFragment();
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
        binding = FragmentProductBinding.inflate(getLayoutInflater());
        productRepository.getAllProduct();
        binding.txtAll.setOnClickListener(view -> {
            productRepository.getAllProduct();
            binding.txtAll.setBackgroundColor(Color.parseColor("#FF03DAC5"));
            binding.txtProductLaptop.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            binding.txtProductSmartPhone.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            binding.txtProductPart.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        });
        binding.txtProductLaptop.setOnClickListener(view -> {
            productRepository.getProductLapTop();
            binding.txtProductLaptop.setBackgroundColor(Color.parseColor("#FF03DAC5"));
            binding.txtAll.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            binding.txtProductSmartPhone.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            binding.txtProductPart.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        });
        binding.txtProductSmartPhone.setOnClickListener(view -> {
            productRepository.getProductSmartPhone();
            binding.txtProductSmartPhone.setBackgroundColor(Color.parseColor("#FF03DAC5"));
            binding.txtAll.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            binding.txtProductLaptop.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            binding.txtProductPart.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

        });

        binding.txtProductPart.setOnClickListener(view -> {
            productRepository.getProductPart();
            binding.txtProductPart.setBackgroundColor(Color.parseColor("#FF03DAC5"));
            binding.txtAll.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            binding.txtProductLaptop.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            binding.txtProductSmartPhone.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        });

        return binding.getRoot();
    }


    @Override
    public void getAllProduct(ProductResponse productResponse) {
        productList.clear();
        productList.addAll(productResponse.getData().getListProduct());
        ProductAdapter adapter = new ProductAdapter(getContext(), productList, this, token);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rcAllProduct.setAdapter(adapter);
        binding.rcAllProduct.setLayoutManager(layoutManager);
        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s);
                return true;
            }
        });

    }

    @Override
    public void getLapTop(ProductResponse productResponse) {
        productList.clear();
        productList.addAll(productResponse.getData().getListProduct());
        ProductAdapter adapter = new ProductAdapter(getContext(), productList, this, token);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rcAllProduct.setAdapter(adapter);
        binding.rcAllProduct.setLayoutManager(layoutManager);
        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s);
                return true;
            }
        });
    }

    @Override
    public void getSmartPhone(ProductResponse productResponse) {
        productList.clear();
        productList.addAll(productResponse.getData().getListProduct());
        ProductAdapter adapter = new ProductAdapter(getContext(), productList, this, token);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rcAllProduct.setAdapter(adapter);
        binding.rcAllProduct.setLayoutManager(layoutManager);
        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s);
                return true;
            }
        });
    }

    @Override
    public void getPart(ProductResponse productResponse) {
        productList.clear();
        productList.addAll(productResponse.getData().getListProduct());
        ProductAdapter adapter = new ProductAdapter(getContext(), productList, this, token);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rcAllProduct.setAdapter(adapter);
        binding.rcAllProduct.setLayoutManager(layoutManager);
        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s);
                return true;
            }
        });
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
        productRepository.addToCart(token, id);
    }
}