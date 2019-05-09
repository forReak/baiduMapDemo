package com.design.helpPlatform.controller;

import com.design.helpPlatform.entity.GoodsEntity;
import com.design.helpPlatform.entity.ResponseMap;
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

    @RequestMapping("/makeOrder")
    public String makeOrderView(){
        return "buy";
    }

    @RequestMapping("/addGoodsToBuyCar")
    @ResponseBody
    public ResponseMap addGoodsToBuyCarAction(String goodsId, HttpSession session){
        try {
            String buyCar = (String)session.getAttribute("buyCar");
            if(buyCar==null){
                buyCar = goodsId +",";
            }else{
                buyCar += goodsId+",";
            }
            session.setAttribute("buyCar",buyCar);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMap(false,null,e.getMessage());
        }
        return new ResponseMap(true,null,"已成功将商品"+goodsId+"加入购物车");
    }


    /**
     * 添加商品到购物车
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


}
