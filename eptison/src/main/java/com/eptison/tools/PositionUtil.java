package com.eptison.tools;

/**
 * Date: 2024/10/7
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class PositionUtil {


    /**
     * 赤道半径（单位：米）
     */
    private static final double EQUATOR_RADIUS = 6378137;


    /**
     * 方法一：（反余弦计算方式）
     *
     * @param longitude1 第一个点的经度
     * @param latitude1  第一个点的纬度
     * @param longitude2 第二个点的经度
     * @param latitude2  第二个点的纬度
     * @return 返回距离，单位m
     */
    public static double getDistance1(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lon1 = Math.toRadians(longitude1);
        double lon2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lon1 - lon2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘赤道半径, 返回单位: 米
        s = s * EQUATOR_RADIUS;
        return s;
    }



    /**
     * 地球平均半径（单位：米）
     */
    private static final double EARTH_AVG_RADIUS = 6371000;




    /**
     * 经纬度转化为弧度(rad)
     *
     * @param d 经度/纬度
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 方法三：（基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多。）
     *
     * @param longitude1 第一点的经度
     * @param latitude1  第一点的纬度
     * @param longitude2 第二点的经度
     * @param latitude2  第二点的纬度
     * @return 返回的距离，单位m
     */
    public static double getDistance2(double longitude1, double latitude1, double longitude2, double latitude2) {
        double radLat1 = rad(latitude1);
        double radLat2 = rad(latitude2);
        double a = radLat1 - radLat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_AVG_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return s;
    }

    public static void main(String[] args) {

        double longitude1 = 117.344733;
        double latitude1 = 31.912334;
        double longitude2 = 117.272186;
        double latitude2 = 31.79422;
        long startTime = System.currentTimeMillis();
        System.out.println(getDistance1(longitude1, latitude1, longitude2, latitude2));
        long endTime = System.currentTimeMillis();
        System.out.println("方法1耗时："+(endTime - startTime));


        startTime = System.currentTimeMillis();
        System.out.println(getDistance2(longitude1, latitude1, longitude2, latitude2));
        endTime = System.currentTimeMillis();
        System.out.println("方法2耗时："+(endTime - startTime));






    }

}