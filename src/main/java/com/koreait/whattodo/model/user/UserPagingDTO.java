package com.koreait.whattodo.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPagingDTO extends UserEntity {
    private int page;
    private int perPageNum;
    private int category;

    public UserPagingDTO() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public void setPage(int page) {
        if(page<=0) {
            this.page = 1;
            return;
        }

        this.page = page;
    }

    public void setPerPageNum(int perPageNum) {
        if(perPageNum<=0 || perPageNum>100) {
            this.perPageNum = 10;
            return;
        }

        this.perPageNum = perPageNum;
    }

    public int getPageStart() {
        return (this.page-1) * perPageNum;
    }
}
