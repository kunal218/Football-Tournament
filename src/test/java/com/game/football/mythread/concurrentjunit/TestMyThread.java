package com.game.football.mythread.concurrentjunit;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.game.football.team.Team;
import com.game.football.thread.MyThread;

@RunWith(ConcurrentTestRunner.class)
public class TestMyThread {

	private MyThread myThread = new MyThread();
    private final static int THREAD_COUNT = 2;

    @Test
    @ThreadCount(THREAD_COUNT)
    public void testExecute() {
    	Team homeTeam =new Team();
    	homeTeam.setTeamName("Barcelona");
    	Team awayTeam = new Team();
    	awayTeam.setTeamName("Levante");
    	
    	ArrayList<Team > arrayList = myThread.execute(homeTeam, awayTeam);
    	
    	for(Team team : arrayList) {
    		System.out.println("Winner "+team+" Goals :"+team.getScore());
    	}
    	
    	
    }
}
