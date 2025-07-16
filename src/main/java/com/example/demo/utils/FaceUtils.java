package com.example.demo.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class FaceUtils {

    private final String appId;
    private final String apiKey;
    private final String secretKey;
    private final ImageUtil imageUtil;

    // 使用构造器注入参数
    public FaceUtils(
            @Value("${baidu.face.app-id}") String appId,
            @Value("${baidu.face.api-key}") String apiKey,
            @Value("${baidu.face.secret-key}") String secretKey
    ) {
        this.appId = appId;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        ImageUtil imageUtil = new ImageUtil();
        this.imageUtil = imageUtil;
    }

    // 创建AipFace实例的工厂方法
    private AipFace createAipFaceClient() {
        AipFace client = new AipFace(appId, apiKey, secretKey);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

    // 人脸注册方法
    public JSONObject registerFace(String imagePath, String groupId, String userId, String userInfo) {
        AipFace client = createAipFaceClient();
        String image = imageUtil.imageToBase64(imagePath);

        HashMap<String, String> options = new HashMap<>();
        options.put("user_info", userInfo);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");

        return client.addUser(image, "BASE64", groupId, userId, options);
    }

    // 人脸识别方法（1:N搜索）
    public JSONObject recognizeFace(String imagePath, String groupIdList) {
        AipFace client = createAipFaceClient();
        String image = imageUtil.imageToBase64(imagePath);

        HashMap<String, String> options = new HashMap<>();
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("max_user_num", "3");

        return client.search(image, "BASE64", groupIdList, options);
    }

    // 人脸删除方法
    public JSONObject deleteFace(String userId, String groupId, String faceToken) {
        HashMap<String, String> options = new HashMap<String, String>();
        AipFace client = createAipFaceClient();
        return client.faceDelete(userId, groupId, faceToken,options);
    }

    // 人脸检测方法（检测图片中是否有人脸）
    public JSONObject detectFace(String imagePath) {
        AipFace client = createAipFaceClient();
        String image = imageUtil.imageToBase64(imagePath);

        HashMap<String, String> options = new HashMap<>();
        options.put("face_field", "age,beauty,expression,face_shape,gender,glasses,landmark,race,qualities");
        options.put("max_face_num", "1");

        return client.detect(image, "BASE64", options);
    }

    // 人脸更新方法
    public JSONObject updateFace(String imagePath, String groupId, String userId, String userInfo) {
        AipFace client = createAipFaceClient();
        String image = imageUtil.imageToBase64(imagePath);

        HashMap<String, String> options = new HashMap<>();
        options.put("user_info", userInfo);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");

        return client.updateUser(image, "BASE64", groupId, userId, options);
    }



}