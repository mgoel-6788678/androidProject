package com.example.evento;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ShowEventsAdapter extends FirebaseRecyclerAdapter <EventsClass, ShowEventsAdapter.ShowEventsViewHolder>{

    public ShowEventsAdapter(@NonNull FirebaseRecyclerOptions<EventsClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ShowEventsViewHolder holder, int position, @NonNull EventsClass model) {
        holder.date.setText("DATE: "+model.EventDate);
        holder.time.setText("TIME: "+model.EventTime);
        holder.heading.setText(getRef(position).getKey());
    }

    @NonNull
    @Override
    public ShowEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_single_row, parent, false);
        return new ShowEventsViewHolder(view);
    }

    class ShowEventsViewHolder extends RecyclerView.ViewHolder{

        TextView heading;
        TextView date;
        TextView time;
        public ShowEventsViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.show_event_single_row_heading);
            date = itemView.findViewById(R.id.show_event_single_row_date);
            time = itemView.findViewById(R.id.show_event_single_row_time);
        }
    }
}
