package top.hcode.hoj.service.problem.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hcode.hoj.dao.ProblemTagMapper;
import top.hcode.hoj.pojo.entity.problem.ProblemTag;
import top.hcode.hoj.service.problem.ProblemTagService;

/**
 * @Author: Himit_ZH
 * @Date: 2020/12/13 23:22
 * @Description:
 */
@Service
public class ProblemTagServiceImpl extends ServiceImpl<ProblemTagMapper, ProblemTag> implements ProblemTagService {
}