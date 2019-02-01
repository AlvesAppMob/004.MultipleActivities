package e.tamen.activityswap;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity {

    private Intent localIntent;
    private TextView txt_name;
    private TextView txt_desc;
    private ImageView img_thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Initialization();
        //Toast.makeText(getApplicationContext(), name + " " + firstName, Toast.LENGTH_SHORT).show();
    }

    public void launchFirstActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Initialization(){

        txt_name = findViewById(R.id.DESC_TXT_Name);
        txt_desc = findViewById(R.id.TXT_Description);
        img_thumbnail = findViewById(R.id.IMG_Thumbnail);

        localIntent = getIntent();

        Game game = (Game)localIntent.getExtras().getParcelable("gameInfos");
        Log.i("LOGINFO", game.GetName());

        InputStream imageStream = null;
        Log.i("LOGINFO", game.GetImgPath().toLowerCase() + ".jpg");


        try{
            imageStream = getAssets().open(game.GetImgPath().toLowerCase() + ".jpg");
        }catch (IOException e){
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

        img_thumbnail.setImageBitmap(bitmap);
        txt_name.setText(game.GetName());
        txt_desc.setText(game.GetDescription());
    }

}

