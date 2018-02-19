import java.util.HashMap;
import java.util.Map;

public class Hunt_user {

	String userID;
	Map<Integer, String> answers;
	
	public Hunt_user(String userID) {
		this.userID = userID;
		answers = new HashMap<Integer, String>();
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Map<Integer, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<Integer, String> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Hunt_user [userID=" + userID + ", answers=" + answers + "]";
	}

}
