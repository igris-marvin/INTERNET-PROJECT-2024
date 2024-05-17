package com.sanienterprise.dawn.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sanienterprise.dawn.persistence.entity.Image;


public interface ImageRepository extends JpaRepository<Image, Integer> {

    public Optional<Image> findByImageName(String imageName);

    @Transactional
    @Modifying
    @Query("UPDATE Image i SET i.imageName=:name, i.image_source=:source WHERE i.image_id=:id")
    public int updateImageById(
        @Param("name") String name,
        @Param("source") byte[] source,
        @Param("id") Integer id
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM Image i WHERE i.image_id = :id")
    public int removeImageById(
        @Param("id") Integer id
    );
}
