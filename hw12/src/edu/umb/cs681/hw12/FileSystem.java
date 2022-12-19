package edu.umb.cs681.hw12;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
	
	private static FileSystem fileSystem = null;
	private static LinkedList <Directory> rootDirs = null;
	private static ReentrantLock slock = new ReentrantLock();
	
	private FileSystem() {
		
	}
	
	public static FileSystem getFileSystem() {
		slock.lock();
        try{
        	if (fileSystem == null) {
    			fileSystem = new FileSystem();
    			rootDirs = new LinkedList<Directory>();
    		}
    		return fileSystem;	
        } finally{
            slock.unlock();
        }
		
	}
	
	public LinkedList<Directory> getRootDirs(){
		slock.lock();
        try{
        	return rootDirs;
        } finally{
            slock.unlock();
        }
		
	}
	
	public void appendRootDir(Directory root) {
		slock.lock();
        try{
        	rootDirs.add(root);
        } finally{
            slock.unlock();
        }
		
	}
}
