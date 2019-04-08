package org.sang.bean;

public class Status {
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Status(int status) {
		super();
		this.status = status;
	}

	@Override
	public String toString() {
		return "Status [status=" + status + "]";
	}
	
}
