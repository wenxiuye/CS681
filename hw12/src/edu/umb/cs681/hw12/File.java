package edu.umb.cs681.hw12;

import java.time.LocalDateTime;

public class File extends FSElement{

	public File(Directory parent, String name, int size, LocalDateTime creationTime) {
		super(parent, name, size, creationTime);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isDiretory() {
		return false;
	}
	
	@Override
	public boolean isLink() {
		return false;
	}
}