package com.game.football.tournament;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;


import com.game.football.team.Team;

public class TestTournament {

		
		private Tournament tournament = new Tournament();
		
	
	
	@Test
	public void testStartTheTournamentEvenTeams() {
		
		ArrayList<Team > arrayList = new ArrayList<Team>();
		for(int i=0;i<4;i++) {
		Team  team = new Team();
		team.setTeamName("Team"+i);
		arrayList.add(team);
		}
		
		tournament.setParticipantList(arrayList);
		
		int teams = tournament.startTheTournamentEvenTeams(4);
		assertEquals(2, teams);
	
	}
	@Test
	public void testStartTheTournamentEvenTeamsWithDraw() {
		ArrayList<Team > arrayList = new ArrayList<Team>();
		for(int i=0;i<4;i++) {
		Team  team = new Team();
		team.setTeamName("Team"+i);
		arrayList.add(team);
		}
		
		tournament.setParticipantList(arrayList);
		
		int teams = tournament.startTheTournamentEvenTeams(4);
		
		
		assertEquals(3, teams);
		
	}
	@Test
	public void testStartTheTournamentOddTeams() {
		ArrayList<Team > arrayList = new ArrayList<Team>();
		for(int i=0;i<5;i++) {
		Team  team = new Team();
		team.setTeamName("Team"+i);
		arrayList.add(team);
		}
		
		tournament.setParticipantList(arrayList);
		
		int teams = tournament.startTheTournamentOddTeams(5);
		assertEquals(3, teams);
	
	}
	@Test
	public void testStartTheTournamentOddTeamsWithDraw() {
		ArrayList<Team > arrayList = new ArrayList<Team>();
		for(int i=0;i<5;i++) {
		Team  team = new Team();
		team.setTeamName("Team"+i);
		arrayList.add(team);
		}
		
		tournament.setParticipantList(arrayList);
		
		int teams = tournament.startTheTournamentOddTeams(5);
		assertEquals(4, teams);
	
	}
	

}
