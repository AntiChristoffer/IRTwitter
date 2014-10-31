public class Result{
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
}