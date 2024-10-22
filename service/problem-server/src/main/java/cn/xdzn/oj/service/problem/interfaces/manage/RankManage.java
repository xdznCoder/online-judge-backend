package cn.xdzn.oj.service.problem.interfaces.manage;

import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.service.problem.interfaces.dto.RankDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 排名管理
 * @author shelly
 * @data 2024/10/20 15:06
 */
@Tag(name = "排名模块")
@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class RankManage {
    @GetMapping("/acm")
    @Operation(summary = "acm排名")
    public Result<PageInfo<RankDTO>> acmRank(Long pageNum, Long pageSize,String key) {
        return Result.success();
    }


    @GetMapping("/oi")
    @Operation(summary = "oi排名")
    public Result<PageInfo<RankDTO>> oiRank(Long pageNum, Long pageSize, String key) {
        return Result.success();
    }

    @GetMapping
    @Operation(summary = "一周ac前10排名")
    public Result<RankDTO> getWeekAcRank() {
        //limit 10
        return Result.success();
    }
}
