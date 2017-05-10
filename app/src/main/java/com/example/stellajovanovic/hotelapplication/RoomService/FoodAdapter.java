package com.example.stellajovanovic.hotelapplication.RoomService;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stellajovanovic.hotelapplication.R;

public class FoodAdapter extends BaseAdapter {

    private List<Food> mFoodList;
    private LayoutInflater mInflater;
    private boolean mShowQuantity;

    public FoodAdapter(List<Food> list, LayoutInflater inflater, boolean showQuantity) {
        mFoodList = list;
        mInflater = inflater;
        mShowQuantity = showQuantity;
    }

    @Override
    public int getCount() {
        return mFoodList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFoodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewItem item;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item, null);
            item = new ViewItem();

            item.ImageView = (ImageView) convertView.findViewById(R.id.ImageViewItem);

            item.title = (TextView) convertView.findViewById(R.id.TextViewItem);

            item.quantity = (TextView) convertView.findViewById(R.id.textViewQuantity);

            convertView.setTag(item);
        } else {
            item = (ViewItem) convertView.getTag();
        }

        Food curFood = mFoodList.get(position);

        item.ImageView.setImageDrawable(curFood.productImage);
        item.title.setText(curFood.title);

        if (mShowQuantity) {
            item.quantity.setText("Quantity: " + CartHelper.getProductQuantity(curFood));
        } else {
            item.quantity.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class ViewItem {
        ImageView ImageView;
        TextView title;
        TextView quantity;
    }

}
