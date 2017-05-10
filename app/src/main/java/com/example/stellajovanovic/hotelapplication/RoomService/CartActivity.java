package com.example.stellajovanovic.hotelapplication.RoomService;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.stellajovanovic.hotelapplication.R;

public class CartActivity extends Activity {

    private List<Food> mCartList;
    private FoodAdapter mFoodAdapter;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);


        mCartList = CartHelper.getCartList();

        for (int i = 0; i < mCartList.size(); i++) {
            mCartList.get(i).selected = false;
        }

        final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewMenu);
        mFoodAdapter = new FoodAdapter(mCartList, getLayoutInflater(), true);
        listViewCatalog.setAdapter(mFoodAdapter);

        listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent productDetailsIntent = new Intent(getBaseContext(), FoodDetailsActivity.class);
                productDetailsIntent.putExtra(CartHelper.PRODUCT_INDEX, position);
                startActivity(productDetailsIntent);
            }
        });
        mButton = (Button) findViewById(R.id.Button02);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, RoomServiceActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mFoodAdapter != null) {
            mFoodAdapter.notifyDataSetChanged();
        }
    }

}
