package cn.xdzn.oj.service.problem.interfaces.manage;

import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.service.problem.domain.tag.entity.po.Tag;
import cn.xdzn.oj.service.problem.domain.tag.service.TagDomainService;
import cn.xdzn.oj.service.problem.interfaces.dto.TagDTO;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签管理
 * @author shelly
 * @data 2024/10/20 15:06
 */
@io.swagger.v3.oas.annotations.tags.Tag(name = "标签模块")
@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagManage extends BaseController<TagDomainService, Tag, TagDTO, Long> {

    @Override
    public Result<Void> save(TagDTO instance) {
        return super.save(instance);
    }

    @Override
    public Result<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public Result<Tag> get(Long id) {
        return super.get(id);
    }

    @Override
    public Result<Void> update(TagDTO instance) {
        return super.update(instance);
    }
    @GetMapping("/page")
    public Result<PageInfo<Tag>> page(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false,defaultValue = "-1") Long groupId
    ) {
        IPage<Tag> tagPage = service.lambdaQuery()
                .like(StringUtils.isNotBlank(key), Tag::getName, key)
                .eq(!groupId.equals(-1L), Tag::getGid, groupId)
                .page(new Page<>(pageNum, pageSize));
        return Result.page(tagPage);
    }

    @Override
    protected Class<Tag> createInstance() {
        return Tag.class;
    }
}
