package com.youngzeu.mplus.pojo.dto.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class PageDTO<T> extends Page<T> {

    private PageDTO(long pageNum, long pageSize) {
        super(pageNum, pageSize, 0L);
    }

    public static <T> PageDTO<T> defaultPage(){
        // 默认    this.total = 0L; // 数据总数0
        //        this.size = 10L; // 每页展示数据条
        //        this.current = 1L; // 当前第几页
        return new PageDTO<>();
    }

    /**
     *
     * @param pageNum   当前第几页
     * @param pageSize  每页展示数据条
     * @param <T>       泛型
     * @return          Page对象
     */
    public static <T> PageDTO<T> createPage(long pageNum, long pageSize){
        // 默认 this.total = 0L; //
        //        this.size = 10L; // 每页展示数据条
        //        this.current = 1L; // 当前第几页
        if((pageNum < 0) || (pageSize < 0)){
            StringBuffer strBuffer = new StringBuffer("pageNum,pageSize均不能小于0!");
            if(pageNum < 0){
                strBuffer.append("pageNum:[").append(pageNum).append("] ");
            }
            if(pageSize < 0){
                strBuffer.append("pageSize:[").append(pageSize).append("]");
            }
            throw new RuntimeException(strBuffer.toString());
        }
        return new PageDTO<>(pageNum, pageSize);
    }
}
