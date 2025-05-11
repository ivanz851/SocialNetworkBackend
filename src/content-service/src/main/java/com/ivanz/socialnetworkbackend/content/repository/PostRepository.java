package com.ivanz.socialnetworkbackend.content.repository;

import com.ivanz.socialnetworkbackend.content.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
