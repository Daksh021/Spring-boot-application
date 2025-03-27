package com.example.Basic_Project.Repository;

import com.example.Basic_Project.Model.Blog;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {

    Optional<Blog> findById(UUID uuid);

}
