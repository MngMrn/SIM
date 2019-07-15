package esercizio11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Sim {
	private String number = new String();
	private double balance;
	private File file = new File("C:\\Users\\wolfh\\Desktop\\Esercizi\\Esercizio11\\src\\file");
	private Call call = new Call();
	private ArrayList<Call> calls = new ArrayList <>();
	
	public Sim(String number,double balance) {
		this.number=number;
		this.balance=balance;
	}
	
	/**
	 * Il metodo startCall inizia la chiamata
	 * @param number
	 * @return
	 */
	public String startCall(String number) {
		call.setNumber(number);
		call.setStartTime(System.currentTimeMillis());
		return number;
	}
	
	/**
	 * Il metodo endCall termina la chiamata
	 * @throws IOException
	 */
	public void endCall() throws IOException {
		call.setEndTime(System.currentTimeMillis());
		call.setTime(call.getEndTime()-call.getStartTime());
		call.setTime(call.getTime()/1000);
		calls.add(call);
		PrintWriter writer = new PrintWriter(new FileWriter(file.getPath(),true));
		writer.print(call.getTime()+";"+call.getStartTime()+";"+call.getEndTime()+";"+call.getNumber()+"\n");
		writer.close();
	}
	
	/**
	 * Il metodo allCallsForNumber restituisce il numero di tutte le chiamate effettutate
	 * @param number
	 * @return
	 */
	public int allCallsForNumber(String number) {
		int allCalls = 0;
		for(Call call : calls) {
			if(call.getNumber().equals(number)) {
				allCalls++;
			}
		}
		return allCalls;
	}
	
	/**
	 * Il metodo print info stampa in output il numero della sim il saldo e tutte le chiamate effettuate
	 */
	public void printInfo() {
		System.out.println("Number = "+this.number);
		System.out.println("Balance = "+this.balance);
		int counter = 0;
		for(Call call : calls) {
			counter++;
			System.out.println("Call = "+counter+"/Number = "+call.getNumber()+"/Time = "+call.getTime());
		}
	}
	
	/**
	 * Carica tutte le chiamate presenti sul file nel programma
	 * @throws IOException
	 */
	public void uploadFileCalls() throws IOException {
		String line = null;
		BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
		line=reader.readLine();
		while(line != null) {
			String[] pieces = line.split(";");
			long time = Long.parseLong(pieces[0]);
			long startTime = Long.parseLong(pieces[1]);
			long endTime = Long.parseLong(pieces[2]);
			call.setTime(time);
			call.setStartTime(startTime);
			call.setEndTime(endTime);
			call.setNumber(pieces[3]);
			calls.add(call);
			line=reader.readLine();
		}
		reader.close();
	}
}
