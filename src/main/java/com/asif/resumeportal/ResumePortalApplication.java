package com.asif.resumeportal;

import com.asif.resumeportal.model.Education;
import com.asif.resumeportal.model.Job;
import com.asif.resumeportal.model.UserProfile;
import com.asif.resumeportal.model.Users;
import com.asif.resumeportal.repository.UserProfileRepository;
import com.asif.resumeportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ResumePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumePortalApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args -> {
			Users user1 = new Users();
			user1.setId(1);
			user1.setUserName("alKhwarizmi");
			user1.setPassword("alKhwarizmi");
			user1.setActive(true);
			user1.setRoles("USER");
			Users user2 = new Users();
			user2.setId(2);
			user2.setUserName("sina");
			user2.setPassword("sina");
			user2.setActive(true);
			user2.setRoles("USER");
			userRepository.save(user1);
			userRepository.save(user2);
		};
	}

	@Bean
	CommandLineRunner init1(UserProfileRepository userProfileRepository){
		return args -> {
			UserProfile userProfile1 = new UserProfile();
			userProfile1.setId(1);
			userProfile1.setUserName("alKhwarizmi");
			userProfile1.setSummary("produced vastly influential works in mathematics, astronomy, and geography.");
			userProfile1.setTheme(1);
			userProfile1.setFirstName("Musa");
			userProfile1.setLastName("al-Khwarizmi");
			userProfile1.setEmail("musa@gmail.com");
			userProfile1.setPhone("123-123-123");
			userProfile1.setDesignation("The father of Algebra and Master of Algorithms");

			// job section
			Job job1 = new Job(); // job 1
			job1.setId(1);
			job1.setCompany("company 1");
			job1.setDesignation("designation 1");
			job1.setStartDate(LocalDate.of(2020, 1, 1));
			job1.setCurrentJob(true);
			job1.getResponsibilities().add("produced vastly influential works in mathematics");
			job1.getResponsibilities().add("produced vastly influential works in astronomy");
			job1.getResponsibilities().add("produced vastly influential works in geography");
			Job job2 = new Job(); // job 2
			job2.setId(2);
			job2.setCompany("company 2");
			job2.setDesignation("designation 2");
			job2.setStartDate(LocalDate.of(2022, 1, 1));
			job2.setEndDate(LocalDate.of(2023, 1, 1));
			job2.getResponsibilities().add("produced vastly influential works in mathematics");
			job2.getResponsibilities().add("produced vastly influential works in astronomy");
			job2.getResponsibilities().add("produced vastly influential works in geography");

			userProfile1.getJobs().clear();
			userProfile1.getJobs().add(job1);
			userProfile1.getJobs().add(job2);

			// education section
			Education e1 = new Education(); // education 1
			e1.setCollege("Awesome College 1");
			e1.setQualification("Great Degree");
			e1.setCurrentStatus(false);
			e1.setSummary("Studied a lot");
			e1.setStartDate(LocalDate.of(2022, 1, 1));
			e1.setEndDate(LocalDate.of(2022, 1, 1));
			Education e2 = new Education(); // education 2
			e2.setCollege("Awesome College 2");
			e2.setQualification("Great Degree");
			e2.setCurrentStatus(false);
			e2.setSummary("Studied a lot");
			e2.setStartDate(LocalDate.of(2022, 1, 1));
			e2.setEndDate(LocalDate.of(2022, 1, 1));

			userProfile1.getEducations().clear();
			userProfile1.getEducations().add(e1);
			userProfile1.getEducations().add(e2);

			// skills
			userProfile1.getSkills().clear();
			userProfile1.getSkills().add("mathematics");
			userProfile1.getSkills().add("astronomy");
			userProfile1.getSkills().add("geography");

			userProfileRepository.save(userProfile1);

			//second user info
			UserProfile userProfile2 = new UserProfile();
			userProfile2.setId(2);
			userProfile2.setUserName("sina");
			userProfile2.setSummary("Regarded as one of the most significant physicians, astronomers, philosophers, writer, and the father of early modern medicine.");
			userProfile2.setTheme(2);
			userProfile2.setFirstName("Ibn");
			userProfile2.setLastName("Sina");
			userProfile2.setEmail("sina@gmail.com");
			userProfile2.setPhone("456-456-456");
			userProfile2.setDesignation("Father of early modern Medicine");

			// job section
			Job job3 = new Job(); // job 1
			job3.setId(3);
			job3.setCompany("company 1");
			job3.setDesignation("designation 1");
			job3.setStartDate(LocalDate.of(2020, 1, 1));
			job3.setCurrentJob(true);
			job3.getResponsibilities().add("produced vastly influential works in physics");
			job3.getResponsibilities().add("produced vastly influential works in astronomy");
			job3.getResponsibilities().add("produced vastly influential works in medicine");
			Job job4 = new Job(); // job 2
			job4.setId(4);
			job4.setCompany("company 2");
			job4.setDesignation("designation 2");
			job4.setStartDate(LocalDate.of(2022, 1, 1));
			job4.setEndDate(LocalDate.of(2023, 1, 1));
			job4.getResponsibilities().add("produced vastly influential works in physics");
			job4.getResponsibilities().add("produced vastly influential works in astronomy");
			job4.getResponsibilities().add("produced vastly influential works in medicine");

			userProfile2.getJobs().clear();
			userProfile2.getJobs().add(job3);
			userProfile2.getJobs().add(job4);

			// education section
			userProfile2.getEducations().clear();
			userProfile2.getEducations().add(e1);
			userProfile2.getEducations().add(e2);

			// skills
			userProfile2.getSkills().clear();
			userProfile2.getSkills().add("physics");
			userProfile2.getSkills().add("astronomy");
			userProfile2.getSkills().add("medicine");

			userProfileRepository.save(userProfile2);
		};
	}
}
