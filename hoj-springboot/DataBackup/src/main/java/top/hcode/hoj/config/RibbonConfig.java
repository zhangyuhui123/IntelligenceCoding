package top.hcode.hoj.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.hcode.hoj.judge.self.JudgeChooseRule;

/**
 * @Author: Himit_ZH
 * @Date: 2021/2/4 23:10
 * @Description:
 */
@Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonRule() {
        // 随机的负载均衡策略对象
        return new JudgeChooseRule();
    }

}