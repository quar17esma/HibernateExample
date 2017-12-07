package com.mkyong.dao;

import java.util.List;

import com.mkyong.model.avatar.Avatar;

public interface AvatarDAO {

	void save(Avatar avatar);
	
	List<Avatar> list();
}
