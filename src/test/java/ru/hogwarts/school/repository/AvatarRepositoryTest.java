package com.example.hogwarts.repository;

import com.example.hogwarts.model.Avatar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AvatarRepositoryTest {

    @Autowired
    private AvatarRepository avatarRepository;

    @Test
    void findAll_withPagination_shouldReturnPage() {
        Avatar avatar = new Avatar();
        avatar.setFilePath("test/path");
        avatar.setFileSize(1024L);
        avatar.setMediaType("image/jpeg");
        avatarRepository.save(avatar);

        Page<Avatar> page = avatarRepository.findAll(PageRequest.of(0, 10));
        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getTotalElements()).isEqualTo(1);
    }
}