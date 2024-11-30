package com.easyapi.starter.property;

import com.easyapi.core.constant.CoreConstants;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Locale;

/**
 * 配置
 **/
@Data
@ConfigurationProperties(value = "easyapi")
@ConditionalOnProperty(prefix = "easyapi", name = {"enable"}, havingValue = "true", matchIfMissing = true)
public class DocPlusConfigProperties {
    /**
     * 是否开启文档自动生成 默认开启
     */
    private boolean enable = true;
    /**
     * 项目名称
     */
    private String projectName = "easyapi";
    /**
     * 项目路径
     */
    private String projectPath = System.getProperty(CoreConstants.USER_DIR);
    /**
     * 文档版本
     */
    private String docVersion = "doc-version-" + CoreConstants.VERSION;
    /**
     * 文档生成路径
     */
    private String docPath = System.getProperty(CoreConstants.USER_DIR);
    /**
     * 是否自动生成 默认为true 若为false时,需要加@DocApi注解
     */
    private boolean autoGenerate = true;
    /**
     * 是否生成markdown文档,默认生成,若为否则仅生成html文档
     */
    private boolean generateMarkDown = true;
    /**
     * 水印
     */
    private String watermark;

    /**
     * 跨域相关配置
     */
    @NestedConfigurationProperty
    private CorsConfigProperties corsConfig = new CorsConfigProperties();

    /**
     * 生成文档语言,默认为当前系统语言
     */
    private Locale locale = Locale.getDefault();

}
