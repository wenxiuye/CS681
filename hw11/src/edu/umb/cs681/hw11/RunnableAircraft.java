package edu.umb.cs681.hw11;

public class RunnableAircraft extends Aircraft implements Runnable{

	public RunnableAircraft(Position pos) {
		super(pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Current Position: " + getPosition().toString());
		this.setPosition(2 ,3, 4);
		System.out.println("Current Position: " + getPosition().toString());
		this.setPosition(4 ,7, 4);
		System.out.println("Current Position: " + getPosition().toString());
		this.setPosition(8 ,3, 4);
		System.out.println("Current Position: " + getPosition().toString());
		
	}

}
