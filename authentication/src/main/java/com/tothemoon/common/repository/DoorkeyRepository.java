package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DoorKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DoorkeyRepository extends CrudRepository<DoorKey, Long> {

    Optional<DoorKey> findByKey(String key);
}
