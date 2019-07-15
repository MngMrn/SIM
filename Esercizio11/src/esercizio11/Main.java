package esercizio11;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		Sim sim = new Sim("3466054108", 10);
		sim.uploadFileCalls();
		sim.startCall("3896888929");
		TimeUnit.SECONDS.sleep(5);
		sim.endCall();
		System.out.println(sim.allCallsForNumber("3896888929"));
		sim.printInfo();
	}
}
