package hu.testathon.model.domain;

public class Result {
	
	private final String id;
	private final String answer;
	private final Integer score;
	private final Solution solution;

	public Result(String id, String answer, Solution solution) {
		this.id = id;
		this.answer = answer;
		this.solution = solution;
		score = solution.getScrore(answer);
	}

	public String getId() {
		return id;
	}

	public String getAnswer() {
		return answer;
	}

	public Integer getScore() {
		return score;
	}
	
	public String getCorrectResult() {
		return solution.getResult(answer);
	}
	
	public boolean isCorrectTask(int taskNumber) {
		return solution.isCorrectResult(taskNumber - 1, answer);
	}

	@Override
	public String toString() {
		return id + " " + score;
	}
	
	
}
