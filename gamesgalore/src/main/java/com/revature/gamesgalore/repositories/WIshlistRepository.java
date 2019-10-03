package com.revature.gamesgalore.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.revature.gamesgalore.dao.Wishlist;

public interface WIshlistRepository extends CrudRepository<Wishlist, Long>, JpaSpecificationExecutor<Wishlist>  {

}
