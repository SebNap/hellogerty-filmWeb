package com.gerty.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * mybatis Plus代码生成器
 */
public class CodeGenerator {
    public static void main(String[] args) {
        generate();

    }

    private static void generate(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/hellogerty?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "Sebastian0821")
                .globalConfig(builder -> {
                    builder.author("SebNap(wyq)") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\xiaobai\\springboot\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.gerty.springboot") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\xiaobai\\springboot\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("wyq_user") // 设置需要生成的表名
                            .addTablePrefix("wyq_", ""); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
