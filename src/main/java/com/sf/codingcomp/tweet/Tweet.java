package com.sf.codingcomp.tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tweet {

	private String text;
	private User author;

	public Tweet(String text, User author) {
		this.text = text;
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	public List<String> getMentions() {
		Pattern regex = Pattern.compile("@\\w+");
		Matcher match = regex.matcher(getText());
		List<String> mentions = new ArrayList<String>();
		
		while (match.find())
			mentions.add(match.group());
		
		return mentions;
	}
	
	public List<String> getHashtags() {
		Pattern regex = Pattern.compile("#\\w+");
		Matcher match = regex.matcher(getText());
		List<String> hashtags = new ArrayList<String>();
		
		while (match.find())
			hashtags.add(match.group());
		
		return hashtags;
	}

}
