package com.khurshid.gufran.awokmovies.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.khurshid.gufran.awokmovies.R;
import com.khurshid.gufran.awokmovies.entity.Movie;

import java.util.List;

/**
 * Created by gufran on 20/1/18.
 */

public class MoviesCollectionAdapter extends RecyclerView.Adapter {


    private final OnItemClickListener mListener;
    private List<Object> mMovieList;
    private final int ITEM_VIEW_TYPE_MOVIE = 1;
    private final int ITEM_VIEW_TYPE_LOADING = 2;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading = false;


    public MoviesCollectionAdapter(List<Object> mMovieList, OnItemClickListener mListener) {
        this.mMovieList = mMovieList;
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_MOVIE) {
            return new MovieCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_view, parent, false));
        } else {
            return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= getItemCount() - 1 && !isLoading && mOnLoadMoreListener != null) {
            isLoading = true;
            mOnLoadMoreListener.onLoadMore();
        }
        if (getItemViewType(position) == ITEM_VIEW_TYPE_MOVIE) {
            MovieCardViewHolder movieViewHolder = (MovieCardViewHolder) holder;
            Movie movie = (Movie) mMovieList.get(position);
            movieViewHolder.setClick(movie, position, mListener);
            movieViewHolder.movieNameTV.setText(movie.getTitle());
            movieViewHolder.movieYearTV.setText(movie.getReleaseDate().split("-")[0]);
            movieViewHolder.movieRatingTV.setText(movie.getVoteAverage() + "");
            movieViewHolder.descriptionTV.setText(movie.getOverview());
            Glide.with(movieViewHolder.moviePosterIV.getContext())
                    .load("http://image.tmdb.org/t/p/w185//" + movie.getPosterPath())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    //.placeholder(R.drawable.loading_spinner)
                    .into(movieViewHolder.moviePosterIV);
        }
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMovieList.get(position) instanceof LoadingProxyEntity) {
            return ITEM_VIEW_TYPE_LOADING;
        } else
            return ITEM_VIEW_TYPE_MOVIE;
    }

    public synchronized void notifyDataSetChangedManually() {
        super.notifyDataSetChanged();
        isLoading = false;
    }


    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public class MovieCardViewHolder extends RecyclerView.ViewHolder {
        TextView movieNameTV, descriptionTV, movieRatingTV, movieYearTV;
        ImageView moviePosterIV;
        CardView movieCardView;

        public MovieCardViewHolder(View itemView) {
            super(itemView);
            movieCardView = itemView.findViewById(R.id.movieCardView);
            moviePosterIV = itemView.findViewById(R.id.moviePosterIV);
            movieNameTV = itemView.findViewById(R.id.movieNameTV);
            descriptionTV = itemView.findViewById(R.id.descriptionTV);
            movieRatingTV = itemView.findViewById(R.id.movieRatingTV);
            movieYearTV = itemView.findViewById(R.id.movieYearTV);
        }


        public void setClick(final Movie movie, final int position, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(movie, position);
                }
            });
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public interface OnItemClickListener {
        void onClick(Movie movie, int position);
    }
}
