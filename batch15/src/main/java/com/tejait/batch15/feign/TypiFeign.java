package com.tejait.batch15.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="TypiCode",url = "https://jsonplaceholder.typicode.com/")// base url
public interface TypiFeign {
	@GetMapping("comments")
	public List<CommentsDto> getcomments(); // list of comments
	@GetMapping("posts")
	public List<PostsDto>getPosts();

}
