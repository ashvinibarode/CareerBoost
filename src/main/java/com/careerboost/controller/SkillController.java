package com.careerboost.controller;

import com.careerboost.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "*")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/user/{userId}")
    public List<String> getSkills(@PathVariable Long userId) {

        return skillService.getSkills(userId);

    }

    @PostMapping("/{userId}")
    public void saveSkills(@PathVariable Long userId,
                           @RequestBody List<String> skills) {

        skillService.saveSkills(userId, skills);

    }

}