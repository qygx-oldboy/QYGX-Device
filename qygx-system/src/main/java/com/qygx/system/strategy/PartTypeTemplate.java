package com.qygx.system.strategy;

import com.qygx.common.core.domain.entity.SysAutoCodePart;

public interface PartTypeTemplate {
    /**
     * 分段的处理规则
     * @param sysAutoCodePart
     * @return
     */
    String partHandle(SysAutoCodePart sysAutoCodePart);
}
