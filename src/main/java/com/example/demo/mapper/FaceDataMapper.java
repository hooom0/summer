package com.example.demo.mapper;

import com.example.demo.entity.FaceData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FaceDataMapper {

    /**
     * 插入人脸特征数据
     * @param faceData 人脸数据实体
     * @return 影响行数
     */
    @Insert("INSERT INTO face_data(person_id, feature_data, image_path, quality_score, version) " +
            "VALUES(#{personId}, #{featureData}, #{imagePath}, #{qualityScore}, #{version})")
    int insert(FaceData faceData);

    /**
     * 根据ID删除人脸数据
     * @param personId 人员ID
     * @return 影响行数
     */
    @Delete("DELETE FROM face_data WHERE person_id = #{personId}")
    int deleteByPersonId(@Param("personId") Long personId);

    /**
     * 更新人脸特征数据
     * @param faceData 人脸数据实体
     * @return 影响行数
     */
    @Update("UPDATE face_data SET " +
            "feature_data = #{featureData}, " +
            "image_path = #{imagePath}, " +
            "quality_score = #{qualityScore}, " +
            "version = #{version} " +
            "WHERE person_id = #{personId}")
    int update(FaceData faceData);

    /**
     * 根据人员ID查询人脸数据
     * @param personId 人员ID
     * @return 人脸数据列表
     */
    @Select("SELECT * FROM face_data WHERE person_id = #{personId}")
    List<FaceData> selectByPersonId(@Param("personId") Long personId);

    /**
     * 根据特征版本查询
     * @param version 特征版本号
     * @return 人脸数据列表
     */
    List<FaceData> selectByVersion(@Param("version") String version);

    /**
     * 批量插入人脸数据
     * @param faceDataList 人脸数据列表
     * @return 影响行数
     */
    int batchInsert(@Param("list") List<FaceData> faceDataList);

    /**
     * 根据质量分数范围查询
     * @param minScore 最低质量分
     * @param maxScore 最高质量分
     * @return 人脸数据列表
     */
    List<FaceData> selectByQualityScoreRange(@Param("minScore") Float minScore,
                                             @Param("maxScore") Float maxScore);
}