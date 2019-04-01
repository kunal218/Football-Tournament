package com.game.football.tournament.concurrentjunit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.game.football.team.Team;
import com.game.football.tournament.Tournament;

@RunWith(ConcurrentTestRunner.class)
public class TournamentTestDemo {

	Tournament tournament = new Tournament();
    private final static int THREAD_COUNT = 2;

	@Test
	@ThreadCount(THREAD_COUNT)
	public void testStartTheTournamentEvenTeams() throws InterruptedException {
		ArrayList<Team > arrayList = new ArrayList<Team>();
		for(int i=0;i<4;i++) {
		Team  team = new Team();
		team.setTeamName("Team"+i);
		arrayList.add(team);
		}
		
		tournament.setParticipantList(arrayList);
		int  teams = tournament.startTheTournamentEvenTeams(4);
		
		
		
		assertEquals(2, teams);
	
	}
	
}
