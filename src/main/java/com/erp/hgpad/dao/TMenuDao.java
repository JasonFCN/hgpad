/**
 * 
 */
package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.hgpad.entity.TMenu;

/**
 * @author 武杰
 *
 */
public interface TMenuDao extends JpaRepository<TMenu, String> {

	List<TMenu> findByStatusAndStateOrderByNoAsc(Integer status, Integer state);

}
