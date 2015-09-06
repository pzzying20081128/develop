/**
 * Program  : PaginationService.java
 * Author   : liaolj
 * Create   : 2008-12-16 下午03:22:24
 *
 * Copyright 2006 by Embedded Internet Solutions Inc.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Embedded Internet Solutions Inc.("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Embedded Internet Solutions Inc.
 *
 */

package cn.zy.apps.tools.web ;

/**
 * 分页服务
 */
public class PaginationService {

    private int perPageRows = 10 ; // 每个PAGE 有多少条数据

    private int nowPage ; // 现在的page

    private int prePage ; // 上一页

    private int nextPage ;// 下一页

    private int startRow ;// 开始行数

    private int sumRows ;// 总行数

    private int sumPages ;// 总页数

    private int lastPage ; // 最后页数

    private int pageleaf = 10 ;// 每leaf 的 page 数

    private int nowPageSum ;// 当前大页(所拥有的page数)

    private boolean prePageleaf ; // 是否有上一leaf

    private boolean nextPageleaf ;// 是否有下 一leaf

    private int nowleafPageIndex ;

    //    private int nowleafStartIndex;

    private int nextleafStartPageIndex ;// 下一页开始的page数

    private int preleafPageIndex ;// 上一页开始的page数

    private int nextleaf ;// 下一大页

    private int preleaf ;// 上一大页

    public int getSumRows() {
        return sumRows ;
    }

    public void setSumRows(int sumRows) {
        this.sumRows = sumRows ;
    }

    public int getNowPage() {
        return nowPage ;
    }

    public PaginationService(int perPageRows) {
        super() ;
        this.perPageRows = perPageRows ;
    }

    public PaginationService() {
        this(10) ;

    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage ;
    }

    public int getPrePage() {
        return prePage ;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage ;
    }

    public int getNextPage() {
        return nextPage ;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage ;
    }

    public int getStartRow() {
        return startRow ;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow ;
    }

    public int getPerPageRows() {
        return perPageRows ;
    }

    public void setPerPageRows(int perPageRows) {
        if (perPageRows == 0) this.perPageRows = 20 ;
        else
            this.perPageRows = perPageRows ;
    }

    public int getSumPages() {
        return sumPages ;
    }

    public void setSumPages(int sumPages) {
        this.sumPages = sumPages ;
    }

    public void compute(int nowPage) {
        compute(sumRows, nowPage) ;
    }

    public void compute(int sumRows, int nowPage) {

        //		if (nowPage <=0) this.nowPage = 1 ;
        //	    System.out.println("  1 sumPages   "+sumPages +" nowPage "+nowPage) ;
        this.sumRows = sumRows ;
        this.nowPage = nowPage ;

        pages() ;

        //		this.nowPage = this.nowPage + 1;

    }

    private void pages() {
        //	    System.out.println("  2 sumPages   "+sumPages +" nowPage "+this.nowPage) ;
        //	    总行数 /每个PAGE 有多少条数据 
        int size = sumRows / this.perPageRows ;

        //		System.out.println(" sumRows   "+sumRows+"  this.perPageRows  "+this.perPageRows) ;

        int xx = sumRows % this.perPageRows == 0 ? 0 : 1 ;
        // 总leaf 数
        sumPages = size + xx ;

        //		 System.out.println(" sumPages   "+sumPages +" nowPage "+nowPage) ;

        //		if (sumPages == 0) {
        //			sumPages = 1;
        //		}

        //		  System.out.println(" sumPages   "+sumPages) ;

        /**********************************/
        prePage = nowPage - 1 ;

        //		if (prePage <= 1) prePage = 1;

        nextPage = nowPage + 1 ;

        if (nextPage > this.sumPages) nextPage = sumPages ;

        lastPage = sumPages ;

        //		System.out.println(" 上一页   "+prePage + "  下一页  "+nextPage+"   lastPage  "+lastPage) ;

        /******************************************************/

        this.startRow = (this.nowPage - 1) * perPageRows ;

        //		System.out.println(" 开始行数   "+this.startRow) ;

        // 总leaf 数
        int pageleafSum = sumPages / pageleaf + (sumPages % pageleaf == 0 ? 0 : 1) ;
        // //System.out.println("------- " + pageleafSum + " sumPages " +
        // sumPages);
        //		System.out.println(" 总leaf 数    "+pageleafSum) ; 

        // 当前的 leaf 数
        int itmpPageleaf = nowPage / pageleaf ;// 现在的leaf
        itmpPageleaf = nowPage / pageleaf + (nowPage % pageleaf == 0 ? 0 : 1) ; //itmpPageleaf+1;

        nextleaf = itmpPageleaf + 1 ;
        if (nextleaf > pageleafSum) nextleaf = pageleafSum ;
        preleaf = itmpPageleaf - 1 ;
        if (preleaf <= 0) preleaf = 1 ;

        System.out.println(" 当前的 leaf 数   " + itmpPageleaf + "  nextleaf " + nextleaf + "   preleaf   " + preleaf) ;

        if ((itmpPageleaf) * pageleaf * perPageRows < sumRows) {
            nowPageSum = pageleaf ;
            //		    System.out.println(" 1 当前left (所拥有的page数)  "+nowPageSum) ; 
        } else {
            int x = sumRows - (itmpPageleaf - 1) * pageleaf * perPageRows ;
            nowPageSum = x / (perPageRows) + (x % (perPageRows) == 0 ? 0 : 1) ;
        }

        //		System.out.println(" 当前left (所拥有的page数)  "+nowPageSum) ; 

        if (itmpPageleaf == 0) prePageleaf = false ;
        else
            prePageleaf = true ;

        if (itmpPageleaf == (pageleafSum)) nextPageleaf = false ;
        else
            nextPageleaf = true ;

        //		nowPageleafStartIndex = (itmpPageleaf + 1);

        //		  System.out.println(" prePageleaf  "+prePageleaf+"   nextPageleaf   "+nextPageleaf) ; 

        nextleafStartPageIndex = (nextleaf) * pageleaf - (pageleaf - 1) ;
        if (!nextPageleaf) nextleafStartPageIndex = lastPage ;

        preleafPageIndex = ((preleaf)) * pageleaf - (pageleaf - 1) ;
        if (!prePageleaf) preleafPageIndex = 1 ;

        nowleafPageIndex = ((itmpPageleaf)) * pageleaf - (pageleaf - 1) ;

        //		   System.out.println("下一页开始的page数   "+nextleafStartPageIndex +"   上一页开始的page数  "+preleafPageIndex) ; 

    }

    /**
     * 计拴 现在的索引
     * 
     * @param index
     * @return
     */
    //	public int nowPage(int index) {
    //		return (nowPageleafStartIndex - 1) * pageleaf + index;
    //
    //	}

    //	public int getNowPageleaf() {
    //		return nowPageleaf;
    //	}

    public String nextPageUrl() {
        return "&nextPage=" + nextPage ;
    }

    public int getLastPage() {
        return lastPage ;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage ;
    }

    public int getPageleaf() {
        return pageleaf ;
    }

    public void setPageleaf(int pageleaf) {
        this.pageleaf = pageleaf ;
    }

    public boolean isPrePageleaf() {
        return prePageleaf ;
    }

    public boolean isNextPageleaf() {
        return nextPageleaf ;
    }

    //	public int getNowPageleafStartIndex() {
    //		return nowPageleafStartIndex;
    //	}

    //	public int getNextPageleafIndex() {
    //		return nextPageleafIndex;
    //	}
    //
    //	public int getPrePageleafIndex() {
    //		return prePageleafIndex;
    //	}

    public static void main(String[] args) {

        PaginationService pages = new PaginationService(8) ;
        pages.setPageleaf(1) ;
        pages.setSumRows(9);
        pages.compute(1) ;
        System.out.println(" -> "+pages.getSumPages());
        //		 System.out.println("---------getNowPageleafStartIndex - > " +
        //		 pages.getNowPageleafStartIndex()
        //		 + "  " + "   nowPage     " + pages.nowPage
        //		 + "  -  prePage  >  " + pages.prePage + "    nextPage   " + pages.nextPage + "  prePageleafIndex   >  "
        //		 + pages.prePageleafIndex);

    }

    public int getNowPageSum() {
        return nowPageSum ;
    }

    public void setNowPageSum(int nowPageSum) {
        this.nowPageSum = nowPageSum ;
    }

    public int getNextleafStartPageIndex() {
        return nextleafStartPageIndex ;
    }

    public void setNextleafStartPageIndex(int nextleafStartPageIndex) {
        this.nextleafStartPageIndex = nextleafStartPageIndex ;
    }

    public int getPreleafPageIndex() {
        return preleafPageIndex ;
    }

    public void setPreleafPageIndex(int preleafPageIndex) {
        this.preleafPageIndex = preleafPageIndex ;
    }

    public int getNextleaf() {
        return nextleaf ;
    }

    public void setNextleaf(int nextleaf) {
        this.nextleaf = nextleaf ;
    }

    public int getPreleaf() {
        return preleaf ;
    }

    public void setPreleaf(int preleaf) {
        this.preleaf = preleaf ;
    }

    public void setPrePageleaf(boolean prePageleaf) {
        this.prePageleaf = prePageleaf ;
    }

    public void setNextPageleaf(boolean nextPageleaf) {
        this.nextPageleaf = nextPageleaf ;
    }

    public int getNowleafPageIndex() {
        return nowleafPageIndex ;
    }

    public void setNowleafPageIndex(int nowleafPageIndex) {
        this.nowleafPageIndex = nowleafPageIndex ;
    }

}
