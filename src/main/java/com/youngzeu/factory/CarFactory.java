package com.youngzeu.factory;


import org.junit.jupiter.api.Test;

public class CarFactory {
	
	Car getCar(String name) {
		Car car;
		switch (name) {
		case "BMW":
			car = new BMW();
			break;
		case "Audi":
			car = new Audi();
			break;
		default:
			car = new DefaultCar();
			break;
		}
		return car;
	}
	
	@Test
	public void factoryTest() {
		CarFactory factory = new CarFactory();
		Car bmw = factory.getCar("BMW");
		Car audi = factory.getCar("Audi");
		System.out.println("bmw : " + bmw);
		System.out.println("audi : " + audi);
	}
}
