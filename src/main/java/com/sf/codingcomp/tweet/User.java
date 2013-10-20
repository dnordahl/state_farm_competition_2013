package com.sf.codingcomp.tweet;

import java.util.List;
import java.util.ArrayList;

public class User {

	public String username;
	public Feed feed = new Feed();
	public List<User> followedBy = new ArrayList<User>();
	public List<User> follows = new ArrayList<User>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public void follow(User user) {
		follows.add(user);
		user.followedBy.add(this);
	}

	public void unfollow(User user) {
		follows.remove(user);
		user.followedBy.remove(this);
	}

	public String toString() {
		return username;
	}
	
	public List<User> getFollowers() {
		return followedBy;
	}

}
