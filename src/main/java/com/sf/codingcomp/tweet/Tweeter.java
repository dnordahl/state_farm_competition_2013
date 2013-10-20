package com.sf.codingcomp.tweet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

public class Tweeter {
	
	List<Tweet> allTweets = new ArrayList<Tweet>();

	public void tweet(String text, User user) throws TweetTooLongException {
		if (text.length() > 140)
			throw new TweetTooLongException();
		
		Tweet t = new Tweet(text, user);
		user.getFeed().getTweets().add(t);
		for (User follower : user.getFollowers())
			follower.getFeed().getTweets().add(t);
	}

	/**
	 * This method finds the usernames of all other users that are mentioned in
	 * the user's feed, sorted alphabetically, case-insensitive.
	 * 
	 * @param user
	 * @return
	 */
	public List<String> findMentions(User user) {
		List<String> mentions = new ArrayList<String>();
		
		for (Tweet t : user.getFeed().getTweets()) {
			mentionloop:
			for (String a : t.getMentions())
			{
				for (String b : mentions) {
					if (b.equalsIgnoreCase(a))
						continue mentionloop;
				}
				mentions.add(a);
			}
		}
		mentions.remove("@" + user.username);
		Collections.sort(mentions);
		
		return mentions;
	}

	/**
	 * This method finds the hashtags that appear most often in the user's feed,
	 * sorted by number of occurrences.
	 * 
	 * @param user
	 * @param howMany
	 * @return
	 */
	public List<Hashtag> findMostPopularHashtags(User user, int howMany) {
		HashMap<String,Integer> hashtags = new HashMap<String,Integer>();
		
		for (Tweet t : user.getFeed().getTweets()) {
			for (String hashtag : t.getHashtags())
			{
				if (hashtags.containsKey(hashtag)) {
					Integer count = hashtags.get(hashtag);
					count++;
				}
				else
					hashtags.put(hashtag, 0);
			}
		}
		
		//List<Hashtag> hashtags = new ArrayList<Hashtag>();
		return null;
	}

	/**
	 * This method finds the most recent tweets authored by the user.
	 * 
	 * @param user
	 * @param howMany
	 *            - number of results
	 * @return
	 */
	public List<Tweet> findMostRecentTweets(User user, int howMany) {
		List<Tweet> list = new ArrayList<Tweet>();
		List<Tweet> userTweets = user.getFeed().getTweets();
		
		int lowerboundary = (howMany >= userTweets.size()) ? 0 : userTweets.size() - howMany - 1;
		
		for (int i = userTweets.size() - 1; i >= lowerboundary; i--)
			list.add(userTweets.get(i));
		
		return list;
	}

	/**
	 * This method finds the follower of the user with the most authored tweets.
	 * Returns null if the user has no followers
	 * 
	 * @param user
	 * @return
	 */
	public User findMostActiveFollower(User user) {
		if (user.getFollowers().size() == 0)
			return null;
		
		User mostActive = user.getFollowers().get(1);
		
		for (User follower : user.getFollowers()) {
			if (follower.getFeed().getTweets().size() > mostActive.getFeed().getTweets().size())
				mostActive = follower;
		}
		
		return mostActive;
	}
}
