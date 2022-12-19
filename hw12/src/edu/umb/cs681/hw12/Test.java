package edu.umb.cs681.hw12;

import java.time.LocalDateTime;

public class Test {
	
	public static void main(String[] args) {
		
		
		LocalDateTime time = LocalDateTime.now();
		FileSystem fs = FileSystem.getFileSystem();
		Directory root = new Directory (null, "root", 0, time);
		Directory apps = new Directory (root, "apps", 0, time);
		Directory bin = new Directory (root, "bin", 0, time);
		Directory home = new Directory (root, "home", 0, time);
		Directory pictures = new Directory (home, "pictures", 0, time);
		
		File x = new File(apps, "x.java", 20, time);
		File y = new File(bin, "y.java", 30, time);
		Link z = new Link(home, "z.java", 100, time, bin);	
		Link c = new Link(pictures, "c.java", 100, time, y);
		File a = new File(pictures, "a.java", 200, time);
		File b = new File(pictures, "b.java", 100, time);
		
		fs.appendRootDir(root);
		root.appendChild(apps);
		root.appendChild(bin);
		root.appendChild(home);
		apps.appendChild(x);
		bin.appendChild(y);
		home.appendChild(z);
		home.appendChild(pictures);
		home.appendChild(c);
		pictures.appendChild(c);
		pictures.appendChild(a);
		pictures.appendChild(b);
		
		for(int i = 0; i <= 15; i++){
			FileSystemRunnable dataHandler = new FileSystemRunnable(fs);
			Thread thread = new Thread(dataHandler); 
			thread.start();

			//2-Step Thread Termination
			dataHandler.setDone();
			thread.interrupt(); 
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	
	}

}

