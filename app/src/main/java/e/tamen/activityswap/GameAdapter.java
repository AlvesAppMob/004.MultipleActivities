package e.tamen.activityswap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GameAdapter extends ArrayAdapter<Game> {

    static class ViewHolder{
        TextView name;
        ImageView image;
    }

    ArrayList<Game> games;
    Context context;
    Resources res;
    int viewRes;

    public GameAdapter(Context context, int textViewResourceId, ArrayList<Game> games) {
        super(context, textViewResourceId, games);
        this.games = games;
        this.context = context;
        this.res = context.getResources();
        this.viewRes = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        ViewHolder holder;

        if(view == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewRes, parent, false);
            holder = new ViewHolder();
            holder.name  = view.findViewById(R.id.TXT_Name);
            holder.image = view.findViewById(R.id.IMG_Thumbnail);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        final Game item = games.get(position);

        if(item != null) {
            final String planetName = String.format(item.GetName());
            Bitmap planetImage = null;

            try {
                InputStream ims = getContext().getAssets().open(item.GetImgPath().toLowerCase() + ".jpg");
                if(ims != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(ims);
                    planetImage = bitmap;
                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
            holder.name.setText(planetName);
            holder.image.setImageBitmap(planetImage);
        }
        return view;
    }


}
