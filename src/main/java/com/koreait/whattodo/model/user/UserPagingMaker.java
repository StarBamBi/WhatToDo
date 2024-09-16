package com.koreait.whattodo.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPagingMaker {
    private int totalCount; // 전체 게시글 수
    private int startPage;  // 시작 페이지 번호
    private int endPage;    // 끝 페이지 번호
    private boolean prev;
    private boolean next;


    private int displayPageNum = 10;    // 보여질 페이지 번호의 갯수

    private UserPagingDTO dto;

    public void setUserPagingDTO(UserPagingDTO dto) {
        this.dto = dto;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {
        endPage = (int) (Math.ceil(dto.getPage()/(double)displayPageNum)*displayPageNum);

        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) (Math.ceil(totalCount/(double)dto.getPerPageNum()));

        if(endPage>tempEndPage) {
            endPage=tempEndPage;
        }

        prev = startPage == 1 ? false : true;
        next = endPage * dto.getPerPageNum() >= totalCount ? false : true;
    }
}
