package com.wy.scjg.repository;

import com.wy.scjg.bean.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 *  ElasticsearchRepository<数据Bean,一般都是String>
 */
public interface UserRepository extends ElasticsearchRepository<User,String> {

}
