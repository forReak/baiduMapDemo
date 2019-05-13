package com.design.helpPlatform.service.serviceImpl;

import com.design.helpPlatform.dao.OrderDao;
import com.design.helpPlatform.dao.OrderDetailDao;
import com.design.helpPlatform.entity.OrderDetailEntity;
import com.design.helpPlatform.entity.OrderEntity;
import com.design.helpPlatform.entity.OrderInfo;
import com.design.helpPlatform.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.design.helpPlatform.util.Util.getOrderNo;


@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    OrderDao orderDao;

    @Override
    public int orderNow(OrderInfo orderInfo, HttpSession session) {
        int userId = (Integer)session.getAttribute("userId");

        //保存订单主表
        String orderNo = getOrderNo(userId);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(orderNo);
        orderEntity.setOrderOwner(userId);
        orderEntity.setStartPos(orderInfo.getSendAddress());
        orderEntity.setStartDetailPos(orderInfo.getSendAddressDetail());
        orderEntity.setStartGps(orderInfo.getSendAddressPos());
        orderEntity.setSendName(orderInfo.getSendName());
        orderEntity.setSendPhone(orderInfo.getSendPhone());
        orderEntity.setEndPos(orderInfo.getReciveAddress());
        orderEntity.setEndDetailPos(orderInfo.getReciveAddressDetail());
        orderEntity.setEndGps(orderInfo.getReciveAddressPos());
        orderEntity.setReciveName(orderInfo.getReciveName());
        orderEntity.setRecivePhone(orderInfo.getRecivePhone());
        orderEntity.setOrderType("1");
        orderEntity.setOrderStatus(10);
        orderEntity.setOrderCreateTime(new Timestamp(System.currentTimeMillis()));
        orderEntity.setSendType("1");
        orderEntity.setSendRemark(orderInfo.getSendRemark());
        orderEntity.setOrderCity(orderInfo.getCountry());
        orderEntity.setOrderPri(orderInfo.getState());
        orderEntity.setOrderZip(orderInfo.getZip());
        int i = orderDao.insertOrderDao(orderEntity);

        if(i>0){
            //保存详情表
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrderNo(orderNo);
            orderDetailEntity.setOrderWeight(new BigDecimal(orderInfo.getWeight()));
            orderDetailEntity.setGoodsType(orderInfo.getGoodsType());
            orderDetailEntity.setGoodsPrice(orderInfo.getGoodsPrice());
            orderDetailEntity.setGoodsRemark(orderInfo.getGoodsRemark());
            int insert = orderDetailDao.insert(orderDetailEntity);
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public List<OrderEntity> getOrderEntity(Integer userId) {
        List<OrderEntity> orderEntityList = orderDao.getOrderEntityByUserId(userId);
        return orderEntityList;
    }

    @Override
    public List<OrderEntity> getWaitOrderEntity() {
        List<OrderEntity> orderEntityList = orderDao.getAllWaitOrderEntity();
        return orderEntityList;
    }

    @Override
    public OrderEntity getOrderByRider(Integer userId) {
        OrderEntity orderEntity = orderDao.getOrderEntityByRider(userId);
        return orderEntity;
    }

    @Override
    public OrderEntity getOrderByOrderNo(String orderNo) {
        OrderEntity orderEntity = orderDao.getOrderEntityByOrderNo(orderNo);
        return orderEntity;
    }

    @Override
    public int updateOrderToSending(String orderNo, Integer userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("orderNo",orderNo);
        map.put("userId",userId);
        map.put("time",new Timestamp(System.currentTimeMillis()));
        int i = orderDao.updateOrderToSending(map);
        return i;
    }

    @Override
    public int updateOrderFinish(String orderNo) {
        Map<String,Object> map = new HashMap<>();
        map.put("orderNo",orderNo);
        map.put("time",new Timestamp(System.currentTimeMillis()));
        int i = orderDao.updateOrderFinish(map);
        return i;
    }

    @Override
    public List<OrderDetailEntity> getOrderDetailbyOrderNo(String orderNo) {
        List<OrderDetailEntity> orderDetailEntityList = orderDetailDao.getOrderDetailByOrderNo(orderNo);
        return orderDetailEntityList;
    }


}
