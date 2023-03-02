package com.example.demo;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Configuration.AppConstansts;
import com.example.demo.Model.Role;
import com.example.demo.Repository.Rolerepo;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{


	@Autowired
	private Rolerepo rolerepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try{
			Role role1=new Role();
			role1.setId(AppConstansts.ADMIN_USER);
			role1.setName("ADMIN_USER");
			Role role2=new Role();
			role2.setId(AppConstansts.NORMAL_USER);
			role2.setName("NORMAL_USER");

			List<Role> roles=List.of(role1,role2);

			List<Role> result=this.rolerepo.saveAll(roles);

		
		}
		catch(Exception e){
e.printStackTrace();
		}
		System.out.println(this.passwordEncoder.encode("aaaa"));
	}
}
