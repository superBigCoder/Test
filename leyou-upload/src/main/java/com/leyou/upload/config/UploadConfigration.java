package com.leyou.upload.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class UploadConfigration {
    @Bean
  public CorsFilter corsFilter(){
        //参数是cors数据源
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //运行跨域的域名,如果携带cookie不能写*.*代表所有域名都可以跨域
        corsConfiguration.addAllowedOrigin("http://manage.leyou.com");
        corsConfiguration.setAllowCredentials(true);//运行携带cookie
        corsConfiguration.addAllowedMethod("*");//代表所有类型的请求方法
        corsConfiguration.addAllowedHeader("*");//运行携带任何头信息
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(configurationSource);
    }

}
