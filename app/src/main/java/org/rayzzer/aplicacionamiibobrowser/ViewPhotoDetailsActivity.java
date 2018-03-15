package org.rayzzer.aplicacionamiibobrowser;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Rayzzer on 12/03/2018.
 */

public class ViewPhotoDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amiibo_details);

        activateToolbarWithBackEnabled();

        Intent intent = getIntent();
        Amiibo amiibo = (Amiibo) intent.getSerializableExtra(PHOTO_TRANSFER);

        TextView amiboName = findViewById(R.id.amiibo_name);
        amiboName.setText(amiibo.getName());

        TextView amibotype = findViewById(R.id.amiibo_type);
        amibotype.setText(amiibo.getType());

        TextView amiiboseries = findViewById(R.id.amiibo_amiiboseries);
        amiiboseries.setText(amiibo.getAmiiboSeries());

        ImageView photoImage = findViewById(R.id.photo_image);
        Picasso.with(this).load(amiibo.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(photoImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo_details, menu);

        return true;
    }
}
