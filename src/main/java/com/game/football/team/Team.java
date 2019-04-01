package com.game.football.team;

import java.io.Serializable;

public class Team implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8795043330808449512L;
	private String teamName;
	private int score;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return " Name :"+teamName;
	}
}
