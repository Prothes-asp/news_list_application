package com.prothes.newslistapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CustomBaseAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<HashMap<String, String>> arrayList;
    private  HashMap<String, String> hashMap;
    public CustomBaseAdapter(HomePage homePage, ArrayList<HashMap<String, String>> arrayList, HashMap<String, String> hashMap) {
        this.context = homePage;
        this.arrayList = arrayList;
        this.hashMap = hashMap;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_items_layout_design,parent,false);
        }
        ImageView listImages = convertView.findViewById(R.id.listImages);
        TextView listCatagory = convertView.findViewById(R.id.listCatagory);
        TextView listTitle = convertView.findViewById(R.id.listTitle);
        TextView listDetails = convertView.findViewById(R.id.listDetails);
        LinearLayout listLinearLayout = convertView.findViewById(R.id.listLinearLayout);

        String catagory,image_url,title,details;

        hashMap = arrayList.get(position);
        catagory = hashMap.get("catagory");
        image_url = hashMap.get("image_url");
        title = hashMap.get("title");
        details = hashMap.get("details");

        listCatagory.setText(""+catagory);
        listTitle.setText(""+title);
        listDetails.setText(""+details);
        Glide.with(context).load(image_url).placeholder(R.drawable.progress_loading).into(listImages);

        // Random Color Set Catagory Bg
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        listCatagory.setBackgroundColor(color);

        listLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsPage.titles = title;
                NewsDetailsPage.catagorys = catagory;
                NewsDetailsPage.detailss = details;
                Bitmap bitmap = ((BitmapDrawable) listImages.getDrawable()).getBitmap();
                NewsDetailsPage.bitmaps  = bitmap;
                context.startActivity(new Intent(context, NewsDetailsPage.class));
            }
        });

        return convertView;
    }


}
