package com.collection.words.wordscollectionswebservice.domain.posts;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query(value = "SELECT p.* FROM Posts p", nativeQuery = true)
    List<Posts> findAllRand(Pageable pageable);
//ORDER BY RANDOM()
/*    List<Posts> findByCategory(String category);*/
}
