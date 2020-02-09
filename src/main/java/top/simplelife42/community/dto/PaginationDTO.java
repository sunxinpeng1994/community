package top.simplelife42.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    //   <<  < 4 5 6 7 8 >  >>
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;


    public void setPagination(Integer totalCount, Integer page, Integer size) {

        this.page = page;


        totalPage = (int)Math.ceil((double)totalCount/size);


        int left = page - 3;
        //-2,-1,0,1,2,3,4,5
        int right = page + 3;
        for(int i = left; i <= right; i++) {
            if(i >=1 && i <= totalPage) {
                pages.add(i);
            }
        }
        //是否展示前一页
        if(page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示后一页
        if(page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        } else {
            showEndPage = true;
        }


    }
}
