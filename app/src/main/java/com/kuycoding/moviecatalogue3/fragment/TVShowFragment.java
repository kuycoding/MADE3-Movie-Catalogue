package com.kuycoding.moviecatalogue3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kuycoding.moviecatalogue3.MainActivity;
import com.kuycoding.moviecatalogue3.R;
import com.kuycoding.moviecatalogue3.adapter.TVShowAdapter;
import com.kuycoding.moviecatalogue3.model.TVShow;
import com.kuycoding.moviecatalogue3.viewModel.TVShowViewModel;

import java.util.ArrayList;

public class TVShowFragment extends Fragment {
    private TVShowAdapter adapter;
    private ProgressBar progressBar;
    private TVShowViewModel tvShowViewModel;
    
    public TVShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new TVShowAdapter();
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tvshow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        
        progressBar = view.findViewById(R.id.progressBar);
        
        tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel.class);
        tvShowViewModel.getTVShow().observe(this, getTv);
        tvShowViewModel.setTvShow("EXTRA_TV");
        
        showLoading(true);
        return view;
    }

    private Observer<ArrayList<TVShow>> getTv = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> tvShows) {
            if (tvShows != null) {
                adapter.setTvData(tvShows);
            }
            showLoading(false);
        }
    };

    private void showLoading(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
    public void onResume(){
        super.onResume();
        // Set title bar
        ((MainActivity) getActivity())
//                .setActionBarTitle("TV Shows");
                .getSupportActionBar().setTitle(R.string.tv_shows);
    }
}
