package com.example.tictactoe;

public class Score implements Comparable<Score>{
	private String scorer;
	public int scoreNum;
	 
	public Score(String name, int num){
	  //  scoreDate=date;
	    scorer=name;
	    scoreNum=num;
	}
	
	@Override
	public int compareTo(Score sc) {
		// TODO Auto-generated method stub
		return sc.scoreNum>scoreNum? 1 : sc.scoreNum<scoreNum? -1 : 0;
	}
	public String getScoreText()
	{
	    return scorer+"-"+scoreNum;
	}
}
