package net.thumbtack.airline.model;

public class Plane {
	private PlaneInfo planeInfo;

	public Plane(PlaneInfo planeInfo) {
		this.planeInfo = planeInfo;
	}

	public PlaneInfo getPlaneInfo() {
		return planeInfo;
	}

	public void setPlaneInfo(PlaneInfo planeInfo) {
		this.planeInfo = planeInfo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Plane)) return false;

		Plane plane = (Plane) o;

		return getPlaneInfo().equals(plane.getPlaneInfo());
	}

	@Override
	public int hashCode() {
		return getPlaneInfo().hashCode();
	}

	@Override
	public String toString() {
		return "Plane{" +
				"planeInfo=" + planeInfo +
				'}';
	}
}
