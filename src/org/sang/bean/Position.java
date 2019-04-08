package org.sang.bean;

public class Position {
	private double longitude;//经度
	private double latitude;//维度
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Position(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "Position [longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}
	
}
