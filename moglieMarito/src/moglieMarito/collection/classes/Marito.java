package moglieMarito.collection.classes;

import java.util.concurrent.BlockingQueue;

public class Marito implements Runnable{
	
	private final BlockingQueue<String> queue;
	public Marito(BlockingQueue<String> queue) {this.queue = queue;}
	private volatile boolean flag = true;
	
	public void stopMarito() {
		flag=false;
	}
	
	/*
	public void consume(BlockingQueue<String> coda) {
		//togli il primo elemento della coda e stampalo in un output 
		try {
			System.out.println("Il marito ha preso :" +coda.take()+" size :" +coda.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} */
	
	public void consume() {
		
try {
			//System.out.println("Size :" +queue.size());
			System.out.println("Il marito ha preso:" +queue.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
	public void run() {
		
		while(flag) {
		//consuma un oggetto dalla coda
		consume();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
