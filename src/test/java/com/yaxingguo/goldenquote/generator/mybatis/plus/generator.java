package com.yaxingguo.goldenquote.generator.mybatis.plus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class generator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/golden_quote", "root", "1021")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者// 开启 swagger 模式
                            .outputDir("D:\\YXProjects\\GoldenQuote\\src\\test\\java\\com\\yaxingguo\\goldenquote\\generator\\mybatis\\plus"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.yaxingguo.goldenquote") // 设置父包名
                                .moduleName("api/v1") // 设置父包模块名
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\YXProjects\\GoldenQuote\\src\\test\\java\\com\\yaxingguo\\goldenquote\\generator\\mybatis\\plus\\mapper")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude("t_user_favorites")
                                .addTablePrefix("t_", "c_") // 设置过滤表前缀
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
