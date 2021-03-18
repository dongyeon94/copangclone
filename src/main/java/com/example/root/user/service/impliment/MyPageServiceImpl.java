package com.example.root.user.service.impliment;

import com.example.root.user.service.interfaces.MypageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageServiceImpl implements MypageService {
    @Override
    public List buyList() {
        return null;
    }

    @Override
    public int deliveryProductCount() {
        return 0;
    }

    @Override
    public int copangMoney() {
        return 0;
    }

    @Override
    public int copangPoint() {
        return 0;
    }

    @Override
    public int couponCount() {
        return 0;
    }
}
