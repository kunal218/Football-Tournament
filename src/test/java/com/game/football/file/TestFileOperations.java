package com.game.football.file;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.game.football.team.Team;

public class TestFileOperations {

	FileOperations fileOperations = new FileOperations();
	private String actualOutput = null;
	private String winnerActual = null;
	private String loserActual = null;

	public String getLine(String path) {
		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			actualOutput = bufferedReader.readLine();
			return actualOutput;
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				bufferedReader.close();
				fileReader.close();

			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}

		}

		return "";

	}

	@Test
	public void testAddToDraw() {

		Team team1 = new Team();
		team1.setTeamName("Manchester United");
		Team team2 = new Team();
		team2.setTeamName("Chelsea");

		fileOperations.addToDraw(team1, team2, "DrawTest.txt");
		actualOutput = getLine("DrawTest.txt");

		String expected = "Manchester United" + " Draws with " + "Chelsea";
		assertEquals(expected, actualOutput);

	}

	@Test
	public void testAddToDrawNegative() {

		Team team1 = new Team();
		team1.setTeamName("Manchester United");
		Team team2 = new Team();
		team2.setTeamName("Chelsea");

		fileOperations.addToDraw(team1, team2, "DrawTest.txt");
		actualOutput = getLine("DrawTest.txt");

		String expected = "Manchester United" + " Draws with " + "Chelsea";
		assertEquals(expected, actualOutput);

	}

	@Test
	public void testAddToFile() {
		Team team1 = new Team();
		team1.setTeamName("PSG");
		Team team2 = new Team();
		team2.setTeamName("Real Madrid");

		fileOperations.addToFile(team1, team2, 1, "WinnerTest.txt", "LoserTest.txt");
		winnerActual = getLine("WinnerTest.txt");
		loserActual = getLine("LoserTest.txt");

		assertEquals("PSG ", winnerActual);
		assertEquals("Real Madrid ", loserActual);

	}

	@Test
	public void testAddRoundNumberToFile() {

		int roundNumber = 1;

		fileOperations.addRoundNumberToFile(roundNumber, "RoundTest.txt", "RoundTest.txt", "RoundTest.txt");

	}

}
