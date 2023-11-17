package com.tang.common.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePage implements Serializable {
    private static final long serialVersionUID = -7552216077032984884L;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String orderByField = "";

    private String order = "desc";

    public Page startPage(){
        return new Page(Objects.isNull(pageNum)?1:pageNum, Objects.isNull(pageSize)?10:pageSize);
    }
}
