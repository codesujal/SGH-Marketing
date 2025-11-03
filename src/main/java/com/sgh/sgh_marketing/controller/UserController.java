package com.sgh.sgh_marketing.controller;

import com.sgh.sgh_marketing.model.Feedback;
import com.sgh.sgh_marketing.model.Patient;
import com.sgh.sgh_marketing.model.UserModel;
import com.sgh.sgh_marketing.repository.FeedbackRepository;
import com.sgh.sgh_marketing.repository.PatientRepository;
import com.sgh.sgh_marketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private PatientRepository patientRepository; // ✅ added

    @GetMapping("/")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "index";
    }

    @GetMapping("/signin")
    public String showSigninPage(Model model) {
        return "auth/signin";
    }

    @GetMapping("/feedback")
    public String showFeedbackPage(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback/feedback";
    }

    @GetMapping("/feedbacks")
    public String showAllFeedbacks(Model model) {
        var feedbacks = feedbackRepository.findAll();
        model.addAttribute("feedbackList", feedbacks);
        return "dashboard/feedbacks";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@ModelAttribute Feedback feedback) {
        feedbackRepository.save(feedback);
        return "redirect:/thank-you";
    }

    @GetMapping("/thank-you")
    public String thankYou() {
        return "feedback/thank-you";
    }

    @PostMapping("/")
    public String processLogin(@ModelAttribute LoginRequest loginRequest, RedirectAttributes redirectAttributes) {
        Optional<UserModel> userOpt = userRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginRequest.getPassword())) {
            return "redirect:/feedbacks";
        }

        redirectAttributes.addAttribute("error", "Invalid email or password.");
        return "redirect:/signin";
    }

    @GetMapping("/maps")
    public String showPatientMap() {
        return "dashboard/maps"; // maps.html
    }

    @GetMapping("/api/patients")
    @ResponseBody
    public List<Patient> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        // Debug
        System.out.println("=== Patients fetched from MongoDB ===");
        patients.forEach(p ->
            System.out.println(p.getCity_or_village() + " | " + p.getDiagnosis() +
                    " | (" + p.getLatitude() + ", " + p.getLongitude() + ")")
        );

        return patients;
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
