package rs.raf.projekatjun.dejan_kulic_10619rn.view.recycler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Function;

import rs.raf.projekatjun.dejan_kulic_10619rn.R;
import rs.raf.projekatjun.dejan_kulic_10619rn.database.entity.Meeting;

import static android.graphics.Color.RED;

public class MeetingAdapter extends ListAdapter<Meeting, MeetingAdapter.ViewHolder> {
    private final Function<Meeting, Void> onCarClicked;
    private Function<Meeting,Void> onDeleteClicked;

    public MeetingAdapter(@NonNull DiffUtil.ItemCallback<Meeting> diffCallback, Function<Meeting, Void> onCarClicked, Function<Meeting,Void> onDeleteClicked) {
        super(diffCallback);
        this.onCarClicked = onCarClicked;
        this.onDeleteClicked = onDeleteClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_single_event, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
            Meeting meeting = getItem(position);
            onCarClicked.apply(meeting);
            return null;
        },deleteEv ->{
                Meeting meet = getItem(deleteEv);
                onDeleteClicked.apply(meet);
                return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = getItem(position);
        holder.bind(meeting);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked, Function<Integer,Void> onDeleteEvClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.apply(getAdapterPosition());
                }
            });
            itemView.findViewById(R.id.deleteButton).setOnClickListener( v->{
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onDeleteEvClicked.apply(getAdapterPosition());
                }
            });
        }

        public void bind(Meeting meeting) {
            if(meeting.getOdabrano().equals("High")){
                ((TextView)itemView.findViewById(R.id.eventNameTv)).setBackgroundColor(Color.parseColor("#FF0000"));
                ((TextView)itemView.findViewById(R.id.eventDescriptionTv)).setBackgroundColor(Color.parseColor("#FF0000"));
                ((TextView)itemView.findViewById(R.id.eventLinkTv)).setBackgroundColor(Color.parseColor("#FF0000"));
                ((TextView)itemView.findViewById(R.id.eventTimeTv)).setBackgroundColor(Color.parseColor("#FF0000"));
                itemView.findViewById(R.id.deleteButton).setBackgroundColor(Color.parseColor("#FF0000"));
            }else if(meeting.getOdabrano().equals("Medium")){
                ((TextView)itemView.findViewById(R.id.eventNameTv)).setBackgroundColor(Color.parseColor("#00FF00"));
                ((TextView)itemView.findViewById(R.id.eventDescriptionTv)).setBackgroundColor(Color.parseColor("#00FF00"));
                ((TextView)itemView.findViewById(R.id.eventLinkTv)).setBackgroundColor(Color.parseColor("#00FF00"));
                ((TextView)itemView.findViewById(R.id.eventTimeTv)).setBackgroundColor(Color.parseColor("#00FF00"));

                itemView.findViewById(R.id.deleteButton).setBackgroundColor(Color.parseColor("#00FF00"));
            }else if(meeting.getOdabrano().equals("Low")){
                ((TextView)itemView.findViewById(R.id.eventNameTv)).setBackgroundColor(Color.parseColor("#55D3D3D3"));
                ((TextView)itemView.findViewById(R.id.eventDescriptionTv)).setBackgroundColor(Color.parseColor("#55D3D3D3"));
                ((TextView)itemView.findViewById(R.id.eventLinkTv)).setBackgroundColor(Color.parseColor("#55D3D3D3"));
                ((TextView)itemView.findViewById(R.id.eventTimeTv)).setBackgroundColor(Color.parseColor("#55D3D3D3"));

                itemView.findViewById(R.id.deleteButton).setBackgroundColor(Color.parseColor("#55D3D3D3"));
            }
            ((TextView)itemView.findViewById(R.id.eventNameTv)).setText(meeting.getName());
            ((TextView)itemView.findViewById(R.id.eventDescriptionTv)).setText(meeting.getDescription());
            ((TextView)itemView.findViewById(R.id.eventLinkTv)).setText(meeting.getUrl());
            ((TextView)itemView.findViewById(R.id.eventTimeTv)).setText(meeting.getTime());
        }

    }

}
