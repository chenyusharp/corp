package com.xiazhenyu.demo;

import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author mybatisplus-generator
 * @since 2023-04-14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicGoodsVO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 大货款号
     */
    private String goodsNo;

    /**
     * 设计款号
     */
    private String designNo;

    /**
     * 设计师
     */
    private String designer;

    /**
     * 颜色code
     */
    private String colorCode;

    /**
     * 颜色名字
     */
    private String colorName;

    /**
     * 供应链ip
     */
    private String supplyChainIp;

    /**
     * 是否有配件
     */
    private Integer hasPart;

    /**
     * 是否有配件字符串
     */
    private String hasPartStr;

    /**
     * 设计下单数
     */
    private Integer designOrderNum;

    /**
     * 下单尺码
     */
    private String sizeShape;

    /**
     * 尺码比例
     */
    private String sizeShapeRatio;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否软删除
     */
    private Integer deleted;

    /**
     * 企划是否启用
     */
    private Integer planStatus;

    /**
     * 企划是否启用字符串
     */
    private String planStatusStr;

    /**
     * 设计款号的图片
     */
    private String image;

    /**
     * 设计款号的图片的可查看地址
     */
    private String imageUrl;

    /**
     * 设计款号图片的缩略图
     */
    private String designThumbnail;

    /**
     * 开发类型
     */
    private String developTypeName;

    /**
     * 开发工厂
     */
    private String developFactoryName;

    /**
     * 是否延期
     */
    private Integer isDelay;

    /**
     * 是否延期字符串
     */
    private String isDelayStr;

    /**
     * 延期天数
     */
    private Integer delayDays;

    /**
     * 设计稿日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date designDraftDate;

    /**
     * 是否二次核价
     */
    private Integer secondCalPrice;

    /**
     * 是否二次核价字符串
     */
    private String secondCalPriceStr;

    /**
     * 是否下单
     */
    private Integer haveOrder;

    /**
     * 首单创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date orderCreated;

    /**
     * 是否下单字符串
     */
    private String haveOrderStr;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌名称
     */
    private Long brandId;


    /**
     * 年份
     */
    private Integer years;

    /**
     * 季节
     */
    private String season;

    /**
     * 波段
     */
    private String waveBandName;

    /**
     * 一级品类
     */
    private String firstLevel;

    /**
     * 二级品类
     */
    private String secondLevel;

    /**
     * 三级品类
     */
    private String thirdLevel;

    /**
     * 系列
     */
    private String series;

    /**
     * 款式描述
     */
    private String styleDesc;

    /**
     * 创建来源(0-设计下单指令 1-成衣采购创建 2-改标大货款号创建 3-换款 4-印花 5-光板)
     */
    private Integer dataSource;

    /**
     * 创建来源字符串
     */
    private String dataSourceStr;

    /**
     * 款式企划类型；1：常规款;2：印花款;3：光板款;4：换标款;5：换款款
     */
    private Integer enterpriseType;

    /**
     * 原大货款号
     */
    private String originGoodsNo;

    /**
     * 所有尺码，以:分隔（该尺码号型包含尺码）
     */
    private String size;

    /**
     * 尺码号型编码
     */
    private String sizeShapeCode;

    /**
     * 款式基础类型id
     */
    private Long styleTypeId;

    /**
     * 吊牌颜色代码
     */
    private String tagColorCode;

    /**
     * 大货BOM状态;10：待提交;20: 待审核;30: 已审核
     */
    private Integer produceBomStatus;

    private String produceBomStatusStr;

    /**
     * 原颜色代号
     */
    private String originColorCode;

    /**
     * 原颜色名称
     */
    private String originColorName;

    /**
     * 材质
     */
    private String material;

    /**
     * 饰品执行标准
     */
    private String accessoriesExecuteStandard;

    /**
     * 是否钉钉提醒下首单
     * 1：是 0：否
     */
    private Integer alertFlag;
    private String alertFlagStr;

    /**
     * 款式企划类型；1：常规款;2：印花款;3：光板款;4：换标款;5：换款款
     */
    private String enterpriseTypeStr;

    /**
     * 尺码id
     */
    private Long sizeShapeId;

    /**
     * 尺码类型编码
     */
    private String sizeTypeCode;

    /**
     * 尺码类型名称
     */
    private String sizeTypeName;

    /**
     * 性别
     */
    private String gender;

    /**
     * 款号下所有颜色
     */
    private List<BasicGoodsVO> selectors;


    /**
     * fms配置的唯品品类上限价格
     */
    private BigDecimal brandLimitPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getDesignNo() {
        return designNo;
    }

    public void setDesignNo(String designNo) {
        this.designNo = designNo;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSupplyChainIp() {
        return supplyChainIp;
    }

    public void setSupplyChainIp(String supplyChainIp) {
        this.supplyChainIp = supplyChainIp;
    }

    public Integer getHasPart() {
        return hasPart;
    }

    public void setHasPart(Integer hasPart) {
        this.hasPart = hasPart;
    }

    public String getHasPartStr() {
        return hasPartStr;
    }

    public void setHasPartStr(String hasPartStr) {
        this.hasPartStr = hasPartStr;
    }

    public Integer getDesignOrderNum() {
        return designOrderNum;
    }

    public void setDesignOrderNum(Integer designOrderNum) {
        this.designOrderNum = designOrderNum;
    }

    public String getSizeShape() {
        return sizeShape;
    }

    public void setSizeShape(String sizeShape) {
        this.sizeShape = sizeShape;
    }

    public String getSizeShapeRatio() {
        return sizeShapeRatio;
    }

    public void setSizeShapeRatio(String sizeShapeRatio) {
        this.sizeShapeRatio = sizeShapeRatio;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
    }

    public String getPlanStatusStr() {
        return planStatusStr;
    }

    public void setPlanStatusStr(String planStatusStr) {
        this.planStatusStr = planStatusStr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDesignThumbnail() {
        return designThumbnail;
    }

    public void setDesignThumbnail(String designThumbnail) {
        this.designThumbnail = designThumbnail;
    }

    public String getDevelopTypeName() {
        return developTypeName;
    }

    public void setDevelopTypeName(String developTypeName) {
        this.developTypeName = developTypeName;
    }

    public String getDevelopFactoryName() {
        return developFactoryName;
    }

    public void setDevelopFactoryName(String developFactoryName) {
        this.developFactoryName = developFactoryName;
    }

    public Integer getIsDelay() {
        return isDelay;
    }

    public void setIsDelay(Integer isDelay) {
        this.isDelay = isDelay;
    }

    public String getIsDelayStr() {
        return isDelayStr;
    }

    public void setIsDelayStr(String isDelayStr) {
        this.isDelayStr = isDelayStr;
    }

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public Date getDesignDraftDate() {
        return designDraftDate;
    }

    public void setDesignDraftDate(Date designDraftDate) {
        this.designDraftDate = designDraftDate;
    }

    public Integer getSecondCalPrice() {
        return secondCalPrice;
    }

    public void setSecondCalPrice(Integer secondCalPrice) {
        this.secondCalPrice = secondCalPrice;
    }

    public String getSecondCalPriceStr() {
        return secondCalPriceStr;
    }

    public void setSecondCalPriceStr(String secondCalPriceStr) {
        this.secondCalPriceStr = secondCalPriceStr;
    }

    public Integer getHaveOrder() {
        return haveOrder;
    }

    public void setHaveOrder(Integer haveOrder) {
        this.haveOrder = haveOrder;
    }

    public Date getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(Date orderCreated) {
        this.orderCreated = orderCreated;
    }

    public String getHaveOrderStr() {
        return haveOrderStr;
    }

    public void setHaveOrderStr(String haveOrderStr) {
        this.haveOrderStr = haveOrderStr;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getWaveBandName() {
        return waveBandName;
    }

    public void setWaveBandName(String waveBandName) {
        this.waveBandName = waveBandName;
    }

    public String getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(String firstLevel) {
        this.firstLevel = firstLevel;
    }

    public String getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(String secondLevel) {
        this.secondLevel = secondLevel;
    }

    public String getThirdLevel() {
        return thirdLevel;
    }

    public void setThirdLevel(String thirdLevel) {
        this.thirdLevel = thirdLevel;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getStyleDesc() {
        return styleDesc;
    }

    public void setStyleDesc(String styleDesc) {
        this.styleDesc = styleDesc;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataSourceStr() {
        return dataSourceStr;
    }

    public void setDataSourceStr(String dataSourceStr) {
        this.dataSourceStr = dataSourceStr;
    }

    public Integer getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(Integer enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getOriginGoodsNo() {
        return originGoodsNo;
    }

    public void setOriginGoodsNo(String originGoodsNo) {
        this.originGoodsNo = originGoodsNo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSizeShapeCode() {
        return sizeShapeCode;
    }

    public void setSizeShapeCode(String sizeShapeCode) {
        this.sizeShapeCode = sizeShapeCode;
    }

    public Long getStyleTypeId() {
        return styleTypeId;
    }

    public void setStyleTypeId(Long styleTypeId) {
        this.styleTypeId = styleTypeId;
    }

    public String getTagColorCode() {
        return tagColorCode;
    }

    public void setTagColorCode(String tagColorCode) {
        this.tagColorCode = tagColorCode;
    }

    public Integer getProduceBomStatus() {
        return produceBomStatus;
    }

    public void setProduceBomStatus(Integer produceBomStatus) {
        this.produceBomStatus = produceBomStatus;
    }

    public String getProduceBomStatusStr() {
        return produceBomStatusStr;
    }

    public void setProduceBomStatusStr(String produceBomStatusStr) {
        this.produceBomStatusStr = produceBomStatusStr;
    }

    public String getOriginColorCode() {
        return originColorCode;
    }

    public void setOriginColorCode(String originColorCode) {
        this.originColorCode = originColorCode;
    }

    public String getOriginColorName() {
        return originColorName;
    }

    public void setOriginColorName(String originColorName) {
        this.originColorName = originColorName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getAccessoriesExecuteStandard() {
        return accessoriesExecuteStandard;
    }

    public void setAccessoriesExecuteStandard(String accessoriesExecuteStandard) {
        this.accessoriesExecuteStandard = accessoriesExecuteStandard;
    }

    public Integer getAlertFlag() {
        return alertFlag;
    }

    public void setAlertFlag(Integer alertFlag) {
        this.alertFlag = alertFlag;
    }

    public String getAlertFlagStr() {
        return alertFlagStr;
    }

    public void setAlertFlagStr(String alertFlagStr) {
        this.alertFlagStr = alertFlagStr;
    }

    public String getEnterpriseTypeStr() {
        return enterpriseTypeStr;
    }

    public void setEnterpriseTypeStr(String enterpriseTypeStr) {
        this.enterpriseTypeStr = enterpriseTypeStr;
    }

    public Long getSizeShapeId() {
        return sizeShapeId;
    }

    public void setSizeShapeId(Long sizeShapeId) {
        this.sizeShapeId = sizeShapeId;
    }

    public String getSizeTypeCode() {
        return sizeTypeCode;
    }

    public void setSizeTypeCode(String sizeTypeCode) {
        this.sizeTypeCode = sizeTypeCode;
    }

    public String getSizeTypeName() {
        return sizeTypeName;
    }

    public void setSizeTypeName(String sizeTypeName) {
        this.sizeTypeName = sizeTypeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<BasicGoodsVO> getSelectors() {
        return selectors;
    }

    public void setSelectors(List<BasicGoodsVO> selectors) {
        this.selectors = selectors;
    }

    public BigDecimal getBrandLimitPrice() {
        return brandLimitPrice;
    }

    public void setBrandLimitPrice(BigDecimal brandLimitPrice) {
        this.brandLimitPrice = brandLimitPrice;
    }
}