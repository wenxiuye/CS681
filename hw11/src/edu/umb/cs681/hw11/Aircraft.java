package edu.umb.cs681.hw11;
import java.util.concurrent.locks.ReentrantLock;

public class Aircraft {
	private Position position;
	private ReentrantLock lock = new ReentrantLock();
	
	public Aircraft(Position pos) {	
		lock.lock();
		try {
			this.position = pos;
		}finally {
			lock.unlock();
		}
	
	}
	
	public void setPosition(double newLat, double newLong, double newAlt) {
		lock.lock();
		try {
			System.out.println("Changing position to "+ newLat + "," + newLong+ "," + newAlt);
			this.position = this.position.change(newLat, newLong, newAlt);
			System.out.println("Position changed. New position: "+ this.position.toString());
		}finally {
			lock.unlock();
		}
	}
	
	public Position getPosition() {
		lock.lock();
		try {
			return position;
		}finally {
			lock.unlock();
		}
	}
	

}
