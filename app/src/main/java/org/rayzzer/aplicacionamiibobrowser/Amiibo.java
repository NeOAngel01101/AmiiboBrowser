package org.rayzzer.aplicacionamiibobrowser;

import java.io.Serializable;


public class Amiibo implements Serializable {

    private static final long serialVersionUID = 3809936409831543140L;

    private String mName;
    private String mType;
    private String mAmiiboSeries;
    private String mImage;


    public Amiibo(String mName, String mType, String mAmiiboSeries, String mImage) {
        this.mName = mName;
        this.mType = mType;
        this.mAmiiboSeries = mAmiiboSeries;
        this.mImage = mImage;
    }


    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }

    public String getAmiiboSeries() {
        return mAmiiboSeries;
    }

    public String getImage() {
        return mImage;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Amiibo{" +
                ", mName='" + mName + '\'' +
                ", mType='" + mType + '\'' +
                ", mAmiiboSeries='" + mAmiiboSeries + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
