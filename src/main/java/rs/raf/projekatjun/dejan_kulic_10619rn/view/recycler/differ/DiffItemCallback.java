package rs.raf.projekatjun.dejan_kulic_10619rn.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekatjun.dejan_kulic_10619rn.database.entity.Meeting;

public class DiffItemCallback extends DiffUtil.ItemCallback<Meeting> {
    @Override
    public boolean areItemsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
        return oldItem.getName().equals(newItem.getName())
                && oldItem.getDescription().equals(newItem.getDescription())
                && oldItem.getUrl().equals(newItem.getUrl());
    }
}
