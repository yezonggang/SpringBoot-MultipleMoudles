package com.security.sys.dao.sys;

import com.security.sys.entity.sys.AccountState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStateDao extends JpaRepository<AccountState,Integer> {

    @Query(nativeQuery = true,value = "select * from account_state where userid = :userid")
    AccountState getAccountSateByUserid(Integer userid);
}
