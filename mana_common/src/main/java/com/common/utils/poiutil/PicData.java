package com.common.utils.poiutil;

public class PicData
{
    /**
     * 图片在sheet中的rowNum
     */
    private int rowNum;

    /**
     * fdfs上传成功后获取的url
     */
    private String fdfsUrl;

    public int getRowNum()
    {
        return rowNum;
    }

    public PicData(int rowNum)
    {
        super();
        this.rowNum = rowNum;
    }

    public String getFdfsUrl()
    {
        return fdfsUrl;
    }

    public void setFdfsUrl(String fdfsUrl)
    {
        this.fdfsUrl = fdfsUrl;
    }

}
