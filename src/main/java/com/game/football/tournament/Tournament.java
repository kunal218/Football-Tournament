package com.game.football.tournament;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.game.football.file.FileOperations;
import com.game.football.team.Team;
import com.game.football.thread.MyThread;

public class Tournament {

	private ArrayList<Team> participantList = new ArrayList<Team>();
	private ArrayList<Team> winnerList = new ArrayList<Team>();
	private static int roundNumber = 1;
	private MyThread myThread;

	public ArrayList<Team> getParticipantList() {
		return participantList;
	}

	public void setParticipantList(ArrayList<Team> participantList) {
		this.participantList = participantList;
	}

	public ArrayList<Team> getWinnerList() {
		return winnerList;
	}

	public void setWinnerList(ArrayList<Team> winnerList) {
		this.winnerList = winnerList;
	}

	/**
	 * This methods schedules threads using executor service and returns the size of
	 * winList after one round
	 * 
	 * @param teams
	 * @return size of winlist
	 */
	public int startTheTournamentEvenTeams(int teams) {
		int sizeOfPool = teams / 2;

		ExecutorService executorService = Executors.newFixedThreadPool(sizeOfPool);

		setWinnerList(getParticipantList());
		FileOperations fileOperations = new FileOperations();
		myThread = new MyThread();
		long start = System.currentTimeMillis();
		fileOperations.addRoundNumberToFile(roundNumber++, myThread.getWinnerPath(), myThread.getLoserPath(),
				myThread.getDrawPath());
		for (int i = 0; i < (getWinnerList().size()); i += 2) {
			Team team1 = getWinnerList().get(i);
			Team team2 = getWinnerList().get(i + 1);
			executorService.execute(new MyThread(team1, team2));

		}

		executorService.shutdown();
		while (!executorService.isTerminated()) {
		}

		System.out.println("In Winner size: " + myThread.getWinList().size());
		System.out.println("In Winner " + myThread.getWinList().toString());
		System.out.println(System.currentTimeMillis() - start);

		return (myThread.getWinList().size());
	}

	/**
	 * This method also schedules threads but it is wriiten to handle odd number of
	 * teams in which the odd team plays with winners of other matches in that round
	 * 
	 * @param teams
	 * @return size of WinList
	 */
	public int startTheTournamentOddTeams(int teams) {
		int sizeOfPool = teams / 2;

		ExecutorService executorService = Executors.newFixedThreadPool(sizeOfPool);

		setWinnerList(getParticipantList());
		FileOperations fileOperations = new FileOperations();
		myThread = new MyThread();
		long start = System.currentTimeMillis();
		fileOperations.addRoundNumberToFile(roundNumber++, myThread.getWinnerPath(), myThread.getLoserPath(),
				myThread.getDrawPath());
		Team odd = getWinnerList().get((getWinnerList().size()) - 1);
		myThread.getWinList().add(odd);
		File winnerFile = new File("Winner.txt");
		FileWriter fileWriter1 = null;
		BufferedWriter bufferedWriter = null;
	
		try {
			winnerFile.createNewFile();
				
			fileWriter1 = new FileWriter(winnerFile, true);

			bufferedWriter = new BufferedWriter(fileWriter1);
			synchronized (bufferedWriter) {

				bufferedWriter.write(odd.getTeamName() + " ");
				bufferedWriter.newLine();
				
				bufferedWriter.close();
				fileWriter1.close();

			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}


		for (int i = 0; i < ((getWinnerList().size()) - 1); i += 2) {
			Team team1 = getWinnerList().get(i);
			Team team2 = getWinnerList().get(i + 1);
			executorService.execute(new MyThread(team1, team2));

		}

		executorService.shutdown();
		while (!executorService.isTerminated()) {
		}

		System.out.println(" Total Number of Winners: " + myThread.getWinList().size());
		System.out.println(" Winners " + myThread.getWinList().toString());
		System.out.println("\n Execution Time :" + (System.currentTimeMillis() - start));

		return (myThread.getWinList().size());
	}

}
