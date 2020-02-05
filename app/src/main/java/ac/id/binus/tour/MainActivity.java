package ac.id.binus.tour;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.TimeInterpolator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;
import uk.co.deanwild.materialshowcaseview.shape.RectangleShape;

public class MainActivity extends AppCompatActivity implements LocationsRecyclerViewAdapter.ItemClickListener {

    private MaterialToolbar materialToolbar;
    private TextView toolbarTitle;
    private LocationsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialToolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);

        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // data to populate the RecyclerView with
        ArrayList<Integer> locationImages = new ArrayList<>();
        locationImages.add(R.drawable.jakarta);
        locationImages.add(R.drawable.bandung);
        locationImages.add(R.drawable.bekasi);
        locationImages.add(R.drawable.malang);

        ArrayList<String> locationNames = new ArrayList<>();
        locationNames.add("Jakarta");
        locationNames.add("Bandung");
        locationNames.add("Bekasi");
        locationNames.add("Malang");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_locations);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new LocationsRecyclerViewAdapter(this, locationImages, locationNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, newCampusFragmentInstance(0));
        ft.commit();

        // showcase
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        config.setShape(new RectangleShape(200, 400));

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "3252");

        sequence.setConfig(config);

        sequence.addSequenceItem(recyclerView,
                getString(R.string.tutorial_location), getString(R.string.ok));

        sequence.addSequenceItem(findViewById(R.id.fragment_placeholder),
                getString(R.string.tutorial_campus), getString(R.string.ok));

        sequence.start();
    }

    @Override
    public void onItemClick(View view, int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.fragment_placeholder, newCampusFragmentInstance(position));
        ft.commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static CampusFragment newCampusFragmentInstance(int index) {
        CampusFragment f = new CampusFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                showAboutDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.about))
                .setMessage(getString(R.string.about_content))

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })
                .show();
    }
}
