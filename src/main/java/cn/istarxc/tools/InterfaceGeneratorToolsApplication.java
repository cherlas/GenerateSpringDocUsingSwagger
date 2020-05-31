package cn.istarxc.tools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.istarxc.tools.mapper")
public class InterfaceGeneratorToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterfaceGeneratorToolsApplication.class, args);
    }
}
