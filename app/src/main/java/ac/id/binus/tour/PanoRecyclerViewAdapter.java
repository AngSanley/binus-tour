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

public class PanoRecyclerViewAdapter extends RecyclerView.Adapter<PanoRecyclerViewAdapter.ViewHolder> {

    private List<Integer> mLocationImages;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    PanoRecyclerViewAdapter(Context context, List<Integer> locationImages) {
        this.mInflater = LayoutInflater.from(context);
        this.mLocationImages = locationImages;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_pano_pictures, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer locationImage = mLocationImages.get(position);
        holder.imageViewLocation.setImageResource(locationImage);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mLocationImages.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewLocation;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            imageViewLocation = itemView.findViewById(R.id.rv_image);
            cardView = itemView.findViewById(R.id.rv_cardview);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
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