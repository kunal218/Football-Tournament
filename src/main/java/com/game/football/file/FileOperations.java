package com.game.football.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.game.football.team.Team;

public class FileOperations {

	/**
	 * This method prints round number to winner.txt , loser.txt , and draw.txt
	 * 
	 * @param roundNumber
	 */
	public void addRoundNumberToFile(int roundNumber, String winnerPath, String loserPath, String drawPath) {

		File loserFile = new File(loserPath);
		try {
			loserFile.createNewFile();

			FileWriter fileWriter = null;
			BufferedWriter bufferedWriter = null;
			fileWriter = new FileWriter(loserFile, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(" \n ******** Round  : \n" + roundNumber + " Started ***********");
			bufferedWriter.newLine();

			bufferedWriter.close();
			fileWriter.close();

			File winnerFile = new File(winnerPath);
			winnerFile.createNewFile();
			fileWriter = new FileWriter(winnerFile, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(" \n ******** Round  : \n" + roundNumber + " Started ***********");
			bufferedWriter.newLine();

			bufferedWriter.close();
			fileWriter.close();

			File drawFile = new File(drawPath);
			drawFile.createNewFile();
			fileWriter = new FileWriter(drawFile, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(" \n ******** Round  : \n" + roundNumber + " Started ***********");
			bufferedWriter.newLine();
			bufferedWriter.close();

			fileWriter.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	/**
	 * This method add both playing teams to draw.txt
	 * 
	 * @param team1
	 * @param team2
	 */
	public void addToDraw(Team team1, Team team2, String path) {
		File drawFile = new File(path);
		try {
			drawFile.createNewFile();
			FileWriter fileWriter = null;
			BufferedWriter bufferedWriter = null;
			fileWriter = new FileWriter(drawFile, true);

			bufferedWriter = new BufferedWriter(fileWriter);
			synchronized (bufferedWriter) {

				bufferedWriter.write(team1.getTeamName() + " Draws with " + team2.getTeamName());
				bufferedWriter.newLine();

			}

			bufferedWriter.close();
			fileWriter.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	/**
	 * This method add winner to winner.txt and loser to loser.txt and if match is
	 * draw then add both wiiner and loser to winner.txt
	 * 
	 * @param winner
	 * @param loser
	 * @param check
	 */
	public void addToFile(Team winner, Team loser, int drawCheck, String winnerPath, String loserPath) {

		try {

			if (drawCheck == 1) {
				File winnerFile = new File(winnerPath);
				winnerFile.createNewFile();
				FileWriter fileWriter = null;
				BufferedWriter bufferedWriter = null;
				fileWriter = new FileWriter(winnerFile, true);

				bufferedWriter = new BufferedWriter(fileWriter);
				synchronized (bufferedWriter) {

					bufferedWriter.write(winner.getTeamName() + " ");
					bufferedWriter.newLine();
				}

				bufferedWriter.close();
				fileWriter.close();

				File loserFile = new File(loserPath);
				loserFile.createNewFile();
				fileWriter = new FileWriter(loserFile, true);
				bufferedWriter = new BufferedWriter(fileWriter);
				synchronized (bufferedWriter) {

					bufferedWriter.write(loser.getTeamName() + " ");
					bufferedWriter.newLine();
				}

				bufferedWriter.close();
				fileWriter.close();

			} else {
				File winnerFile = new File(winnerPath);
				winnerFile.createNewFile();
				FileWriter fileWriter1 = null;
				BufferedWriter bufferedWriter = null;
				fileWriter1 = new FileWriter(winnerFile, true);

				bufferedWriter = new BufferedWriter(fileWriter1);
				synchronized (bufferedWriter) {

					bufferedWriter.write(winner.getTeamName() + " ");
					bufferedWriter.newLine();
					bufferedWriter.write(loser.getTeamName() + " ");
					bufferedWriter.newLine();
				}

				bufferedWriter.close();

				fileWriter1.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
}
