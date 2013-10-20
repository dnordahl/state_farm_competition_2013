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
		List<Hashtag> hashtags = new ArrayList<Hashtag>();
		Hashtag temp;
		for (Tweet t : user.getFeed().getTweets()) {
			for (Hashtag hashtag : t.getHashtags())
			{
				if (hashtagInList(hashtags, hashtag)) {
					temp = hashtags.get(findHashtagIndex(hashtags,hashtag));
					temp.setOccurrences(temp.getOccurrences()+1);
				}
				else
					hashtags.add(hashtag);
			}
		}
		List<Hashtag> returnList = new ArrayList<Hashtag>();
		int index;
		for(int i = 0; i < howMany; i++){
			if (hashtags.size() == 0)
				break;
			index = getIndexWithMostOccurrences(hashtags);
			returnList.add(hashtags.get(index));
			hashtags.remove(index);
		}
		return returnList;
		
		//List<Hashtag> hashtags = new ArrayList<Hashtag>();
		return null;
	}
	private boolean hashtagInList(List<Hashtag> hashtags, Hashtag hashtag){
		for (Hashtag h : hashtags){
			if (h.getText().equalsIgnoreCase(hashtag.getText()))
				return true;
		}
		return false;
	}
	private int findHashtagIndex(List<Hashtag> hashtags, Hashtag hashtag){
		for (int i = 0; i < hashtags.size(); i++){
			if (hashtags.get(i).getText().equalsIgnoreCase(hashtag.getText()))
				return i;
		}
		return -1;
	}
	private int getIndexWithMostOccurrences(List<Hashtag> hashtags){
		int maxIndex = -1;
		int max = 0;
		for (int i = 0; i < hashtags.size(); i++){
			if(hashtags.get(i).getOccurrences() > max){
				max = hashtags.get(i).getOccurrences();
				maxIndex = i;
			}
		}
		return maxIndex;
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
