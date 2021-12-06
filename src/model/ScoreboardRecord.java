package model;

public class ScoreboardRecord   {
	String nickname;
	int score;
	String date;
	public ScoreboardRecord(String nickname, int score, String date) {
		super();
		this.nickname = nickname;
		this.score = score;
		this.date = date;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ScoreboardRecord [nickname=" + nickname + ", score=" + score + ", date=" + date + "]";
	}


}
