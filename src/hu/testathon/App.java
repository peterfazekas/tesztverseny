package hu.testathon;

import hu.testathon.controller.TestFacade;
import hu.testathon.model.service.DataReader;

public class App {

	private final TestFacade test;
	private final DataReader data;
	
	public App() {
		data = new DataReader();
		test = new TestFacade(data.getResults("valaszok.txt"));
	}

	public static void main(String[] args) {
		new App().run();
	}

	private void run() {
		System.out.println("1. feladat: Az adatok beolvasása\n");
		System.out.println("2. feladat: A vetélkedőn " + test.getCount() + " versenyző indult.\n");
		String id = data.readId("3. feladat: A versenyző azonosítója: ");
		System.out.println(test.getResult(id));
		System.out.println("\n4. feladat:\n" + test.getCorrectResult(id));
		int taskNumber = data.readTaskNumber("\n5. feladat: A feladat sorszáma: ");
		System.out.println(test.getCorrectTaskDetail(taskNumber));
		System.out.println("6. feladat: " + test.printResults("pontok.txt"));
		System.out.println(test.printFinalResult(3));
	}

}
