package cn.gd.cz.hong.demo.jpa.dao;

import cn.gd.cz.hong.demo.jpa.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {
}
