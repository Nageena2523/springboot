package com.tejait.batch15.feign;

import lombok.Data;

@Data
public class PostsDto {
	private int userId;
	private int id;
	private String title;
	private String body;

}
