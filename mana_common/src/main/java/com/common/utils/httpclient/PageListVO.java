package com.common.utils.httpclient;

import com.common.utils.PageHelpVO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @描述 :
 * @时间 :	11:05
 */
public class PageListVO<T>  implements Serializable{

    private int code;
    private String msg;
    /*返回集合数据*/
    private List<T> data= new ArrayList<>();
    private Page page;
    /*总页数*/
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    public PageListVO(Long total, List<T> data) {
        this.total=total;
        this.data=data;
    }
    public PageListVO() {
    }

    public PageListVO(String msg) {
        this(CodeType.FAIL, msg);
    }

    public PageListVO(CodeType codeType) {
        this(codeType, codeType.getName());
    }

    public PageListVO(CodeType codeType, String msg) {
        this(codeType, msg, Collections.<T>emptyList());
    }

    public PageListVO(CodeType codeType, List<T> data) {
        this(codeType, codeType.getName(), data);
    }

    public PageListVO(List<T> data) {
        this(data, 1, data.size(), data.size());
    }

    public PageListVO(List<T> data, String msg) {
        this(CodeType.SUCCESS, msg, data, 1, data.size(), data.size());
    }

    public PageListVO(Integer currentPage, int row, int totalNumber) {
        this(Collections.<T>emptyList(), currentPage, row, totalNumber);
    }

    public PageListVO(List<T> data, Integer currentPage, int row, int totalNumber) {
        this(CodeType.SUCCESS, "", data, currentPage, row, totalNumber);
    }

    public PageListVO(CodeType codeType, String msg, List<T> data, Integer currentPage, int row, int totalNumber) {
        this.code = codeType.getValue();
        this.msg = msg;
        this.data = data;
        this.page = new Page(currentPage, row, totalNumber);
    }

    public PageListVO(CodeType codeType, String msg, List<T> data) {
        this(codeType, msg, data, 1, 1, 1);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCodeType(CodeType codeType) {
        this.code = codeType.getValue();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public enum CodeType {

        SUCCESS("成功", 1),
        FAIL("失败", 2),
        UNLOGIN("未登录", 3),

        /* 订单 10 */
        ORDER_NOT_BELONG_MEMBER("不存在该订单", 1001),
        ORDER_PAID_OR_CANCELED("订单已支付或已取消", 1002),

        /* 支付 11 */
        PAY_SUCCESS("支付成功", 1101),
        PAY_FAIL("支付失败", 1102),
        PAY_BANK_URL("成功获取银行支付页面", 1103),

        /* 参数 12 */
        PARAMS_INCOMPLETE("参数信息不完整", 1201),
        FORM_RESUBMIT("请不要重复操作", 1202),

        /* 购物车 13 */
        CART_PRODUCT_FULL("加价购商品数量超过主商品数量", 1301),
        CART_PRODUCT_UNDER_SHELF("添加商品到购物车失败，添加的商品中存在已下架商品", 1302),
        CART_OUT_OF_INVENTORY("超出库存", 1303),

        /*账号*/
        MEMBER_UNCHECK("您的账号正在审核中，无法进行该操作", 1401),

        /* 服务器 50 */
        SERVER_ERROR("服务器发生错误！", 5000);

        private String name;
        private int value;

        CodeType(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }

    public class Page {

        private int currentPage;
        private int totalPages;
        @JsonIgnore
        private int rows;
        @JsonIgnore
        private long totalNumber;

        public Page() {
        }

        public Page(Integer currentPage, int rows, long totalNumber) {
            if (currentPage == null) {
                currentPage = 1;
            }
            if (rows <= 0) {
                rows = 1;
            }
            this.currentPage = currentPage;
            this.rows = rows;
            setTotalNumber(totalNumber);
        }

        public void setTotalNumber(long totalNumber) {
            if (this.totalNumber <= 0 || this.totalNumber > totalNumber) {
                this.totalNumber = totalNumber;
            }
            this.totalPages = (int) this.totalNumber / rows;
            if (this.totalNumber % rows != 0) {
                this.totalPages += 1;
            }
            if (this.currentPage < 1 || this.currentPage > this.totalPages) {
                this.currentPage = 1;
            }
        }

        @JsonIgnore
        public int getIndex() {
            return (currentPage - 1) * rows;
        }

        public int getRows() {
            return rows;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }

}
