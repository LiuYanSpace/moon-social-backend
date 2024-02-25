package com.tothemoon.common.repository;

import com.tothemoon.common.entity.FileInfo;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Long> {

    void deleteByUrl(String url);
}