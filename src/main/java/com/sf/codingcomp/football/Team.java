package com.sf.codingcomp.football;

import java.util.ArrayList;
import java.util.List;

public class Team {

	private List<Player> players = new ArrayList<Player>();

	public void addPlayer(Player player, LeagueOptions options) throws TooManyPlayersException {
		int count = 0;
		for (int i = 0; i < players.size(); i++){
			if (player.getClass() == (players.get(i)).getClass()){
				count++;
				if (count >= options.getMaximumPlayersAtEachPosition()){
					throw new TooManyPlayersException();
				}
			}
		}
		players.add(player);
	}

	public boolean wins(Team opponent, LeagueOptions options) {
		double score = this.total(options);
		double oppScore = opponent.total(options);
		if (score > oppScore)
			return true;
		return false;
	}

	public double total(LeagueOptions options) {
		double score = 0;
		for (int i = 0; i < players.size(); i++){
			score += players.get(i).calculateTotalScore(options.isPartialPointsAllowed());
		}
		return score;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
