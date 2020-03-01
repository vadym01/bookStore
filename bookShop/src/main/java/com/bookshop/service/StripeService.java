package com.bookshop.service;

import com.bookshop.entities.CartExtensions;
import com.bookshop.entities.User;
import com.bookshop.entities.UserCart;
import com.bookshop.entities.UserOrder;
import com.stripe.exception.*;
import com.stripe.model.Charge;

import java.util.List;

public interface StripeService {


    void chargeNewCard(UserOrder userOrder, User user, UserCart userCart) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException;
}
