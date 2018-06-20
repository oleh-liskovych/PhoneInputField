package us.powercode.design.phone_field;

import android.support.annotation.DrawableRes;

public class PhoneInputDropdownItem {
    private String code;
    private String country;
    private @DrawableRes int flag;

    public PhoneInputDropdownItem(String code, String country, int flag) {
        this.code = code;
        this.country = country;
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public int getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return code;
    }
}
