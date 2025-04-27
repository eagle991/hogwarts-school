package com.example.hogwarts.controller;

import com.example.hogwarts.model.Avatar;
import com.example.hogwarts.service.AvatarService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping
    public Page<Avatar> getAvatarsByPage(
            @RequestParam int page,
            @RequestParam int size) {
        return avatarService.getAvatarsByPage(page, size);
    }
}