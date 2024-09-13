package edu.tesji.appconapi.spinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edu.tesji.appconapi.R;

public class AdaptadorSpinner extends ArrayAdapter<String> {

    private Context context;
    private int[] iconArray;
    private String[] textArray;

    public AdaptadorSpinner(Context context, int[] iconArray, String[] textArray) {
        super(context, R.layout.pilotos_spinner, textArray);
        this.context = context;
        this.iconArray = iconArray;
        this.textArray = textArray;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pilotos_spinner, parent, false);
        }

        ImageView iconImageView = convertView.findViewById(R.id.ivPiloto);
        TextView textView = convertView.findViewById(R.id.tvSelectPiloto);

        iconImageView.setImageResource(iconArray[position]);
        textView.setText(textArray[position]);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pilotos_spinner, parent, false);
        }

        ImageView iconImageView = convertView.findViewById(R.id.ivPiloto);
        TextView textView = convertView.findViewById(R.id.tvSelectPiloto);

        iconImageView.setImageResource(iconArray[position]);
        textView.setText(textArray[position]);

        return convertView;
    }

}
