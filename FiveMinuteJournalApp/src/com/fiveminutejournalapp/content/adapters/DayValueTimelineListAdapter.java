package com.fiveminutejournalapp.content.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fiveminutejournalapp.R;
import com.fiveminutejournalapp.model.DayValue;

public class DayValueTimelineListAdapter extends AbstractListAdapter<DayValue> {

	public DayValueTimelineListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view;

		if (convertView == null) {
			view = inflater.inflate(R.layout.timeline_list_item, parent, false);
		} else {
			view = convertView;
		}

		if (view != null) {
			TextView dateText = (TextView) view.findViewById(R.id.date_in_list);
			final DayValue item = (DayValue) getItem(position);
			dateText.setText(item.getDate());

			TextView citeText = (TextView) view.findViewById(R.id.cite_in_list);
			citeText.setText(item.getCitation());
		}
		return view;
	}

}
