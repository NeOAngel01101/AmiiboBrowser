package org.rayzzer.aplicacionamiibobrowser;

/**
 * Created by Rayzzer on 12/03/2018.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;


public class AmiiboRecyclerViewAdapter extends RecyclerView.Adapter<AmiiboRecyclerViewHolder> {
    private static final String LOG_TAG = AmiiboRecyclerViewAdapter.class.getSimpleName();

    private List<Amiibo> mAmiiboList;
    private Context     mContext;

    public AmiiboRecyclerViewAdapter(Context context, List<Amiibo> amiiboList) {
        mAmiiboList = amiiboList;
        mContext = context;
    }

    @Override
    public AmiiboRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.browse, null, false);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(lp);

        AmiiboRecyclerViewHolder amiiboRecyclerViewHolder =
                new AmiiboRecyclerViewHolder(view);

        return amiiboRecyclerViewHolder;
    }

    @Override
    public int getItemCount() {
        return (mAmiiboList != null ? mAmiiboList.size() : 0 );
    }

    @Override
    public void onBindViewHolder(AmiiboRecyclerViewHolder holder, int position) {
        // Obtenemos el elemento que va a estar en la posición pedida
        Amiibo amiiboItem = mAmiiboList.get(position);

        Log.d(LOG_TAG, "Processing: " + amiiboItem.getName() + " -> " + Integer.toString(position));

        // Pintamos el thumbnail en la pantalla
        Picasso.with(mContext).load(amiiboItem.getImage())
                .error(R.drawable.placeholder)      // En caso de error
                .placeholder(R.drawable.placeholder)// Mientras descarga
                .into(holder.getThumbnail());

        holder.mName.setText(amiiboItem.getName());
    }

    public void loadNewData(List<Amiibo> amiibos){
        mAmiiboList = amiibos;

        notifyDataSetChanged();
    }

    public Amiibo getPhoto(int position) {
        return (mAmiiboList != null ? mAmiiboList.get(position) : null );
    }
}











