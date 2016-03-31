package com.fiveminutejournalapp.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fiveminutejournalapp.R;
import com.fiveminutejournalapp.authorization.LogInFragment;
import com.fiveminutejournalapp.content.adapters.DayValueTimelineListAdapter;
import com.fiveminutejournalapp.model.DayValue;
import com.fiveminutejournalapp.model.User;

public class TimelineFragment extends ListFragment {
	public static final String DAY_ITEM = "dayitem";
	private DayValueTimelineListAdapter timelineListAdapter;

	public static Fragment newInstance(User connectedUser) {
		Bundle args = new Bundle();
		args.putSerializable(LogInFragment.USER_TAG, connectedUser);
		TimelineFragment fragment = new TimelineFragment();

		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.timelineListAdapter = new DayValueTimelineListAdapter(getActivity().getApplicationContext());
		addDayValueItemsToAdapter();
		setListAdapter(this.timelineListAdapter);
	}

	private void addDayValueItemsToAdapter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ListView lv = getListView();

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Object item = timelineListAdapter.getItem(position);
				if (item instanceof DayValue) {
					DayValue dayValueItem = (DayValue) item;
					Intent intent = new Intent(getActivity(), DayActivity.class);
					intent.putExtra(DAY_ITEM, dayValueItem);
					startActivity(intent);
				}
			}
		});
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeline_list_fragment, container, false);
        return view;
    }

}
