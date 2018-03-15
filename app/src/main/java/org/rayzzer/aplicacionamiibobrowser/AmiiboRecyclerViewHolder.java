package org.rayzzer.aplicacionamiibobrowser;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rayzzer on 12/03/2018.
 */

public class AmiiboRecyclerViewHolder extends RecyclerView.ViewHolder {

    private ImageView mThumbnail;
    public TextView mName;

    public AmiiboRecyclerViewHolder(View itemView) {
        super(itemView);
        mThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
        mName = itemView.findViewById(R.id.textViewTitle);
    }

    public ImageView getThumbnail() {
        return mThumbnail;
    }
}





