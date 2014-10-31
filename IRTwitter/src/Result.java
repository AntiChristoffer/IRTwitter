public class Result implements Comparable<Result>{
	String message;
	int weight;
	public Result(String msg, int w){
		message = msg;
		weight = w;
	}

	public String getMessage(){
		return message;
	}

	public int getWeight(){
		return weight;
	}

	public int compareTo(Result other){
		if(weight > other.getWeight()) return -1;
		else if(weight < other.getWeight()) return 1;
		else return 0;
	}
}
