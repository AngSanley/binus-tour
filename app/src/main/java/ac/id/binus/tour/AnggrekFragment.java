package ac.id.binus.tour;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;
import uk.co.deanwild.materialshowcaseview.shape.RectangleShape;

public class AnggrekFragment extends Fragment implements PanoRecyclerViewAdapter.ItemClickListener {

    private VrPanoramaView mVRPanoramaView;
    private PanoRecyclerViewAdapter adapter;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_anggrek, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mVRPanoramaView = getActivity().findViewById(R.id.pano_view);
        webView = getActivity().findViewById(R.id.webview);

        // data to populate the RecyclerView with
        ArrayList<Integer> locationImages = new ArrayList<>();
        locationImages.add(R.drawable.thumb_1);
        locationImages.add(R.drawable.thumb_2);
        locationImages.add(R.drawable.thumb_3);
        locationImages.add(R.drawable.thumb_4);
        locationImages.add(R.drawable.thumb_5);
        locationImages.add(R.drawable.thumb_6);
        locationImages.add(R.drawable.thumb_7);
        locationImages.add(R.drawable.thumb_8);
        locationImages.add(R.drawable.thumb_9);
        locationImages.add(R.drawable.thumb_10);
        locationImages.add(R.drawable.thumb_11);
        locationImages.add(R.drawable.thumb_12);
        locationImages.add(R.drawable.thumb_13);
        locationImages.add(R.drawable.thumb_14);

        // set up the RecyclerView
        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_images);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new PanoRecyclerViewAdapter(getContext(), locationImages);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        // load webview content
        webView.loadDataWithBaseURL("file:///android_asset/", getHtmlFromAssets("anggrek.html"),"text/html","utf-8",null);

        loadPhotoSphere();

        // showcase
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        config.setShape(new RectangleShape(200, 400));
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(getActivity(), "1215");
        sequence.setConfig(config);
        sequence.addSequenceItem(mVRPanoramaView,
                getString(R.string.tutorial_vr), getString(R.string.ok));
        sequence.addSequenceItem(recyclerView,
                getString(R.string.tutorial_more_photos), getString(R.string.ok));
        sequence.start();
    }

    private String getHtmlFromAssets(String s) {
        InputStream is;
        StringBuilder builder = new StringBuilder();
        String htmlString = null;
        try {
            is = getActivity().getAssets().open(s);
            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                htmlString = builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return htmlString;
    }

    @Override
    public void onPause() {
        super.onPause();
        mVRPanoramaView.pauseRendering();
    }

    @Override
    public void onResume() {
        super.onResume();
        mVRPanoramaView.resumeRendering();
    }

    @Override
    public void onDestroy() {
        mVRPanoramaView.shutdown();
        super.onDestroy();
    }

    private void loadPhotoSphere() {
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        InputStream inputStream = null;

        AssetManager assetManager = getActivity().getAssets();
        try {
            inputStream = assetManager.open("pano_anggrek.jpg");
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            mVRPanoramaView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(getActivity(), PanoViewActivity.class);
        i.putExtra("file_name", "anggrek_" + (position + 1) + ".jpg");
        startActivity(i);
    }
}