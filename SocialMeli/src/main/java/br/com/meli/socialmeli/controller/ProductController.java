package br.com.meli.socialmeli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meli.socialmeli.dto.NewPostDTO;
import br.com.meli.socialmeli.dto.NewPromoPostDTO;
import br.com.meli.socialmeli.dto.PostsFromFollowedDTO;
import br.com.meli.socialmeli.entity.Post;
import br.com.meli.socialmeli.entity.PromoPost;
import br.com.meli.socialmeli.service.PostService;
import br.com.meli.socialmeli.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private final PostService postService;

	@Autowired
	public ProductController(ProductService productService, PostService postService) {
		this.productService = productService;
		this.postService = postService;
	}

	@PostMapping("/newpost")
	public ResponseEntity<Post> newPost(@RequestBody NewPostDTO newPostDTO){
		postService.newPost(newPostDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostsFromFollowedDTO> postsFromFollowedLastTwoWeeks(
            @PathVariable Long userId, @RequestParam(defaultValue = "date_desc") String order) {
        PostsFromFollowedDTO postList = postService.postsFromFollowedLastTwoWeeks(userId, order);
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
    
    @PostMapping("/newpromopost")
    public ResponseEntity<PromoPost> newPromoPost(@RequestBody NewPromoPostDTO newPromoPostDTO){
    	postService.newPromoPost(newPromoPostDTO);
    	return ResponseEntity.status(HttpStatus.OK).build();
    }

}