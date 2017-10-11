package ca.ulaval.glo4002.carregistry.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ca.ulaval.glo4002.carregistry.domain.Car;
import ca.ulaval.glo4002.carregistry.domain.CarFactory;
import ca.ulaval.glo4002.carregistry.domain.CarOwner;
import ca.ulaval.glo4002.carregistry.domain.CarRegistry;
import ca.ulaval.glo4002.carregistry.persistence.InMemoryCarRegistry;
import ca.ulaval.glo4002.carregistry.services.dto.CarCreationRequest;
import ca.ulaval.glo4002.carregistry.services.dto.CarOwnerDto;

public class RegistryService {

	private CarRegistry carRegistry;
	private CarFactory carFactory;
	private CarOwnerAssember carOwnerAssember;
	private EntityManager em;
	private EntityManagerFactory emf;

	public RegistryService() {
		this.carRegistry = new InMemoryCarRegistry();
		this.carFactory = new CarFactory();
		this.carOwnerAssember = new CarOwnerAssember();
		this.emf = Persistence.createEntityManagerFactory("ProfessorService");
		this.em = emf.createEntityManager();
	}

	public Car addCar(int ownerId, CarCreationRequest request) {
		CarOwner owner = this.carRegistry.findOwner(ownerId);

		Car car = this.carFactory.createNewCar(request.carPlate);
		em.getTransaction().begin();
		owner.addCar(car);

		carRegistry.update(owner);
		em.getTransaction().commit();

		em.close();
		emf.close();
		return car;

	}

	public List<CarOwnerDto> getCarOwners() {
		em.getTransaction().begin();
		Collection<CarOwner> carOwners = this.carRegistry.findAllOwners();
		em.getTransaction().commit();
		em.close();
		emf.close();
		return carOwners.stream().map(carOwnerAssember::toDto).collect(Collectors.toList());
	}

}
