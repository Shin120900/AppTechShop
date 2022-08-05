package com.example.techshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techshop.databinding.ItemCartBinding;
import com.example.techshop.event.ICartAdapter;
import com.example.techshop.models.Cart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<Cart> carts = new ArrayList<>();
    final static String IP = "192.168.1.4";//192.168.1.164 192.168.1.5
    ICartAdapter iCartAdapter;
    public CartAdapter(List<Cart> carts, ICartAdapter iCartAdapter) {
        this.carts = carts;
        this.iCartAdapter = iCartAdapter;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Picasso.get().load("http://"+IP+":8000"+ carts.get(position).getProduct().getPicture()).into(holder.binding.imgProduct);
        holder.binding.txtNameProduct.setText(carts.get(position).getProduct().getName());
        holder.binding.txtPriceProduct.setText(carts.get(position).getProduct().getPrice()+" $");
        holder.binding.txtAmount.setText(carts.get(position).getCount()+"");
        holder.binding.txtAdd.setOnClickListener(view -> {
            iCartAdapter.addCount(carts.get(position).getProduct_id());
        });
        holder.binding.txtSub.setOnClickListener(view -> {
            iCartAdapter.subCount(carts.get(position).getProduct_id());
        });
        holder.binding.txtDelete.setOnClickListener(view -> {
            iCartAdapter.deleteProduct(carts.get(position).getProduct_id());
        });


    }

    @Override
    public int getItemCount() {
        return carts==null ? 0 : carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCartBinding binding;
        public ViewHolder(@NonNull ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
