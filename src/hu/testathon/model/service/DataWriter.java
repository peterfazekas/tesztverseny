package hu.testathon.model.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import hu.testathon.model.domain.Result;

public class DataWriter {

	private final String fileName;

	public DataWriter(String fileName) {
		this.fileName = fileName;
	}

	public void printAll(List<Result> results) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
			results.forEach(pw::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
