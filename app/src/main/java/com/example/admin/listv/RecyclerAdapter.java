package com.example.admin.listv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    String[] prod_name;
    String[] prod_price;
    int[] images;
    Context context;
    private ClickListener clickListener;

    public RecyclerAdapter(MainActivity productActivity, String[] product_name, String[] prod_price, int[] images, ClickListener clickListener) {
        context = productActivity;
        this.clickListener = clickListener;
        this.prod_price = prod_price;
        this.prod_name = product_name;
        this.images = images;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.prod_list_item, viewGroup, false);

//        view.setOnClickListener(MainActivity.myOnClickListener);

        return new MyViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.productName.setText(prod_name[i]);
        myViewHolder.prod_unit.setText("1");
        myViewHolder.price.setText(prod_price[i]);
//        myViewHolder.quantity.setText("1");

        myViewHolder.prod_image.setImageResource(images[i]);
    }
    @Override
    public int getItemCount() {
        return prod_name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productName, prod_unit, price, quantity;
        ImageView prod_image, plus, minus;
        int sum;

        public MyViewHolder(@NonNull View itemView, final ClickListener listener) {
            super(itemView);

            productName = itemView.findViewById(R.id.productname);
            prod_unit = itemView.findViewById(R.id.tv_unit);
            price = itemView.findViewById(R.id.tv_price);
            quantity = itemView.findViewById(R.id.tv_quantity);
            prod_image = itemView.findViewById(R.id.product_image);
            plus = itemView.findViewById(R.id.img_add);
            minus = itemView.findViewById(R.id.img_sub);
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sum = sum + 1;
                    Log.e("Quantity value2", "" + sum);
                    if (sum > 0) {
                        quantity.setVisibility(View.VISIBLE);
                        minus.setVisibility(View.VISIBLE);
                    }
                    quantity.setText(String.valueOf(sum));
                    listener.itemClick(view, getAdapterPosition());
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sum > 0) {
                        sum = sum - 1;
                        if (sum == 0) {
                            quantity.setVisibility(View.INVISIBLE);
                            minus.setVisibility(View.INVISIBLE);
                        }
                        quantity.setText(String.valueOf(sum));
                        listener.itemClick(view, getAdapterPosition());
                    }
                }
            });
        }
    }
}
