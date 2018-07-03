package net.thumbtack.airline.context.passengers;

import net.thumbtack.airline.dto.ServedPassengerDto;
import net.thumbtack.airline.model.Nationality;
import net.thumbtack.airline.model.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengersTestContext {
	private List<ServedPassengerDto> servedPassengerDtos;
	private List<Passenger> passengers = new ArrayList<>();
	private Passenger passenger1 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);
	private Passenger passenger2 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);
	private Passenger passenger3 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);
	private Passenger passenger4 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);
	private Passenger passenger5 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);


	public PassengersTestContext() {
		passengers.add(passenger1);
		passengers.add(passenger2);
		passengers.add(passenger3);
		passengers.add(passenger4);
		passengers.add(passenger5);
	}

	public void resetContext() {
		passengers = new ArrayList<>();
		passenger1 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);
		passenger2 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);
		passenger3 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);
		passenger4 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);
		passenger5 = new Passenger(0, "aaaa", "bbbb", "aaaa", "BUSINESS", new Nationality("ZW"), 0);
		passengers.add(passenger1);
		passengers.add(passenger2);
		passengers.add(passenger3);
		passengers.add(passenger4);
		passengers.add(passenger5);
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Passenger getPassenger1() {
		return passenger1;
	}

	public void setPassenger1(Passenger passenger1) {
		this.passenger1 = passenger1;
	}

	public Passenger getPassenger2() {
		return passenger2;
	}

	public void setPassenger2(Passenger passenger2) {
		this.passenger2 = passenger2;
	}

	public Passenger getPassenger3() {
		return passenger3;
	}

	public void setPassenger3(Passenger passenger3) {
		this.passenger3 = passenger3;
	}

	public Passenger getPassenger4() {
		return passenger4;
	}

	public void setPassenger4(Passenger passenger4) {
		this.passenger4 = passenger4;
	}

	public Passenger getPassenger5() {
		return passenger5;
	}

	public void setPassenger5(Passenger passenger5) {
		this.passenger5 = passenger5;
	}

	public List<ServedPassengerDto> getServedPassengerDtos() {
		return servedPassengerDtos;
	}

	public void setServedPassengerDtos(List<ServedPassengerDto> servedPassengerDtos) {
		this.servedPassengerDtos = servedPassengerDtos;
	}
}
