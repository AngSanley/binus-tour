package ac.id.binus.tour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationsRecyclerViewAdapter extends RecyclerView.Adapter<LocationsRecyclerViewAdapter.ViewHolder> {

    private List<String> mLocationNames;
    private List<Integer> mLocationImages;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    LocationsRecyclerViewAdapter(Context context, List<Integer> locationImages, List<String> locationNames) {
        this.mInflater = LayoutInflater.from(context);
        this.mLocationNames = locationNames;
        this.mLocationImages = locationImages;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_locations, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String locationName = mLocationNames.get(position);
        Integer locationImage = mLocationImages.get(position);
        holder.textViewLocation.setText(locationName);
        holder.imageViewLocation.setImageResource(locationImage);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mLocationNames.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewLocation;
        TextView textViewLocation;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            textViewLocation = itemView.findViewById(R.id.rv_location_name);
            imageViewLocation = itemView.findViewById(R.id.rv_location_image);
            cardView = itemView.findViewById(R.id.rv_cardview);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mLocationNames.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}