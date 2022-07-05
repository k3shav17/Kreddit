package com.kreddit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kreddit.model.Post;
import com.kreddit.model.Subreddit;
import com.kreddit.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> findAllBySubreddit(Subreddit subreddit);
	
	List<Post> findByUser(User user);
}
