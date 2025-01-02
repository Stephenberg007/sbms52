package in.ashokit.binding;


public class Customer {
	private Integer id;
	private String name;
	private Long phNo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPhNo() {
		return phNo;
	}
	public void setPhNo(Long phNo) {
		this.phNo = phNo;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phNo=" + phNo + "]";
	}
	
	
}
