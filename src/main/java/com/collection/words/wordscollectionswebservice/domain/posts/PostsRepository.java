package com.collection.words.wordscollectionswebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query(value = "SELECT p.* FROM Posts p ORDER BY RANDOM()", nativeQuery = true)
    List<Posts> findAllRand();
}
