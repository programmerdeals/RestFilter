package com.trystwithtomm.rest.restfilter.mock.service;

import com.trystwithtomm.rest.restfilter.domain.Address;
import com.trystwithtomm.rest.restfilter.domain.User;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        DataFactory df = new DataFactory();
        df.randomize(new Random().nextInt());
        User user = new User();
        Address address = new Address();
        for (int i = 0; i < 5; i++) {
            user.setFirstName(df.getFirstName());
            user.setLastName(df.getLastName());
            address.setAddress1(df.getAddress());
            address.setAddress2(df.getAddressLine2());
            address.setCity(df.getCity());
            address.setZipCode(df.getNumberText(5));
            user.setAddress(address);
            users.add(user);
        }

        return users;
    }
}
