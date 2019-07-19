package test.pathao.pathaomovie.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import test.pathao.pathaomovie.Models.MovieDetail;
import test.pathao.pathaomovie.R;

import static test.pathao.pathaomovie.Util.CommonValues.IMAGE_BASE_URL;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{
    private Context context;
    private ArrayList<MovieDetail> movieList;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public RecycleAdapter(Context context, ArrayList<MovieDetail> movieList) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.movieList = movieList;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_recycle_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = movieList.get(position).getTitle();
        String image = movieList.get(position).getImageUrl();
        String year = movieList.get(position).getReleaseDate();


        holder.tvTitle.setText(title);
        holder.tvYear.setText(year);
        Picasso.with(context)
                .load(IMAGE_BASE_URL + image)
                .placeholder(R.drawable.splash_logo)
                .error(R.drawable.splash_logo)
                .into(holder.ivThumbnail);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivThumbnail;
        TextView tvTitle, tvYear;

        ViewHolder(View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.image_view_movie);
            tvTitle = itemView.findViewById(R.id.text_view_title);
            tvYear = itemView.findViewById(R.id.text_view_year);
        }
    }
}
