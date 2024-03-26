package com.tothemoon.common.repository;

import com.tothemoon.common.entity.FileInfo;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;


@Repository
public interface FileRepository extends ListCrudRepository<FileInfo, Long> {

    void deleteByUrl(String url);
}