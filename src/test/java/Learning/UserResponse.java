package Learning;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
	
	private String name;
	private String job;
	private String id;
	private String createdAt;
	
//	@JsonProperty("name")
//	public String getName() {
//		return name;
//	}
//	@JsonProperty("name")
//	public void setName(String name) {
//		this.name = name;
//	}
//	@JsonProperty("job")
//	public String getJob() {
//		return job;
//	}
//	@JsonProperty("job")
//	public void setJob(String job) {
//		this.job = job;
//	}
//	@JsonProperty("id")
//	public String getId() {
//		return id;
//	}
//	@JsonProperty("id")
//	public void setId(String id) {
//		this.id = id;
//	}
//	@JsonProperty("createdAt")
//	public String getCreatedAt() {
//		return createdAt;
//	}
//	@JsonProperty("createdAt")
//	public void setCreatedAt(String createdAt) {
//		this.createdAt = createdAt;
//	}
	public UserResponse(String name, String job, String id, String createdAt) {
		super();
		this.name = name;
		this.job = job;
		this.id = id;
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "UserResponse [name=" + name + ", job=" + job + ", id=" + id + ", createdAt=" + createdAt + "]";
	}
	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
