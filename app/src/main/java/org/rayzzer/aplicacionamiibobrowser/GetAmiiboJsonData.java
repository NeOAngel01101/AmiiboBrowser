package org.rayzzer.aplicacionamiibobrowser;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetAmiiboJsonData extends GetRawData {

    private static final String LOG_TAG = GetAmiiboJsonData.class.getSimpleName();

    private List<Amiibo> mAmiibo;
    private Uri mDestinationUri;

    public GetAmiiboJsonData(String searchCriteria, boolean matchAll) {
        super(null);
        createAndUpdateUri(searchCriteria, matchAll);
        mAmiibo = new ArrayList<>();
    }

    public List<Amiibo> getAmiibo() {
        return mAmiibo;
    }

    private boolean createAndUpdateUri(String searchCriteria, boolean matchAll) {
        final String AMIIBO_BASE_API_URL = "http://www.amiiboapi.com/api/amiibo/";
        final String TAGS_PARAM = "tags";
        final String TAGMODE_PARAM = "tagmode";
        final String FORMAT_PARAM = "format";

        mDestinationUri = Uri.parse(AMIIBO_BASE_API_URL).buildUpon()
                .appendQueryParameter(TAGS_PARAM, searchCriteria)
                .appendQueryParameter(TAGMODE_PARAM, (matchAll?"all":"any"))
                .appendQueryParameter(FORMAT_PARAM, "json")
                .build();

        return mDestinationUri != null;
    }

    private void processResult() {
        final String AMIIBO_amiibo = "amiibo";
        final String AMIIBO_NAME = "name";
        final String AMIIBO_Type = "type";
        final String AMIIBO_amiiboSeries = "amiiboSeries";
        final String AMIIBO_IMAGE = "image";


        if ( getDownloadStatus() != DownloadStatus.OK ) {
            Log.e(LOG_TAG, "No se ha descargado el JSON");
            return;
        }

        try {
            JSONObject jsonData = new JSONObject(getData());
            JSONArray itemsArray = jsonData.getJSONArray(AMIIBO_amiibo);

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject jsonPhoto = itemsArray.getJSONObject(i);
                String name = jsonPhoto.getString(AMIIBO_NAME);
                String type = jsonPhoto.getString(AMIIBO_Type);
                String tipoamibo = jsonPhoto.getString(AMIIBO_amiiboSeries);
                String photoUrl =  jsonPhoto.getString(AMIIBO_IMAGE);

                Amiibo amiibo = new Amiibo(name,type,tipoamibo,photoUrl);
                mAmiibo.add(amiibo);
            }

            for(Amiibo amiibo: mAmiibo){
                Log.d(LOG_TAG, "Amiibo: " + amiibo.toString());
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "No se puede crear el objeto JSON");
            e.printStackTrace();
        }
    }

    public void execute() {
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Build Uri: " + mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public class DownloadJsonData extends DownloadRawData {

        @Override
        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();
        }

        @Override
        protected String doInBackground(String... params) {
            String [] par = { mDestinationUri.toString() };

            return super.doInBackground(par);
        }
    }
}