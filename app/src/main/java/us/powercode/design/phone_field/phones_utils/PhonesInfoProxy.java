package us.powercode.design.phone_field.phones_utils;

import android.content.Context;
import android.support.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import us.powercode.design.MyApplication;
import us.powercode.design.phone_field.PhoneInputDropdownItem;

public class PhonesInfoProxy implements PhonesInfoDataSource {

    private PhoneNumberUtil phoneNumberUtil;
    private Context context;

    // todo: pass PhonesInfoStorage through constructor
    public PhonesInfoProxy(Context context) {
        this.context = context;
        this.phoneNumberUtil = PhoneNumberUtil.createInstance(context);

    }

    @Override
    public List<PhoneInputDropdownItem> getData() {

        MyApplication app = MyApplication.getApplication(context);
        List<PhoneInputDropdownItem> data = app.phonesInfoStorage.getData();

        if (data == null || data.isEmpty()) {
            Set<Integer> countryCodes = phoneNumberUtil.getSupportedCallingCodes();
            data = new ArrayList<>();
            for (Integer code : countryCodes) {
                String characterCode = phoneNumberUtil.getRegionCodeForCountryCode(code);
                Locale loc = new Locale("", characterCode);
                PhoneInputDropdownItem item = new PhoneInputDropdownItem("+"+code.toString(), loc.getDisplayCountry(), getDrawableIdByName(characterCode));
                data.add(item);
            }
            app.phonesInfoStorage.saveData(data);
        }

        return data;
    }


    private @DrawableRes int getDrawableIdByName(final String name) {
        return context.getResources().getIdentifier("flag_" + name.toLowerCase(), "drawable", context.getPackageName());
    }
}
