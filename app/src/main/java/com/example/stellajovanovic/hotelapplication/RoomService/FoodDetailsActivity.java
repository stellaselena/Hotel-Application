package com.example.stellajovanovic.hotelapplication.RoomService;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stellajovanovic.hotelapplication.R;

public class FoodDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details);

        List<Food> catalog = CartHelper.getCatalog(getResources());

        int productIndex = getIntent().getExtras().getInt(
                CartHelper.PRODUCT_INDEX);
        final Food selectedFood = catalog.get(productIndex);

        ImageView imageView = (ImageView) findViewById(R.id.ImageViewFood);
        imageView.setImageDrawable(selectedFood.productImage);
        TextView foodTitle = (TextView) findViewById(R.id.TextViewFoodTitle);
        foodTitle.setText(selectedFood.title);
        TextView foodDetails = (TextView) findViewById(R.id.TextViewFoodDetails);
        foodDetails.setText(selectedFood.description);
        TextView foodPrice = (TextView)findViewById(R.id.TextViewFoodPrice);
        foodPrice.setText(selectedFood.price + "NOK");

        TextView textViewCurrentQuantity = (TextView) findViewById(R.id.textViewCurrentlyInCart);
        textViewCurrentQuantity.setText("Currently in Cart: " + CartHelper.getProductQuantity(selectedFood));

        final EditText editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);

        Button addToCartButton = (Button) findViewById(R.id.ButtonAddToCart);
        addToCartButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                int quantity = 0;
                try {
                    quantity = Integer.parseInt(editTextQuantity.getText()
                            .toString());

                    if (quantity < 0) {
                        Toast.makeText(getBaseContext(), "Please enter a quantity above 0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Please enter a numeric quantity", Toast.LENGTH_SHORT).show();

                    return;
                }

                CartHelper.setQuantity(selectedFood, quantity);
                finish();
            }
        });

    }

}
