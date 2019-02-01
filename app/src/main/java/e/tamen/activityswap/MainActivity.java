package e.tamen.activityswap;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Game> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Initialization();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void Initialization()
    {
        games = new ArrayList<Game>();

        final String[] nameList = getResources().getStringArray(R.array.names);
        final String[] descList = getResources().getStringArray(R.array.descriptions);
        final String[] imgPathList = getResources().getStringArray(R.array.images);

        listView = findViewById(R.id.LIST_GamesContainer);

        for(int i = 0 ; i < nameList.length ; i++) {
            Game newGameItem = new Game(nameList[i], descList[i], imgPathList[i]);
            games.add(newGameItem);
        }

        final GameAdapter adapter = new GameAdapter(getApplicationContext(), R.layout.list_item, games);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Game newPlanet = (Game) adapter.getItemAtPosition(position);

                //Toast.makeText(getApplicationContext(), newPlanet.GetName(), Toast.LENGTH_LONG).show();
                Intent infos = new Intent(getApplicationContext(), Main2Activity.class);
                infos.putExtra("gameInfos", newPlanet);
                startActivity(infos);
            }
        });
    }
}
