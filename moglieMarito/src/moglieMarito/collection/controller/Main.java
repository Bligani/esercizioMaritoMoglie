package moglieMarito.collection.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import moglieMarito.collection.classes.Marito;
import moglieMarito.collection.classes.Moglie;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<String> listino = new ArrayList<String>();
		
		Path r = Paths.get("Products.txt");
		Collection<String> linee;
		try {
			linee = Files.lines(r).collect(Collectors.toList());
			for(String l : linee) {
				String[] parole= l.split(";");
				listino.add(parole[0].toLowerCase());
		}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		//definisco la coda e la assegno a moglie e marito
		final BlockingQueue<String> scaffale = new ArrayBlockingQueue<String>(10);
		
		Moglie moglie = new Moglie(scaffale, listino);
		Marito marito = new Marito(scaffale);
		
		Thread t1 = new Thread(moglie);
		Thread t2 = new Thread(marito);
		
		Thread t3;
		t3= new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 moglie.stopMoglie();
		    	 marito.stopMarito();
		     }
				
		   });
		
		t1.start();
		t2.start();
		
		try {
			t2.join(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t3.start();
	}
}

/*Thread t3= new Thread(new Runnable() {
@Override
public void run() {
  
	try {
		TimeUnit.SECONDS.sleep(5);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	t1.interrupt();
   t2.interrupt();
}
});
t3.start();*/