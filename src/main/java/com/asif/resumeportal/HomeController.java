package com.asif.resumeportal;

import com.asif.resumeportal.model.Job;
import com.asif.resumeportal.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(){
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName("alKhwarizmi");
        userProfileOptional.orElseThrow(() -> new RuntimeException("not found"));

        UserProfile userProfile1 = userProfileOptional.get();

        Job job1 = new Job();
        job1.setId(1);
        job1.setCompany("company 1");
        job1.setDesignation("designation 1");
        job1.setStartDate(LocalDate.of(2020, 1, 1));
        job1.setEndDate(LocalDate.of(2022, 1, 1));
        Job job2 = new Job();
        job2.setId(2);
        job2.setCompany("company 2");
        job2.setDesignation("designation 2");
        job2.setStartDate(LocalDate.of(2022, 1, 1));
        job2.setEndDate(LocalDate.of(2023, 1, 1));

        userProfile1.getJobs().clear();
        userProfile1.getJobs().add(job1);
        userProfile1.getJobs().add(job2);

        UserProfile save = userProfileRepository.save(userProfile1);

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

        return "profile-templates/" + userProfile.getTheme() + "/index";
    }
}
