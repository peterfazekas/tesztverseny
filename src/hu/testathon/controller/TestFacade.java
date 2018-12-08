package hu.testathon.controller;

import java.util.List;
import java.util.stream.Collectors;

import hu.testathon.model.domain.Result;
import hu.testathon.model.service.DataWriter;

public class TestFacade {

	private final List<Result> results;

	public TestFacade(List<Result> results) {
		this.results = results;
	}
	
	public int getCount() {
		return results.size();
	}
	
	public String getResult(String id) {
		return String.format("%s    (a versenyző válasza)", getResultById(id).getAnswer());
	}
	
	public String getCorrectResult(String id) {
		return getResultById(id).getCorrectResult();
	}
	
	public String getCorrectTaskDetail(int taskNumber) {
		long count = countCorrectTask(taskNumber);
		double percent = 100.0 * count / getCount();
		return String.format("A feladatra %d fő, a versenyzők %5.2f%%-a adott helyes megoldást.", count, percent);
	}
	
	public String printResults(String fileName) {
		DataWriter writer = new DataWriter(fileName);
		writer.printAll(results);
		return "A versenyzők pontszámának meghatározása\n";
	}
	
	public String printFinalResult(int place) {
		List<Result> winners = getFinalResult(place);
		int actPlace = 0, oldScore = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < winners.size(); i++) {
			Result winner = winners.get(i);
			actPlace = winner.getScore() == oldScore ? actPlace : actPlace + 1;
			sb.append(String.format("%d. díj (%d pont): %s%n", actPlace, winner.getScore(), winner.getId()));
			oldScore = winner.getScore();
		}
		return sb.toString();
	}
	
	public List<Result> getFinalResult(int place) {
		return sortListByScoreDesc().stream()
				.filter(i -> i.getScore() >= getScoreLimit(place - 1))
				.collect(Collectors.toList());
	}
	
	private Result getResultById(String id) {
		return results.stream().filter(i -> i.getId().equals(id)).findAny().get();
	}
	
	private long countCorrectTask(int taskNumber) {
		return results.stream().filter(i -> i.isCorrectTask(taskNumber)).count();
	}

	private int getScoreLimit(int place) {
		List<Integer> scores = sortListByScoreDesc().stream()
				.map(Result::getScore)
				.distinct()
				.collect(Collectors.toList());
		return scores.get(place);
	}
	
	private List<Result> sortListByScoreDesc() {
		return results.stream()
				.sorted((j, i) -> i.getScore().compareTo(j.getScore()))
				.collect(Collectors.toList());
	}
}
