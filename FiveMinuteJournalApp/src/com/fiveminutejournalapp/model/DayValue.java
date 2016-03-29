package com.fiveminutejournalapp.model;

import java.io.Serializable;

/**
 * Created by ekaterina on 29.03.2016.
 */
public class DayValue implements Serializable {
	private static final long serialVersionUID = -2868301860227644365L;

	private String id;
	private User author;
	private String citation;
	private String date;
	private String[] gratefulFor = new String[3];
	private String[] whatwouldimprove = new String[3];
	private String[] amazingtoday = new String[3];
	private String[] howmakebetter = new String[3];

	private DayValue(DayValueBuilder builder) {
		this.id = builder.nestedId;
		this.author = builder.nestedAuthor;
		this.citation = builder.nestedCitation;
		this.date = builder.nestedDate;
		this.amazingtoday = builder.nestedAmazingtoday;
		this.gratefulFor = builder.nestedGratefulFor;
		this.whatwouldimprove = builder.nestedWhatwouldimprove;
		this.howmakebetter = builder.nestedHowmakebetter;
	}

	public static class DayValueBuilder {
		private String nestedId;
		private User nestedAuthor;
		private String nestedCitation;
		private String nestedDate;
		private String[] nestedGratefulFor = new String[3];
		private String[] nestedWhatwouldimprove = new String[3];
		private String[] nestedAmazingtoday = new String[3];
		private String[] nestedHowmakebetter = new String[3];

		public DayValueBuilder(User nestedAuthor) {
			this.nestedAuthor = nestedAuthor;
		}

		public DayValueBuilder(DayValue value) {
			this.nestedAuthor = value.author;
			this.nestedId = value.id;
			this.nestedCitation = value.citation;
			this.nestedDate = value.date;
			this.nestedGratefulFor = value.gratefulFor;
			this.nestedAmazingtoday = value.amazingtoday;
			this.nestedWhatwouldimprove = value.whatwouldimprove;
			this.nestedHowmakebetter = value.howmakebetter;
		}

		public DayValueBuilder setAuthor(User author) {
			this.nestedAuthor = author;
			return this;
		}
		
		public DayValueBuilder setDate(String date) {
			this.nestedDate = date;
			return this;
		}

		/**
		 * Builds the instance of the day value
		 * 
		 * @return the built day value entity
		 */
		public DayValue build() {
			return new DayValue(this);
		}
	}
}
