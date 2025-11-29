package com.example.oreoclicker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomAdapter extends ArrayAdapter<Oreos> {

    List<Oreos> list;
    Context context;
    int xmlResource;

    public CustomAdapter(Context context, int resource, List<Oreos> objects) {
        super(context, resource, objects);
        xmlResource = resource;
        list = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterLayout = layoutInflater.inflate(xmlResource, null);

        SharedPreferences sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        AtomicInteger oreos = new AtomicInteger(sharedPreferences.getInt("oreos", 0));
        AtomicInteger oreosPerSecond = new AtomicInteger(sharedPreferences.getInt("OPS", 0));

        TextView count = adapterLayout.findViewById(R.id.textView7);
        Button purchase = adapterLayout.findViewById(R.id.button3);
        TextView price = adapterLayout.findViewById(R.id.textView6);
        TextView name = adapterLayout.findViewById(R.id.textView5);
        ImageView oreo = adapterLayout.findViewById(R.id.imageView3);

        if (oreos.get() < list.get(position).getPrice()) {
            purchase.setEnabled(false);
        } else {
            purchase.setEnabled(true);
        }
        purchase.setOnClickListener(v -> {
            int pos = (int) (Math.random() * 2) + 1;
            if (pos == 1){
                final RotateAnimation rotate = new RotateAnimation(0, 20, Animation.RELATIVE_TO_SELF
                        , 0.5f
                        , Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(500);
                purchase.startAnimation(rotate);
            }
            else if (pos == 2) {
                final RotateAnimation rotate = new RotateAnimation(0, -20, Animation.RELATIVE_TO_SELF
                        , 0.5f
                        , Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(500);
                purchase.startAnimation(rotate);
            }
            MediaPlayer mediaPlayer = MediaPlayer.create((Shop) context, R.raw.click);
            mediaPlayer.start();


            oreos.addAndGet(-list.get(position).getPrice());
            list.get(position).setPrice((int) (list.get(position).getPrice() * 1.1));
            list.get(position).setCount(list.get(position).getCount() + 1);
            oreosPerSecond.addAndGet(list.get(position).getCount() * list.get(position).getOreosPerSecond());
            editor.putInt("oreos", oreos.get());
            editor.putInt("OPS", oreosPerSecond.get());
            editor.apply();
            ((Shop) context).updateOreoCount(oreos.get());
            ((Shop) context).updateOPS(oreosPerSecond.get());

            notifyDataSetChanged();
        });

        count.setText(list.get(position).getCount() + "");
        purchase.setText("Buy");
        name.setText(list.get(position).getName());
        price.setText(list.get(position).getPrice() + " Oreos");
        oreo.setImageResource(list.get(position).getImage());


        return adapterLayout;
    }
}
