package com.example.demo.repository;

import com.example.demo.entity.PersonDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonDetailRepository extends JpaRepository<PersonDetail, Long> {

    // 根据personId查询（包含关联的Person对象）
    @Query("SELECT pd FROM PersonDetail pd JOIN FETCH pd.person WHERE pd.personId = ?1")
    Optional<PersonDetail> findDetailWithPerson(Long personId);

    // 根据身份证号查询
    Optional<PersonDetail> findByIdCard(String idCard);

    // 更新状态（批量）
    @Modifying
    @Query("UPDATE PersonDetail pd SET pd.status = :status WHERE pd.personId IN :ids")
    int updateStatusByIds(Integer status, List<Long> ids);

    // 检查手机号是否存在（排除当前记录）
    @Query("SELECT COUNT(pd) > 0 FROM PersonDetail pd WHERE pd.phone = :phone AND pd.personId <> :excludeId")
    boolean existsByPhoneExcludeId(String phone, Long excludeId);
}