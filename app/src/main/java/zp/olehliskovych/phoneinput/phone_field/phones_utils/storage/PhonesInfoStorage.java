package zp.olehliskovych.phoneinput.phone_field.phones_utils.storage;

import java.util.List;

import zp.olehliskovych.phoneinput.phone_field.PhoneInputDropdownItem;

public interface PhonesInfoStorage {

    public void saveData(List<PhoneInputDropdownItem> data);
    public List<PhoneInputDropdownItem> getData();

}
