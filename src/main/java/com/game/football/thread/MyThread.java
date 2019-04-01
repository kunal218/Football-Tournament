package com.game.football.thread;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import com.game.football.file.FileOperations;
import com.game.football.team.Team;

public class MyThread implements Runnable {

	private Team homeTeam;
	private Team awayTeam;
	private Random random = new Random();
	private boolean bool = false;
	private FileOperations fileOperations = new FileOperations();
	private static ArrayList<Team> winList = new ArrayList<Team>();
	private String drawPath = "Draw.txt";
	private String winnerPath = "Winner.txt";
	private String loserPath = "Loser.txt";
	
	
	

	public MyThread() {
		
	}

	public MyThread(Team homeTeam, Team awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}

	public ArrayList<Team> getWinList() {
		return winList;
	}
	public String getDrawPath() {
		return drawPath;
	}

	public String getWinnerPath() {
		return winnerPath;
	}

	public String getLoserPath() {
		return loserPath;
	}



	public void run() {

		try {
			if (bool == true)
				wait();
			Thread.sleep(2000);
		
			ArrayList<Team> winner = execute(homeTeam, awayTeam);
			
			Thread.sleep(2000);
			
			synchronized (this) {
				bool = true;
					for (Team team : winner) {
						winList.add(team);
					}
					notifyAll();
			
		} 
		
		
		}catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
		catch (ConcurrentModificationException ex) {
			System.out.println(ex.getMessage());
		}

	}

	/**
	 * This method sets up matches and assigns scores (random ) to hometeam and
	 * awayteam and returns winning team
	 * 
	 * @param homeTeam
	 * @param awayTeam
	 * @return
	 */
	public ArrayList<Team> execute(Team homeTeam, Team awayTeam) {
		ArrayList<Team> arrayList = new ArrayList<Team>();
		homeTeam.setScore(random.nextInt(5));
		awayTeam.setScore(random.nextInt(5));

		if (homeTeam.getScore() == awayTeam.getScore()) {
			System.out.println("\n" + homeTeam.toString() + " Draw " + awayTeam.toString());
			arrayList.add(homeTeam);
			arrayList.add(awayTeam);
			
			fileOperations.addToDraw(homeTeam, awayTeam,drawPath);
			fileOperations.addToFile(homeTeam, awayTeam, 0,winnerPath,loserPath);
			return arrayList;

		}

		if (homeTeam.getScore() > awayTeam.getScore()) {
			arrayList.add(homeTeam);
			fileOperations.addToFile(homeTeam, awayTeam,1,winnerPath,loserPath);
			return arrayList;

		} else if (homeTeam.getScore() < awayTeam.getScore())
			arrayList.add(awayTeam);
		fileOperations.addToFile(awayTeam, homeTeam,1,winnerPath,loserPath);
		return arrayList;

	}

}
