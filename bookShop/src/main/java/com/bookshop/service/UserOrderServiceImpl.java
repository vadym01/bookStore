package com.bookshop.service;

import com.bookshop.entities.User;
import com.bookshop.entities.UserOrder;
import com.bookshop.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    UserOrderRepository userOrderRepository;


    @Override
    public List<UserOrder> getUserOrder(User user,boolean condition) {
        return userOrderRepository.findByUserAndDisplayToUser(user,condition);
    }

//    @Override
//    public List<UserOrder> getUserOrderAdmin(boolean condition) {
//        return userOrderRepository.findByStatusDisplayAdmin(condition);
//    }

    @Override
    public List<UserOrder> userOrderForAdmin(){
        return userOrderRepository.userOrderForAdmin();
    }

    @Override
    public Page<UserOrder> getUserOrderAdmin(Pageable pageable,List<UserOrder> userOrder) {
        List<UserOrder> userOrderList = userOrder;
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<UserOrder> list;

        if (userOrderList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, userOrderList.size());
            list = userOrderList.subList(startItem, toIndex);
        }

        Page<UserOrder> userOrders
                = new PageImpl<UserOrder>(list, PageRequest.of(currentPage, pageSize), userOrderList.size());
        return userOrders;
    }







    @Override
    public void changeStatusToOrder(Long id) {
        Optional<UserOrder> userOrder = userOrderRepository.findById(id);
        if(userOrder.isPresent()){
            UserOrder order = userOrder.get();
            order.setDisplayToUser(false);
            userOrderRepository.save(order);
            if(!order.isDisplayToUser() && order.isDisplayToAdmin() == false){
                userOrderRepository.deleteById(id);
            }
        }
    }


    @Override
    public void changeStatusToAdminOrder(Long id) {
        Optional<UserOrder> userOrder = userOrderRepository.findById(id);
        if(userOrder.isPresent()){
            UserOrder order = userOrder.get();
            order.setDisplayToAdmin(false);
            userOrderRepository.save(order);
            if(!order.isDisplayToUser() && order.isDisplayToAdmin() == false){
                userOrderRepository.deleteById(id);
            }
        }
    }

    @Override
    public void changeStatusForOrderAdmin(Long id) {
        Optional<UserOrder> userOrder = userOrderRepository.findById(id);
        if (userOrder.isPresent()) {
            UserOrder orderStatus = userOrder.get();
            if (!orderStatus.isOrderStatus()) {
                orderStatus.setOrderStatus(true);
            }
         else {
            orderStatus.setOrderStatus(false);
        }
         userOrderRepository.save(orderStatus);
    }
}


//    @Override
//    public void changeStatusToAdminOrder(Long id) {
//         userOrderRepository.deleteById(id);
//    }
    }
