package hu.testathon.model.domain;

public class Solution {
	
	private static final int[] SCORES = {3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 6};
	
	private final String result;

	public Solution(String result) {
		this.result = result;
	}

	public String getResult(String answer) {
		return String.format("%s    (a helyes megoldás)%n%s    (a versenyző helyes válaszai)", 
				result, getCorrectResults(answer));
	}
	
	
	public int getScrore(String answer) {
		int score = 0;
		for (int i = 0; i < result.length(); i++) {
			if (isCorrectResult(i, answer)) {
				score += SCORES[i];
			}
		}
		return score;
	}
	
	public boolean isCorrectResult(int index, String answer) {
		return result.charAt(index) == answer.charAt(index);
	}
	
	private String getCorrectResults(String answer) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length(); i++) {
			sb.append(isCorrectResult(i, answer) ? "+" : " ");
		}
		return sb.toString();
	}

}
