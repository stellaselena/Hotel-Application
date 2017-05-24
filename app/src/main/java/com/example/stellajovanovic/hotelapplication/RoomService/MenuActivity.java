package com.example.stellajovanovic.hotelapplication.RoomService;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.stellajovanovic.hotelapplication.R;

public class MenuActivity extends Activity {

    private List<Food> mFoodList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_menu);

        mFoodList = CartHelper.getCatalog(getResources());

        ListView listViewCatalog = (ListView) findViewById(R.id.ListViewMenu);
        listViewCatalog.setAdapter(new FoodAdapter(mFoodList, getLayoutInflater(), false));

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(), FoodDetailsActivity.class);
                productDetailsIntent.putExtra(CartHelper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });

        Button viewShoppingCart = (Button) findViewById(R.id.ButtonViewCart);
        viewShoppingCart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent viewShoppingCartIntent = new Intent(getBaseContext(), CartActivity.class);
                startActivity(viewShoppingCartIntent);
            }
        });

    }
}