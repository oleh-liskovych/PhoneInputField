package us.powercode.design.phone_field.phones_utils.storage;

import java.util.List;

import us.powercode.design.phone_field.PhoneInputDropdownItem;

public interface PhonesInfoStorage {

    public void saveData(List<PhoneInputDropdownItem> data);
    public List<PhoneInputDropdownItem> getData();

}
