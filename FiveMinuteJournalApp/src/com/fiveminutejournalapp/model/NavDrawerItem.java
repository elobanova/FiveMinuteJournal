package com.fiveminutejournalapp.model;

/**
 * Created by ekaterina on 30.03.2016.
 */
public class NavDrawerItem implements IListItem {

	private static final long serialVersionUID = -5310828615433027016L;
	private String title;
	private int icon;

	public NavDrawerItem(String title, int icon) {
		this.title = title;
		this.icon = icon;
	}

	public String getTitle() {
		return this.title;
	}

	public int getIcon() {
		return this.icon;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
}