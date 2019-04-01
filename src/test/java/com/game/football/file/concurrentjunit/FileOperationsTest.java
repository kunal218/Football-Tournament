package com.game.football.file.concurrentjunit;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.game.football.file.FileOperations;
import com.game.football.team.Team;

@RunWith(ConcurrentTestRunner.class)
public class FileOperationsTest {
	FileOperations 	fileOperations=new FileOperations();
    private final static int THREAD_COUNT = 2;
    private static int lines=0;
	@Test
	@ThreadCount(THREAD_COUNT)
	public void testAddToDraw() {
		Team team1=new Team();
		team1.setTeamName("Manchester United");
		Team team2=new Team();
		team2.setTeamName("Chelsea");
	
		fileOperations.addToDraw(team1, team2, "DrawTest.txt");
		int numberOfLines=getLine("DrawTest.txt");
		
	//	String expected="Manchester United" + " Draws with " + "Chelsea";
		
		assertEquals(2, numberOfLines);
				
	}
	@Test
	@ThreadCount(THREAD_COUNT)
	public void testAddToFile() {
		Team team1=new Team();
		team1.setTeamName("PSG");
		Team team2=new Team();
		team2.setTeamName("Real Madrid");
		
		fileOperations.addToFile(team1, team2, 1, "WinnerTest.txt", "LoserTest.txt");
		
	}
	public int getLine(String path) {
		
		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			fileReader = new FileReader(file);
			bufferedReader=new BufferedReader(fileReader);
			
			while(bufferedReader.readLine()!=null) {
				 bufferedReader.readLine();
					lines++;
					}
			return lines ;
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
		System.out.println(ex.getMessage());
		}
		finally {
			try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
			
		}
			return 0;

	}


}


