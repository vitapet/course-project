package com.gmail.vitaliapetsenak.shop.service.converter;

import com.gmail.vitaliapetsenak.shop.repository.model.User;
import com.gmail.vitaliapetsenak.shop.repository.model.UserAddress;
import com.gmail.vitaliapetsenak.shop.repository.model.UserInfo;
import com.gmail.vitaliapetsenak.shop.repository.model.UserStatus;
import com.gmail.vitaliapetsenak.shop.service.model.UserDTO;
import com.gmail.vitaliapetsenak.shop.service.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    @Autowired
    private DateFormatter dateFormatter;

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = UserDTO.newBuilder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .role(user.getRole())
                .status(UserStatus.valueOf(user.getStatus().toString()))
                .build();
        if (user.getUserInfo() != null) {
            UserInfo info = user.getUserInfo();
            setInfoToDTO(userDTO, info);
            if (info.getAddress() != null) {
                UserAddress address = info.getAddress();
                setAddressToDTO(userDTO, address);
            }
        }
        return userDTO;
    }

    public void convert(User user, UserDTO userDTO) throws ParseException {
        if (user.getUserInfo() == null) {
            user.setUserInfo(new UserInfo());
            user.setLogin(userDTO.getLogin());
        }
        if (userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword());
        }
        convertInfo(user.getUserInfo(), userDTO);
        convertAddress(user.getUserInfo(), userDTO);
        if (user.getId() == null) {
            if (user.getUserInfo() != null) {
                user.getUserInfo().setUser(user);
                if (user.getUserInfo().getAddress() != null) {
                    user.getUserInfo().getAddress().setInfo(user.getUserInfo());
                }
            }
        } else {
            if (user.getUserInfo().getAddress() != null) {
                user.getUserInfo().getAddress().setInfo(user.getUserInfo());
            }
        }
    }

    private void convertInfo(UserInfo info, UserDTO userDTO) throws ParseException {
        info.setFirstName(userDTO.getFirstName());
        info.setSurname(userDTO.getSurname());
        info.setBirthDate(dateFormatter.parse(userDTO.getBirthDate()));
        info.setPhone(userDTO.getPhone());
    }

    private void convertAddress(UserInfo info, UserDTO userDTO) {
        if (!isAddressFieldsNull(userDTO)) {
            if (info.getAddress() == null) {
                info.setAddress(new UserAddress());
            }
            UserAddress address = info.getAddress();
            address.setCountry(userDTO.getCountry());
            address.setCity(userDTO.getCity());
            address.setStreet(userDTO.getStreet());
            address.setHouse(userDTO.getHouse());
            if (userDTO.getBlock() != null && !userDTO.getBlock().isEmpty()) {
                address.setBlock(Integer.valueOf(userDTO.getBlock()));
            } else {
                address.setBlock(null);
            }
            if (userDTO.getApartment() != null && !userDTO.getApartment().isEmpty()) {
                address.setApartment(Integer.valueOf(userDTO.getApartment()));
            } else {
                address.setApartment(null);
            }
        } else {
            info.setAddress(null);
        }
    }

    private boolean isAddressFieldsNull(UserDTO userDTO) {
        if (userDTO.getCountry().isEmpty() &&
                userDTO.getCity().isEmpty() &&
                userDTO.getStreet().isEmpty() &&
                userDTO.getHouse().isEmpty() &&
                userDTO.getBlock().isEmpty() &&
                userDTO.getApartment().isEmpty()) {
            return true;
        }
        return false;
    }

    private void setAddressToDTO(UserDTO userDTO, UserAddress address) {
        if (userDTO.getId() != null) {
            address.setUserId(userDTO.getId());
        }
        userDTO.setCountry(address.getCountry());
        userDTO.setCity(address.getCity());
        userDTO.setStreet(address.getStreet());
        userDTO.setHouse(address.getHouse());
        if (address.getBlock() == null) {
            userDTO.setBlock(null);
        } else {
            userDTO.setBlock(address.getBlock().toString());
        }
        if (address.getApartment() == null) {
            userDTO.setApartment(null);
        } else {
            userDTO.setApartment(address.getApartment().toString());
        }
    }

    private void setInfoToDTO(UserDTO userDTO, UserInfo info) {
        if (userDTO.getId() != null) {
            info.setUserIid(userDTO.getId());
        }
        userDTO.setFirstName(info.getFirstName());
        userDTO.setSurname(info.getSurname());
        userDTO.setBirthDate(dateFormatter.format(info.getBirthDate()));
        userDTO.setPhone(info.getPhone());
    }

    public List<UserDTO> usersToDTO(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                userDTOS.add(convertToDTO(user));
            }
            return userDTOS;
        }
        return null;
    }
}
