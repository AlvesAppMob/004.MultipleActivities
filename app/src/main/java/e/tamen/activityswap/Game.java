package e.tamen.activityswap;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
    private String name;
    private String description;
    private String imgPath;

    Game(String name, String desc, String path){
        this.name = name;
        this.description = desc;
        this.imgPath = path;
    }

    Game(Parcel source){
        name = source.readString();
        description = source.readString();
        imgPath = source.readString();
    }

    public void SetName(String name) {this.name = name;}
    public String GetName() {return this.name;}

    public void SetDescription(String description){ this.description = description;}
    public String GetDescription() {return this.description;}

    public void SetImgPath(String imgPath){this.imgPath = imgPath;}
    public String GetImgPath(){ return this.imgPath;}

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imgPath);
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>(){
        @Override
        public Game createFromParcel(Parcel source){
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size){
            return new Game[size];
        }
    };
}
