package com.example.demo.service.impl;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "人员详细信息请求DTO")
public class PersonDetailRequestDTO {
    @Schema(description = "关联人员ID", example = "1234567890123456789")
    private Long personId;

    @NotBlank(message = "姓名不能为空")
    @Schema(description = "姓名", example = "张三", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "性别:0-女,1-男,2-其他", example = "1")
    private Integer gender;

    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9Xx]$",
            message = "身份证格式不正确")
    @Schema(description = "身份证号", example = "110101199003072345")
    private String idCard;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Schema(description = "手机号", example = "13800138000")
    private String phone;

    @Schema(description = "身份/职位", example = "员工")
    private String position;

    @Schema(description = "状态:0-禁止,1-可通行", example = "1")
    private Integer status;
}