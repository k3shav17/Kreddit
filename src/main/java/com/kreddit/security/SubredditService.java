package com.kreddit.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kreddit.dto.SubredditDto;
import com.kreddit.model.Subreddit;
import com.kreddit.repository.SubredditRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class SubredditService {

	private final SubredditRepository subredditRepository;

	public SubredditDto save(SubredditDto subredditDto) {
		Subreddit save = subredditRepository.save(mapSubredditDto(subredditDto));
		subredditDto.setId(save.getId());
		return subredditDto;
	}

	private Subreddit mapSubredditDto(SubredditDto subredditDto) {
		return Subreddit.builder().name(subredditDto.getName()).description(subredditDto.getDescription())
				.build();
	}

	@Transactional(readOnly = true)
	public List<SubredditDto> getAll() {
		return subredditRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());

	}

	private SubredditDto mapToDto(Subreddit subreddit) {
		return SubredditDto.builder().name(subreddit.getName()).id(subreddit.getId())
				.numberOfPosts(subreddit.getPosts().size()).build();
	}

}
