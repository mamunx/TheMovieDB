package test.pathao.pathaomovie.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.pathao.pathaomovie.Adapters.RecycleAdapter;
import test.pathao.pathaomovie.Interfaces.ApiInterface;
import test.pathao.pathaomovie.Models.ApiResponse;
import test.pathao.pathaomovie.Models.MovieDetail;
import test.pathao.pathaomovie.R;
import test.pathao.pathaomovie.Retrofit.RetrofitApiClient;

public class HomeActivity extends AppCompatActivity {

    private RecycleAdapter topRatedAdapter, nowPlayingAdapter, upcomingAdapter;
    private ApiInterface apiInterface;
    private int totalTopRatedPages, totalNowPlayingPages, totalUpcomingPages;
    private ArrayList<MovieDetail> topRatedList, nowPlayingList, upcomingList;
    private RecyclerView rvTopRated, rvNowPlaying, rvUpcoming;
    private int topRatedPageNumber = 1, nowPlayingPageNumber = 1, upcomingPageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //ButterKnife.bind(this);

        Toolbar homeToolbar = findViewById(R.id.toolbar_home);
        ScrollView rootScrollView = findViewById(R.id.rootScrollView);
        rootScrollView.setVisibility(View.VISIBLE);

        setSupportActionBar(homeToolbar);

        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        getTopRatedList(topRatedPageNumber++);
        getNowPlayingList(nowPlayingPageNumber++);
        getUpcomingList(upcomingPageNumber++);

        // set up the RecyclerView
        rvTopRated = findViewById(R.id.rvTopRated);
        rvNowPlaying = findViewById(R.id.rvNowPlaying);
        rvUpcoming = findViewById(R.id.rvUpcoming);

        final LinearLayoutManager lmTopRated = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);
        final LinearLayoutManager lmNowPlaying = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);
        final LinearLayoutManager lmUpcoming = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);

        rvTopRated.setLayoutManager(lmTopRated);
        rvNowPlaying.setLayoutManager(lmNowPlaying);
        rvUpcoming.setLayoutManager(lmUpcoming);

        rvTopRated.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleThreshold = 1;
                int totalItemCount = lmTopRated.getItemCount();
                int lastVisibleItem = lmTopRated.findLastVisibleItemPosition();

                if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (topRatedPageNumber <= totalTopRatedPages)
                        getTopRatedList(topRatedPageNumber++);
                }
            }
        });

        rvNowPlaying.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleThreshold = 1;
                int totalItemCount = lmNowPlaying.getItemCount();
                int lastVisibleItem = lmNowPlaying.findLastVisibleItemPosition();

                if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (nowPlayingPageNumber <= totalNowPlayingPages)
                        getNowPlayingList(nowPlayingPageNumber++);
                }
            }
        });

        rvUpcoming.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleThreshold = 1;
                int totalItemCount = lmUpcoming.getItemCount();
                int lastVisibleItem = lmUpcoming.findLastVisibleItemPosition();

                if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (upcomingPageNumber <= totalUpcomingPages)
                        getUpcomingList(upcomingPageNumber++);
                }
            }
        });
    }

    private void getTopRatedList(int pageNumber) {
        Call<ApiResponse> call = apiInterface.getTopRatedList(pageNumber);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                try {
                    ApiResponse body = response.body();
                    if (body != null) {
                        totalTopRatedPages = body.getTotalPages();

                        if (topRatedAdapter != null) {
                            topRatedList.addAll(body.getResults());
                            topRatedAdapter.notifyDataSetChanged();
                        } else {
                            topRatedList = body.getResults();
                            topRatedAdapter = new RecycleAdapter(getApplicationContext(), topRatedList);
                            rvTopRated.setAdapter(topRatedAdapter);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getNowPlayingList(int pageNumber) {
        Call<ApiResponse> call = apiInterface.getNowPlayingList(pageNumber);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                try {
                    ApiResponse body = response.body();
                    if (body != null) {
                        totalNowPlayingPages = body.getTotalPages();

                        if (nowPlayingAdapter != null) {
                            nowPlayingList.addAll(body.getResults());
                            nowPlayingAdapter.notifyDataSetChanged();
                        } else {
                            nowPlayingList = body.getResults();
                            nowPlayingAdapter = new RecycleAdapter(getApplicationContext(), nowPlayingList);
                            rvNowPlaying.setAdapter(nowPlayingAdapter);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getUpcomingList(int pageNumber) {
        Call<ApiResponse> call = apiInterface.getUpcomingList(pageNumber);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                try {
                    ApiResponse body = response.body();
                    if (body != null) {
                        totalUpcomingPages = body.getTotalPages();
                        if (upcomingAdapter != null) {
                            upcomingList.addAll(body.getResults());
                            upcomingAdapter.notifyDataSetChanged();
                        } else {
                            upcomingList = body.getResults();
                            upcomingAdapter = new RecycleAdapter(getApplicationContext(), upcomingList);
                            rvUpcoming.setAdapter(upcomingAdapter);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

        searchView.setQueryHint("Search Movies");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // Configure the search info and add any event listeners...
        return super.onCreateOptionsMenu(menu);
    }
}