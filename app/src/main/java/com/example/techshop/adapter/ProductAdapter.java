package com.example.techshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techshop.databinding.ItemProductBinding;
import com.example.techshop.event.IProductAdapter;
import com.example.techshop.models.Product;
import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private final Context context;
    private IProductAdapter iProductAdapter;
    List<Product> productList;
    List<Product> list = new ArrayList<>();
    final static String IP = "192.168.1.4";//192.168.1.164 192.168.1.5
    String token;

    public ProductAdapter(Context context, List<Product> productList, IProductAdapter iProductAdapter, String token) {
        this.context = context;
        this.productList = productList;
        this.iProductAdapter = iProductAdapter;
        if(list.size()==0){
            list.addAll(productList);
        }
        this.token = token;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtDetail.setOnClickListener(view -> {
            iProductAdapter.getID(productList.get(position).getId());
        });
        Picasso.get().load("http://"+IP+":8000"+productList.get(position).getPicture()).into(holder.binding.imgProduct);
        if(token.equals("")){
            holder.binding.imgAddCart.setOnClickListener(view -> {
                Toast.makeText(context, "Bạn cần đăng nhập", Toast.LENGTH_LONG).show();
                iProductAdapter.setUserFragment();
            });

        }else {
            holder.binding.imgAddCart.setOnClickListener(view -> {
                iProductAdapter.addToCart(productList.get(position).getId());
            });
        }

    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProductBinding binding;
        public ViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void filter(String text) {
        String nfdNormalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        text = pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase().replaceAll("đ", "d");;
        productList.clear();
        if(text.isEmpty()){
            productList.addAll(list);
        } else {
            text = text.toLowerCase();
            for (Product product : list) {
                if (product.getName().toLowerCase().contains(text)) {
                    productList.add(product);
                }
            }
        }

        notifyDataSetChanged();
    }
}
