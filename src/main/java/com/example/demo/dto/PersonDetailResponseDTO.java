package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "人员详细信息响应DTO")
public class PersonDetailResponseDTO {
    @Schema(description = "关联人员ID", example = "1234567890123456789")
    private Long personId;

    @Schema(description = "姓名", example = "张三")
    private String name;

    @Schema(description = "性别:0-女,1-男,2-其他", example = "1")
    private Integer gender;

    @Schema(description = "身份证号", example = "110101199003072345")
    private String idCard;

    @Schema(description = "手机号", example = "13800138000")
    private String phone;

    @Schema(description = "身份/职位", example = "员工")
    private String position;

    @Schema(description = "注册时间", example = "2023-01-01T00:00:00")
    private LocalDateTime registerTime;

    @Schema(description = "更新时间", example = "2023-01-01T00:00:00")
    private LocalDateTime updateTime;

    @Schema(description = "状态:0-禁止,1-可通行", example = "1")
    private Integer status;
}