package com.asif.resumeportal;

import com.asif.resumeportal.model.Education;
import com.asif.resumeportal.model.Job;
import com.asif.resumeportal.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
        /*Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName("alKhwarizmi");
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
        System.out.println(userProfile1);
        UserProfile save = userProfileRepository.save(userProfile1);
        System.out.println(userProfile1);*/
        return "profile";
    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal, @RequestParam(required = false) String add){
        String userId = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("not found: " + userId));

        UserProfile userProfile = userProfileOptional.get();
        if("job".equals(add)){
            userProfile.getJobs().add(new Job());
        } else if("education".equals(add)){
            userProfile.getEducations().add(new Education());
        } else if("skill".equals(add)){
            userProfile.getSkills().add("");
        }
        model.addAttribute("userId", userId);
        model.addAttribute("userProfile", userProfile);
        return "profile-edit";
    }

    @GetMapping("/delete")
    public String delete(Model model, Principal principal, @RequestParam String type, @RequestParam int index) {
        String userId = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
        UserProfile userProfile = userProfileOptional.get();
        if ("job".equals(type)) {
            userProfile.getJobs().remove(index);
        } else if ("education".equals(type)) {
            userProfile.getEducations().remove(index);
        } else if ("skill".equals(type)) {
            userProfile.getSkills().remove(index);
        }
        userProfileRepository.save(userProfile);
        return "redirect:/edit";
    }

    @PostMapping("/edit")
    public String postEdit(Model model, Principal principal, @ModelAttribute UserProfile userProfile){
        String userName = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userName);
        userProfileOptional.orElseThrow(() -> new RuntimeException("not found: " + userName));
        UserProfile savedUserProfile = userProfileOptional.get();
        userProfile.setId(savedUserProfile.getId());
        userProfile.setUserName(userName);
        userProfileRepository.save(userProfile);
        return "redirect:/view/" + userName;
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
