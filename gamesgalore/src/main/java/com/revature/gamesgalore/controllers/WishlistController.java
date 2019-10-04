package com.revature.gamesgalore.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gamesgalore.dao.Wishlist;
import com.revature.gamesgalore.dto.WishlistDTO;
import com.revature.gamesgalore.service.MasterService;

@CrossOrigin
@RestController
public class WishlistController {

	/**
	 * An object used to handle the business logic for all Wishlist objects. It's
	 * creation is handled by Spring's container.
	 */
	@Autowired
	MasterService<Wishlist> wishlistService;

	/**
	 * @param response     The HTTP response from the GET operation.
	 * @param wishlistName The Wishlist name to filter search
	 * @return A collection of Wishlist POJO's.
	 */
	@GetMapping(value = "/wishlists")
	public List<WishlistDTO> getWishlists(HttpServletResponse response,
			@RequestParam(required = false) String wishlistName,
			@RequestParam(required = false) String accountUsername) {
		response.setStatus(200);
		List<Wishlist> wishlists = wishlistService.getByParams(wishlistName, accountUsername);
		List<WishlistDTO> wishlistDTOs = new ArrayList<>();
		for (Wishlist wishlist : wishlists) {
			WishlistDTO wishlistDTO = new WishlistDTO();
			BeanUtils.copyProperties(wishlist, wishlistDTO);
			wishlistDTOs.add(wishlistDTO);
		}
		return wishlistDTOs;
	}

	/**
	 * 
	 * @param response     The HTTP response from the POST operation.
	 * @param wishlistDTOs A array of objects containing a POJO representation of
	 *                     Wishlist objects.
	 */
	@PostMapping(value = "/wishlists")
	public void createWishlists(HttpServletResponse response, @RequestBody WishlistDTO wishlistDTO1) {
		System.out.println("jhsjshdjhsjdhd");
		//System.out.println(wishlistDTOs);
		List<WishlistDTO> wishlistDTOs = new ArrayList<WishlistDTO>();
		wishlistDTOs.add(wishlistDTO1);
		List<Wishlist> wishlists = new ArrayList<>();
		for (WishlistDTO wishlistDTO : wishlistDTOs) {
			Wishlist wishlist = new Wishlist();
			wishlist.copyPropertiesFrom(wishlistDTO);

			wishlists.add(wishlist);
		}
		response.setStatus(201);
		wishlistService.add(wishlists);
	}

	/**
	 * 
	 * @param response   The HTTP response from the GET operation.
	 * @param wishlistId The numeric id pertaining to a specific Wishlist object. It
	 *                   must be passed in the url path.
	 * @return A specific Wishlist POJO
	 */
	@GetMapping(value = "/wishlists/{id}")
	public WishlistDTO getGame(HttpServletResponse response, @PathVariable("id") Long wishlistId) {
		response.setStatus(200);
		Wishlist wishlist = wishlistService.get(wishlistId);
		WishlistDTO wishlistDTO = new WishlistDTO();
		BeanUtils.copyProperties(wishlist, wishlistDTO);
		return wishlistDTO;
	}

	/**
	 * 
	 * @param response    The HTTP response from the PUT operation.
	 * @param wishlistDTO A POJO object representing a Wishlist object.
	 * @param wishlistId  The numeric id pertaining to a specific Wishlist object.
	 *                    It must be passed in the url path.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping(value = "/wishlists/{id}")
	public void putGame(HttpServletResponse response, @NotNull @RequestBody WishlistDTO wishlistDTO,
			@PathVariable("id") Long wishlistId) {
		Wishlist wishlist = new Wishlist();
		wishlist.copyPropertiesFrom(wishlistDTO);
		response.setStatus(200);
		wishlistService.update(wishlist, wishlistId);
	}

	/**
	 * 
	 * @param response   The HTTP response from the DELETE operation.
	 * @param wishlistId The numeric id pertaining to a specific Wishlist object. It
	 *                   must be passed in the url path.
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(value = "/wishlists/{id}")
	public void deleteGame(HttpServletResponse response, @PathVariable("id") Long wishlistId) {
		response.setStatus(204);
		wishlistService.delete(wishlistId);
	}
}
