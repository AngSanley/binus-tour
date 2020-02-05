package ac.id.binus.tour;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class OtherCampusFragment extends Fragment {

    private WebView webView;
    private ImageView campusPictureView;
    private TextView campusNameView;
    private TextView campusAddressView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_other_campus, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle args = getArguments();
        int campusPicture = args.getInt("campusPicture", 0);
        String campusName = args.getString("campusName");
        String campusAddress = args.getString("campusAddress");
        String campusHtmlFileName = args.getString("campusHtmlFileName");

        webView = getActivity().findViewById(R.id.webview);
        campusPictureView = getActivity().findViewById(R.id.header_imageview);
        campusNameView = getActivity().findViewById(R.id.campus_name_text);
        campusAddressView = getActivity().findViewById(R.id.campus_address_text);

        // load webview content
        webView.loadDataWithBaseURL("file:///android_asset/", getHtmlFromAssets(campusHtmlFileName),"text/html","utf-8",null);

        // change picture
        campusPictureView.setImageResource(campusPicture);
        campusNameView.setText(campusName);
        campusAddressView.setText(campusAddress);

        campusPictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), getString(R.string.sorry_no_vr), Toast.LENGTH_LONG).show();
            }
        });

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
}