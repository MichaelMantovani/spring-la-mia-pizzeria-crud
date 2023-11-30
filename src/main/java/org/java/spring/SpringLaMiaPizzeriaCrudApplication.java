package org.java.spring;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private PizzaService pizzaService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		pizzaService.save(new Pizza("Margherita", "Pomodoro,mozzarella","https://images.prismic.io/eataly-us/ed3fcec7-7994-426d-a5e4-a24be5a95afd_pizza-recipe-main.jpg?auto=compress,format", 5));
		pizzaService.save(new Pizza("4 stagioni", "Pomodoro,mozzarella,funghi, prosciutto cotto, olive nere,carciofi", "https://i0.wp.com/www.piccolericette.net/piccolericette/wp-content/uploads/2016/07/3017_Pizza.jpg?resize=895%2C616&ssl=1", 10));
		pizzaService.save(new Pizza("Crudo", "Mozzarella,pomoro,prosciutto crudo","https://images-tastehub.mdlzapps.cloud/images/b876fafd-cee1-440e-81e7-283ddc6c7cc6.jpg?fm=webp&q=80" , 10));
	}

}
