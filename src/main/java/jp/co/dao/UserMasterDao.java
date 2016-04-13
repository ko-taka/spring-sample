package jp.co.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import jp.co.entity.UserMaster;


@ConfigAutowireable
@Dao
public interface UserMasterDao {
    @Select
    List<UserMaster> selectAll();


    @Insert
    @Transactional
    int insert(UserMaster usermaster);

}
