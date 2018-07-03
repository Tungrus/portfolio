package net.thumbtack.airline.context.flights;

import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.PlaneInfo;

public class PlaneTestContext {
	private Plane plane1 = new Plane(new PlaneInfo(0, "plane1", 10, 10, 10, 10));
	private Plane plane2 = new Plane(new PlaneInfo(0, "plane2", 20, 20, 20, 20));
	private Plane plane3 = new Plane(new PlaneInfo(0, "plane3", 30, 30, 30, 30));
	private Plane plane4 = new Plane(new PlaneInfo(0, "plane4", 40, 40, 40, 40));
	private Plane plane5 = new Plane(new PlaneInfo(0, "plane5", 50, 50, 50, 50));


	public PlaneTestContext() {
	}

	public Plane getPlane1() {
		return plane1;
	}

	public void setPlane1(Plane plane1) {
		this.plane1 = plane1;
	}

	public Plane getPlane2() {
		return plane2;
	}

	public void setPlane2(Plane plane2) {
		this.plane2 = plane2;
	}

	public Plane getPlane3() {
		return plane3;
	}

	public void setPlane3(Plane plane3) {
		this.plane3 = plane3;
	}

	public Plane getPlane4() {
		return plane4;
	}

	public void setPlane4(Plane plane4) {
		this.plane4 = plane4;
	}

	public Plane getPlane5() {
		return plane5;
	}

	public void setPlane5(Plane plane5) {
		this.plane5 = plane5;
	}

	public void resetContext() {
		plane1 = new Plane(new PlaneInfo(0, "plane1", 10, 10, 10, 10));
		plane2 = new Plane(new PlaneInfo(0, "plane2", 20, 20, 20, 20));
		plane3 = new Plane(new PlaneInfo(0, "plane3", 30, 30, 30, 30));
		plane4 = new Plane(new PlaneInfo(0, "plane4", 40, 40, 40, 40));
		plane5 = new Plane(new PlaneInfo(0, "plane5", 50, 50, 50, 50));
	}
}
