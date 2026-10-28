package com.example.seminar4;

import android.os.Parcel;
import android.os.Parcelable;

public class AutomobilZian implements Parcelable {
    private String marca;
    private int anFabricatie;
    private boolean esteNou;
    private String tipCombustibil;
    private String categorie;
    private float ratingStare;
    private boolean esteInmatriculat;

    public AutomobilZian(String marca, int anFabricatie, boolean esteNou, String tipCombustibil, String categorie, float ratingStare, boolean esteInmatriculat) {
        this.marca = marca;
        this.anFabricatie = anFabricatie;
        this.esteNou = esteNou;
        this.tipCombustibil = tipCombustibil;
        this.categorie = categorie;
        this.ratingStare = ratingStare;
        this.esteInmatriculat = esteInmatriculat;
    }

    protected AutomobilZian(Parcel in) {
        marca = in.readString();
        anFabricatie = in.readInt();
        esteNou = in.readByte() != 0;
        tipCombustibil = in.readString();
        categorie = in.readString();
        ratingStare = in.readFloat();
        esteInmatriculat = in.readByte() != 0;
    }

    public static final Creator<AutomobilZian> CREATOR = new Creator<AutomobilZian>() {
        @Override
        public AutomobilZian createFromParcel(Parcel in) {
            return new AutomobilZian(in);
        }

        @Override
        public AutomobilZian[] newArray(int size) {
            return new AutomobilZian[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeInt(anFabricatie);
        dest.writeByte((byte) (esteNou ? 1 : 0));
        dest.writeString(tipCombustibil);
        dest.writeString(categorie);
        dest.writeFloat(ratingStare);
        dest.writeByte((byte) (esteInmatriculat ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Automobil: " + marca + " (" + anFabricatie + ")\n" +
                "Nou: " + (esteNou ? "Da" : "Nu") + "\n" +
                "Combustibil: " + tipCombustibil + "\n" +
                "Categorie: " + categorie + "\n" +
                "Rating: " + ratingStare + "/5\n" +
                "Înmatriculat: " + (esteInmatriculat ? "Da" : "Nu");
    }
}