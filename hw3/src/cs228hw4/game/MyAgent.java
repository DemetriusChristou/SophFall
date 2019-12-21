package cs228hw4.game;

import java.awt.Color;
import java.io.File;
import java.util.Random;

import edu.iastate.cs228.game.Agent;
import edu.iastate.cs228.game.GalaxyState;
import edu.iastate.cs228.game.SystemState;

public class MyAgent implements Agent {

	/**
	 * holds onto the color my agent was given
	 */
	private Color myColor;

	private Color enemyColor;

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return "Demetrius";
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return "Christou";
	}

	@Override
	public String getStuID() {
		// TODO Auto-generated method stub
		return "946902985";
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return "ddc";
	}

	@Override
	public String getAgentName() {
		// TODO Auto-generated method stub
		return "Meach";
	}

	@Override
	public File getAgentImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean inTournament() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColor(Color c) {
		myColor = c;

	}

	@Override
	public void setOpponentColor(Color c) {
		enemyColor = c;

	}

	@Override
	public AgentAction[] getCommandTurnGrading(GalaxyState g, int energy) {
		AgentAction[] myTurn = new AgentAction[3];

		Capture c = new Capture(g.getCurrentSystemFor(myColor).getCostToCapture());
		myTurn[0] = c;
		myTurn[1] = new Refuel();
		Random r = new Random();
		SystemState[] s = g.getSystems();
		myTurn[2] = new Move(s[r.nextInt(s.length)].getName());
		return myTurn;
	}

	@Override
	public AgentAction[] getCommandTurnTournament(GalaxyState arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
