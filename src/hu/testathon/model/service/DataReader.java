package hu.testathon.model.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import hu.testathon.model.domain.Result;
import hu.testathon.model.domain.Solution;

public class DataReader {

	private final Scanner scanner = new Scanner(System.in);
	
	public List<Result> getResults(String fileName) {
		return dataParser(fileName);
	}
	
	public String readId(String text) {
		System.out.print(text);
		return scanner.next();
	}
	
	public int readTaskNumber(String text) {
		System.out.print(text);
		return scanner.nextInt() - 1;
	}

	private List<Result> dataParser(String fileName) {
		List<String> list = read(fileName);
		Solution solution = new Solution(list.remove(0));
		return list.stream().map(line -> createResult(line, solution)).collect(Collectors.toList());
	}
	
	private Result createResult(String line, Solution solution) {
		String[] items = line.split(" ");
		return new Result(items[0], items[1], solution);
	}
	
	private List<String> read(String fileName) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
		
}
