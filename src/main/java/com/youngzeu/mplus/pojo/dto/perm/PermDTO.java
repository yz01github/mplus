package com.youngzeu.mplus.pojo.dto.perm;

import com.youngzeu.mplus.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermDTO extends BaseEntity {

    private String permId;

    private String permType;

    private String permName;

    private String remark;
}
