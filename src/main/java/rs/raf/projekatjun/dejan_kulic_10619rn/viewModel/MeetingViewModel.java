package rs.raf.projekatjun.dejan_kulic_10619rn.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekatjun.dejan_kulic_10619rn.database.entity.Meeting;

public class MeetingViewModel extends ViewModel {

    private final MutableLiveData<List<Meeting>> meetings = new MutableLiveData<>();
    private final ArrayList<Meeting> meetingList = new ArrayList<>();

    public LiveData<List<Meeting>> getMeetings() {
        return meetings;
    }
}
