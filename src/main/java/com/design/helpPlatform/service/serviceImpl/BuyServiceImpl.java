package com.design.helpPlatform.service.serviceImpl;

import com.design.helpPlatform.dao.*;
import com.design.helpPlatform.entity.*;
import com.design.helpPlatform.service.BuyService;
import com.design.helpPlatform.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.design.helpPlatform.util.Util.getOrderNo;
import static java.util.stream.Collectors.counting;

@Service
@Transactional
public class BuyServiceImpl implements BuyService {

    @Autowired
    GoodsDao goodsDao;
    @Autowired
    ShopDao shopDao;
    @Autowired
    UserDao userDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderDetailDao orderDetailDao;


    @Override
    public boolean orderNow(BuyInfoEntity buyInfoEntity, HttpSession session) {

        int userId = (Integer)session.getAttribute("userId");

        String goodsStr = (String) session.getAttribute("buyCar");

        String[] goodsArr = goodsStr.split(",");
        Map<String, Long> collect = Arrays.stream(goodsArr).collect(Collectors.groupingBy(Function.identity(), counting()));

        Set<String> goodsSet = new HashSet<>(Arrays.asList(goodsArr));

        //获取商品信息
        List<GoodsEntity> goodsEntityList = goodsDao.getGoodsByGoodsIds(goodsSet);

        //获取用户信息
        User user = userDao.getUserById(userId);

        //获取商家id
        Integer shopId = goodsEntityList.get(0).getShopId();

        //获取商家信息
        ShopEntity shopEntity = shopDao.getShopInfoById(shopId);

        OrderEntity order = new OrderEntity();

        //获取订单号
        String orderNo = getOrderNo(userId);
        order.setOrderNo(orderNo);
        order.setOrderOwner(userId);
        order.setStartPos(shopEntity.getShopAddr());
        order.setEndPos(buyInfoEntity.getReciveAddress());
        order.setStartGps(buyInfoEntity.getStartPos());
        order.setEndGps(buyInfoEntity.getRecivePos());
        order.setSendName(shopEntity.getShopName());
//        orderEntity.setSendPhone(shopEntity.get);
        order.setReciveName(buyInfoEntity.getReciveName());
        order.setRecivePhone(buyInfoEntity.getRecivePhone());
        order.setOrderType("2");
        order.setOrderStatus(10);
        order.setOrderCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setStartDetailPos(shopEntity.getShopAddr());
        order.setEndDetailPos(buyInfoEntity.getReciveAddressDetail());
//        orderEntity.setSendType();
//        int returnInt = orderDao.ioi(order);
        int i = orderDao.insertOrderDao(order);
        if(i>0){

            List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
            collect.forEach((k,v) -> {
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                orderDetailEntity.setOrderNo(orderNo);
                Optional<GoodsEntity> goodsEntityOp = goodsEntityList.stream().filter(e -> String.valueOf(e.getId()).equals(k)).findAny();
                if(goodsEntityOp.isPresent()){
                    GoodsEntity curGoods = goodsEntityOp.get();
                    orderDetailEntity.setGoodsName(curGoods.getGoodsName());
                    orderDetailEntity.setGoodsMoney(curGoods.getGoodsPrice());
                    orderDetailEntity.setGoodsPic(curGoods.getGoodsPic());
                    orderDetailEntity.setGoodsNum(Integer.valueOf(v.toString()));
                    orderDetailEntities.add(orderDetailEntity);
                }
            });
            if(orderDetailEntities.size()==0){
                throw new RuntimeException("无法获取物品信息！无法创建订单字表！");
            }
            //插入订单字表数据
            int j = orderDetailDao.insertBeach(orderDetailEntities);
            if(j>0){
                return true;
            }else{
                throw new RuntimeException("无法新增订单子表！没有任何数据需要插入！");

            }

        }else{
            throw new RuntimeException("无法新增订单表！程序异常！");
        }
    }
}
