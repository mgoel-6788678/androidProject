package com.example.evento;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowEventsFragment extends Fragment {

    RecyclerView recyclerView;
    ShowEventsAdapter showEventsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_events, container, false);

        recyclerView = view.findViewById(R.id.show_events_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //FIRE BASE
        FirebaseRecyclerOptions<EventsClass> options =
                new FirebaseRecyclerOptions.Builder<EventsClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Events"), EventsClass.class)
                        .build();

        showEventsAdapter = new ShowEventsAdapter(options);
        recyclerView.setAdapter(showEventsAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        showEventsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        showEventsAdapter.stopListening();
    }

}