package com.kreddit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kreddit.model.Comment;
import com.kreddit.model.Post;
import com.kreddit.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findByPost(Post post);
	
	List<Comment> findAllByUser(User user);

}
