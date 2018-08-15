package zp.olehliskovych.phoneinput.phone_field.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

import zp.olehliskovych.phoneinput.phone_field.PhoneInputDropdownItem;

public abstract class BasePhoneFieldAdapter extends ArrayAdapter{

    public interface AdapterCallback {
        void updateUI(PhoneInputDropdownItem item);
        void clearIcon();
    }

    protected AdapterCallback communicator;

    public void setCommunicator(AdapterCallback communicator) {
        this.communicator = communicator;
    }

    public BasePhoneFieldAdapter(@NonNull Context context, int resource, @NonNull List<PhoneInputDropdownItem> dataList) {
        super(context, resource, dataList);
    }
}
