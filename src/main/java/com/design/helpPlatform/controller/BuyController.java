package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.BuyInfoEntity;
import com.design.helpPlatform.entity.GoodsEntity;
import com.design.helpPlatform.entity.ResponseMap;
import com.design.helpPlatform.service.BuyService;
import com.design.helpPlatform.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

@Controller
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    ShopService shopService;
    @Autowired
    BuyService buyService;


    @RequestMapping("/makeOrder")
    public String makeOrderView(){
        return "buy";
    }



    /**
     * 添加商品到购物车
     *      * 当前台页面点击购物车的小按钮后，
     *      * 会自动发动一个请求到后台，传递的就是商品的id
     *      购物车的实现思路是通过springmvc的session进行实现的
     * @param goodsId 入参参数：商品id
     * @param session 缓存
     * @return
     */
    @RequestMapping("/addGoodsToBuyCar")
    @ResponseBody
    public ResponseMap addGoodsToBuyCarAction(String goodsId, HttpSession session){
        try {
            //我们通过程序，获取到缓存
            String buyCar = (String)session.getAttribute("buyCar");
            //如果缓存是空，那么我们就当当前的id保存成字符串的格式：'17，'
            if(buyCar==null){
                buyCar = goodsId +",";
            }else{
                //如果缓存不是空，那么我们就将当前的商品信息追加到缓存后去
                //'1，6，3，2，2，7，19,17，'
                buyCar += goodsId+",";
            }
            //重新将我们编辑好的字符串放入缓存中。放入缓存中后，就成功保存了购物车信息
            //注意的是，缓存只在当前程序启动中才有效果。重启程序或终断后，缓存都会消失
            session.setAttribute("buyCar",buyCar);


        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,null,e.getMessage());
        }
        return new ResponseMap(true,null,"已成功将商品"+goodsId+"加入购物车");
    }


    /**
     * 获取购物车信息
     * @param session
     * @return
     */
    @RequestMapping("/getBuyCarInfo")
    @ResponseBody
    public ResponseMap getBuyCarInfoACtion(HttpSession session){

        try {
            Object buyCar = session.getAttribute("buyCar");
            if(buyCar!=null){
                String buyCarStr = (String)buyCar;
                String[] split = buyCarStr.split(",");
                List<String> goodsList = Arrays.asList(split);
                Map<String, Long> collect = goodsList.stream().collect(Collectors.groupingBy(Function.identity(), counting()));
                Set<String> goodsIdSet = new HashSet<>(goodsList);

                if(collect!=null){
                    //查询物品信息
                    List<GoodsEntity> goodsEntityList = shopService.getGoodsByGoodsIds(goodsIdSet);
                    Map<String,Object> maps = new HashMap<>();
                    maps.put("goodsNum",collect);
                    maps.put("goodsInfo",goodsEntityList);

                    return new ResponseMap(true,maps,"success");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,null,e.getMessage());

        }
        return new ResponseMap(true,null,"您当前购物车为空！");

    }


    @RequestMapping("/resetCarSession")
    @ResponseBody
    public ResponseMap resetCarSession(String goodsNum,HttpSession session){
        try {
            session.setAttribute("buyCar",goodsNum);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,null,e.getMessage());

        }
        return new ResponseMap(true,"success","success");

    }

    /**
     * 删除购物车缓存
     * @param session
     * @return
     */
    @RequestMapping("/flushCarSession")
    @ResponseBody
    public ResponseMap flushCarSession(HttpSession session){
        try {
            session.setAttribute("buyCar","");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,null,e.getMessage());

        }
        return new ResponseMap(true,"success","success");

    }


    /**
     * 代买下单
     * @return
     */
    @RequestMapping("/orderNow")
    @ResponseBody
    public ResponseMap orderNow(BuyInfoEntity buyInfoEntity , HttpSession session){

        try {
            boolean flag = buyService.orderNow(buyInfoEntity,session);
            if(flag){
                //如果下单成功，清空购物车
                session.setAttribute("buyCar","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,null,e.getMessage());
        }
        return new ResponseMap(true,"success","创建订单成功！");
    }


}
