package com.asif.resumeportal;

import com.asif.resumeportal.model.Job;
import com.asif.resumeportal.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(){
        UserProfile userProfile1 = new UserProfile();
        userProfile1.setUserName("alKhwarizmi");
        userProfile1.setDesignation("The father of Algebra and Master of Algorithms");
        userProfile1.setFirstName("Musa");
        userProfile1.setLastName("al-Khwarizmi");
        userProfile1.setTheme(1);

        Job job1 = new Job();
        job1.setCompany("company 1");
        job1.setDesignation("designation 1");
        job1.setStartDate(LocalDate.of(2020, 1, 1));
        job1.setEndDate(LocalDate.of(2022, 1, 1));
        Job job2 = new Job();
        job2.setCompany("company 2");
        job2.setDesignation("designation 2");
        job2.setStartDate(LocalDate.of(2022, 1, 1));
        job2.setEndDate(LocalDate.of(2023, 1, 1));

        userProfile1.setJobs(Arrays.asList(job1, job2));

        userProfileRepository.save(userProfile1);

        return "profile";
    }

    @GetMapping("/edit")
    public String edit(){
        return "edit page";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model){
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("not found: " + userId));

        UserProfile userProfile = userProfileOptional.get();

        model.addAttribute("userId", userId);
        model.addAttribute("userProfile", userProfile);
        System.out.println(userProfile.getJobs());
        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
