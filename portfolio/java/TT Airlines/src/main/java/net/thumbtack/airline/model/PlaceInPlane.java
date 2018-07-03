package net.thumbtack.airline.model;

public class PlaceInPlane {
	private int id;
	private int placeNumber;
	private int rowNumber;
	private String stringRepresentation;

	public PlaceInPlane() {
	}

	public PlaceInPlane(int id, int placeNumber, int rowNumber, String stringRepresentation) {
		this.id = id;
		this.placeNumber = placeNumber;
		this.rowNumber = rowNumber;
		this.stringRepresentation = stringRepresentation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlaceNumber() {
		return placeNumber;
	}

	public void setPlaceNumber(int placeNumber) {
		this.placeNumber = placeNumber;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getStringRepresentation() {
		return stringRepresentation;
	}

	public void setStringRepresentation(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlaceInPlane)) return false;

		PlaceInPlane that = (PlaceInPlane) o;

		if (getId() != that.getId()) return false;
		if (getPlaceNumber() != that.getPlaceNumber()) return false;
		if (getRowNumber() != that.getRowNumber()) return false;
		return getStringRepresentation().equals(that.getStringRepresentation());
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + getPlaceNumber();
		result = 31 * result + getRowNumber();
		result = 31 * result + getStringRepresentation().hashCode();
		return result;
	}
}
