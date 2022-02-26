package com.example.assignmentjavabootcamp;

import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.product.ProductRepository;
import com.example.assignmentjavabootcamp.user.Address;
import com.example.assignmentjavabootcamp.user.AddressRepository;
import com.example.assignmentjavabootcamp.user.User;
import com.example.assignmentjavabootcamp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedHashSet;

@SpringBootApplication
public class AssignmentJavaBootCampApplication {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@PostConstruct
	public void initializeData() {
		User user = new User();
		user.setId(1);
		userRepository.save(user);

		Address address = new Address();
		address.setId(1);
		address.setUser(user);
		address.setName("John doe");
		address.setAddress("1/234 Mary road");
		address.setPostCode("5000");
		address.setSubProvince("Bang pra");
		address.setProvince("krug thep");
		addressRepository.save(address);


		Product product1 = new Product(2134, "addidas", 1324);
		product1.setDescription("this is product description");
		product1.setSize(new LinkedHashSet<>(Arrays.asList("S", "M", "L")));
		product1.setPictureUrl("https://cataas.com/cat/orange");

		Product product2 = new Product(2135, "not addidas", 234);
		Product product3 = new Product(2136, "another", 432);

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
	}

	public static void main(String[] args) {
		SpringApplication.run(AssignmentJavaBootCampApplication.class, args);
	}

}
