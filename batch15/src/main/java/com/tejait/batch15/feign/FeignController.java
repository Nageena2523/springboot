package com.tejait.batch15.feign;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("feign")
public class FeignController {
	TypiFeign typifeign;
	
	@GetMapping("getComments")
	public ResponseEntity<List<CommentsDto>>getComments(){
		List<CommentsDto>comments= typifeign.getcomments();
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	@GetMapping("getPosts")
	public ResponseEntity<List<PostsDto>> getPosts(){
		List<PostsDto>posts= typifeign.getPosts();
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}

}
