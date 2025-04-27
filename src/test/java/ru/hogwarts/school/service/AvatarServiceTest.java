package com.example.hogwarts.service;

import com.example.hogwarts.model.Avatar;
import com.example.hogwarts.repository.AvatarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AvatarServiceTest {

    @Mock
    private AvatarRepository avatarRepository;

    @InjectMocks
    private AvatarService avatarService;

    @Test
    void getAvatarsByPage_shouldReturnPaginatedResults() {
        Page<Avatar> mockPage = new PageImpl<>(Collections.singletonList(new Avatar()));
        when(avatarRepository.findAll(PageRequest.of(0, 10))).thenReturn(mockPage);

        var result = avatarService.getAvatarsByPage(0, 10);

        assertThat(result.getContent()).hasSize(1);
        verify(avatarRepository).findAll(PageRequest.of(0, 10));
    }
}