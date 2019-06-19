package moglieMarito.collection.classes;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;


public class Moglie implements Runnable {
	
	//la moglie agisce su una coda di elementi q che creo nel main
	private final BlockingQueue<String> queue;
	private ArrayList<String> listino;
	public Moglie(BlockingQueue<String> queue, ArrayList<String> listino) {this.queue = queue; this.listino=listino;}
	private volatile boolean flag = true;
	
	public void stopMoglie() {
		flag=false;
	}
	//int size = listino.size();
	//metodo con cui la moglie produce un oggetto in fondo alla coda
	//int i = 1;//controllo
	private void produce(){
		//prendo una riga a caso dalla collezione dei prodotti
		try {
			Random rnd = new Random();
			queue.put(listino.get(rnd.nextInt(listino.size())));
			//queue.put("prodotto " + i );
			//System.out.println("La moglie ha messo il prodotto: " + i+" Size :" +queue.size());//controllo
			//i++;//controllo
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public void run() {
		while(flag) {
		//produce un oggetto nella coda
		produce();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	}
}