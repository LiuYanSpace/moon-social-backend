package com.tothemoon.common.repository;

import com.tothemoon.common.entity.DoorKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoorkeyRepository extends JpaRepository<DoorKey, Long> {

}
