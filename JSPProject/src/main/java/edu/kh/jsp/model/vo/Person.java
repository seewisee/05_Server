package edu.kh.jsp.model.vo;

public class Person {

	private String name;
	private int age;
	private String address;

	public Person() {} // 기본 생성자

	// getter/setter

	public String getName() { // 외부에서 전달받은 name을 현재 객체의 name에 할당
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address + "]";
	}

}
