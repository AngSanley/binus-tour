package ac.id.binus.tour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class CampusDetail extends AppCompatActivity {

    private MaterialToolbar materialToolbar;
    private TextView textViewTitle;
    private VrPanoramaView mVRPanoramaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_detail);
        Intent intent = getIntent();
        int location = intent.getIntExtra("location", 0);
        int campus = intent.getIntExtra("campus", 0);

        materialToolbar = findViewById(R.id.toolbar);
        textViewTitle = findViewById(R.id.toolbar_title);
        mVRPanoramaView = findViewById(R.id.pano_view);

        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (location == 0 && campus == 0) {
            // anggrek
            ft.replace(R.id.fragment_placeholder, new AnggrekFragment());
        } else {
            // other campus
            if (location == 0) {
                // jakarta
                if (campus == 1) {
                    // syahdan
                    ft.replace(R.id.fragment_placeholder,
                            newOtherCampusFragment(
                                    getString(R.string.syahdan),
                                    getString(R.string.syahdan_address),
                                    R.drawable.syahdan,
                                    "syahdan.html"
                            )
                    );
                } else if (campus == 2) {
                    // kijang
                    ft.replace(R.id.fragment_placeholder,
                            newOtherCampusFragment(
                                    getString(R.string.kijang),
                                    getString(R.string.kijang_address),
                                    R.drawable.kijang,
                                    "kijang.html"
                            )
                    );
                } else if (campus == 3) {
                    // alsut
                    ft.replace(R.id.fragment_placeholder,
                            newOtherCampusFragment(
                                    getString(R.string.alsut),
                                    getString(R.string.alsut_address),
                                    R.drawable.alsut,
                                    "alsut.html"
                            )
                    );
                } else if (campus == 4) {
                    // jwc
                    ft.replace(R.id.fragment_placeholder,
                            newOtherCampusFragment(
                                    getString(R.string.jwc),
                                    getString(R.string.jwc_address),
                                    R.drawable.jwc,
                                    "jwc.html"
                            )
                    );

                } else if (campus == 5) {
                    // fx
                    ft.replace(R.id.fragment_placeholder,
                            newOtherCampusFragment(
                                    getString(R.string.fx),
                                    getString(R.string.fx_address),
                                    R.drawable.fx,
                                    "fx.html"
                            )
                    );
                }
            } else if (location == 1) {
                // bandung
                ft.replace(R.id.fragment_placeholder,
                        newOtherCampusFragment(
                                getString(R.string.bandung_campus),
                                getString(R.string.bandung_campus_address),
                                R.drawable.binus_bandung,
                                "bandung.html"
                        )
                );

            } else if (location == 2) {
                // bekasi
                ft.replace(R.id.fragment_placeholder,
                        newOtherCampusFragment(
                                getString(R.string.bekasi_campus),
                                getString(R.string.bekasi_campus_address),
                                R.drawable.binus_bekasi,
                                "bekasi.html"
                        )
                );

            } else if (location == 3) {
                // malang
                ft.replace(R.id.fragment_placeholder,
                        newOtherCampusFragment(
                                getString(R.string.malang_campus),
                                getString(R.string.malang_campus_address),
                                R.drawable.binus_malang,
                                "malang.html"
                        )
                );

            }
        }
        ft.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static OtherCampusFragment newOtherCampusFragment(String campusName, String campusAddress, int campusPicture, String campusHtmlFileName) {
        OtherCampusFragment f = new OtherCampusFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("campusName", campusName);
        args.putString("campusAddress", campusAddress);
        args.putInt("campusPicture", campusPicture);
        args.putString("campusHtmlFileName", campusHtmlFileName);
        f.setArguments(args);
        return f;
    }
}
