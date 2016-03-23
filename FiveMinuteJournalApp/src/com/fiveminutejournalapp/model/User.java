package com.fiveminutejournalapp.model;

import java.io.Serializable;

/**
 * Created by ekaterina on 23.03.2016.
 */
public class User implements Serializable {
	private static final long serialVersionUID = -811425804649473131L;
	private String emailAddress;
	private String password;
	private String userName;
	private String id;

	private User(UserBuilder builder) {
		this.emailAddress = builder.nestedEmailAddress;
		this.password = builder.nestedPassword;
		this.userName = builder.nestedUserName;
		this.id = builder.nestedId;
	}

	/**
	 * Returns the user's email address
	 * 
	 * @return the user's email address
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getId() {
		return this.id;
	}

	/**
	 * Returns the user's password
	 * 
	 * @return the user's password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * A builder pattern class through which the user's data is being built.
	 */
	public static class UserBuilder {
		private String nestedEmailAddress;
		private String nestedPassword;
		private String nestedUserName;
		private String nestedId;

		public UserBuilder(String userName, String nestedPassword) {
			this.nestedUserName = userName;
			this.nestedPassword = nestedPassword;
		}

		public UserBuilder(User user) {
			this.nestedEmailAddress = user.emailAddress;
			this.nestedPassword = user.password;
			this.nestedUserName = user.userName;
			this.nestedId = user.id;
		}

		/**
		 * Builds the instance of the user
		 * 
		 * @return the built user entity
		 */
		public User build() {
			return new User(this);
		}
	}
}