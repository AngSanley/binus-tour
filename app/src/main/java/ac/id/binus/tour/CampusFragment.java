package ac.id.binus.tour;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CampusFragment extends Fragment implements CampusesRecyclerViewAdapter.ItemClickListener {

    private CampusesRecyclerViewAdapter adapter;
    private int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_campuses, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle args = getArguments();
        index = args.getInt("index", 0);

        // data to populate the RecyclerView with
        ArrayList<Integer> locationImages = new ArrayList<>();
        ArrayList<String> locationNames = new ArrayList<>();

        switch (index) {
            case 0:
                // jakarta
                locationImages.add(R.drawable.anggrek);
                locationImages.add(R.drawable.syahdan);
                locationImages.add(R.drawable.kijang);
                locationImages.add(R.drawable.alsut);
                locationImages.add(R.drawable.jwc);
                locationImages.add(R.drawable.fx);

                locationNames.add(getString(R.string.anggrek));
                locationNames.add(getString(R.string.syahdan));
                locationNames.add(getString(R.string.kijang));
                locationNames.add(getString(R.string.alsut));
                locationNames.add(getString(R.string.jwc));
                locationNames.add(getString(R.string.fx));
                break;

            case 1:
                // bandung
                locationImages.add(R.drawable.binus_bandung);
                locationNames.add(getString(R.string.bandung_campus));
                break;

            case 2:
                // bekasi
                locationImages.add(R.drawable.binus_bekasi);
                locationNames.add(getString(R.string.bekasi_campus));
                break;

            case 3:
                // malang
                locationImages.add(R.drawable.binus_malang);
                locationNames.add(getString(R.string.malang_campus));
                break;
        }

        // set up the RecyclerView
        RecyclerView recyclerView = getActivity().findViewById(R.id.rv_campuses);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CampusesRecyclerViewAdapter(getContext(), locationImages, locationNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(getActivity(), CampusDetail.class);
        i.putExtra("location", index);
        i.putExtra("campus",position);
        startActivity(i);
    }
}