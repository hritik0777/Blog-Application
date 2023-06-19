package com.hritik.blog.payloads;
import java.util.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	
	private int pageSize;
	private int pageNumber;
	private List<PostDto> content;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
	
	
	
	
	

}
