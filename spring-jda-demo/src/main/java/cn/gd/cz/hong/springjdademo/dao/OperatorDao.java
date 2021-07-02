package cn.gd.cz.hong.springjdademo.dao;

import cn.gd.cz.hong.springjdademo.entity.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * 查询操作员
 */
public interface OperatorDao extends PagingAndSortingRepository<Operator, Long> {

    Optional<Operator> findById(Long id);

    //使用自动命名规则进行查询服务 
    List<Operator> findByCodeAndName(String code, String name);

    //使用@Query进行 自定义sql编写
    //nativeQuery=true,正常的sql语法
    //负责是hsql语法
    @Query(value = "select * from Operator where code = ?1", nativeQuery = true)
    List<Operator> queryByCode(String code);

    //分页
    Page<Operator> findByCode(String code, Pageable pageable);
}
