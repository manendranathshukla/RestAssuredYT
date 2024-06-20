package SerializationAndDeserialization;

public class User {
	private String name;
	private String job;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", job=" + job + "]";
	}
	public User(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}
	
	
	

}
