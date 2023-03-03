package com.groupeisi.examenspringdevops.controller;

import com.groupeisi.examenspringdevops.entity.CVEntity;
import com.groupeisi.examenspringdevops.service.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author MS GASSAMA
 */
@Controller
public class HomeController {
    @Autowired
    private CVService service;

    @GetMapping("/")
    public  String index(@AuthenticationPrincipal UserDetails userDetails, ModelMap modelMap) {
        CVEntity cvEntity = service.getCV(userDetails.getUsername());
        modelMap.addAttribute("currentUser", cvEntity);
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CVEntity cv = service.edit(id);
        model.addAttribute("cv", cv);
        return "edit";
    }
    @PostMapping("/update")
    public String updateCVEntity( CVEntity cv) {
//        System.out.println(cv.toString());
          service.update(cv);
//        CVEntity cvEntity = service.edit(cv.getId());
//        cvEntity.setEmail(cv.getEmail());
//        cvEntity.setNom(cv.getNom());
//        cvEntity.setPrenom(cv.getPrenom());
//        cvEntity.setAge(cv.getAge());
//        cvEntity.setTelephone(cv.getTelephone());
//        cvEntity.setAdresse(cv.getAdresse());
//        cvEntity.setSpecialite(cv.getSpecialite());
//        cvEntity.setExperienceProfessionnelle(cv.getExperienceProfessionnelle());
//        service.save(cvEntity);
        return "redirect:/";
    }

}