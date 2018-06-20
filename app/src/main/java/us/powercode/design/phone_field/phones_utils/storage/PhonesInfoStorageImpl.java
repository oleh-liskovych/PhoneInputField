package us.powercode.design.phone_field.phones_utils.storage;

import java.util.ArrayList;
import java.util.List;

import us.powercode.design.phone_field.PhoneInputDropdownItem;

public class PhonesInfoStorageImpl implements PhonesInfoStorage {

    private static volatile PhonesInfoStorageImpl instance;

    private List<PhoneInputDropdownItem> data;

    private PhonesInfoStorageImpl() {}

    public static PhonesInfoStorageImpl getInstance() {
        if (instance == null) {
            synchronized (PhonesInfoStorageImpl.class) {
                if (instance == null) {
                    instance = new PhonesInfoStorageImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void saveData(List<PhoneInputDropdownItem> data) {
        data = new ArrayList<>(data);
    }

    @Override
    public List<PhoneInputDropdownItem> getData() {
        return data;
    }
}
