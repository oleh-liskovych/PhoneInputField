package zp.olehliskovych.phoneinput.phone_field.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zp.olehliskovych.phoneinput.R;
import zp.olehliskovych.phoneinput.phone_field.PhoneInputDropdownItem;

public class PhoneFieldAdapter extends BasePhoneFieldAdapter {
    private List<PhoneInputDropdownItem> dataList;
    private @LayoutRes int itemLayout;

    private ListFilter listFilter = new ListFilter();
    private List<PhoneInputDropdownItem> dataListAllItems;

    public PhoneFieldAdapter(@NonNull Context context, int resource, @NonNull List<PhoneInputDropdownItem> dataList) {
        super(context, resource, dataList);
        this.dataList = dataList;
        this.itemLayout = resource;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        }

        TextView code = view.findViewById(R.id.code);
        TextView country = view.findViewById(R.id.country);
        ImageView flag = view.findViewById(R.id.icon);

        code.setText(dataList.get(position).getCode());
        country.setText(dataList.get(position).getCountry());
        flag.setImageResource(dataList.get(position).getFlag());

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    private class ListFilter extends Filter {

        private final Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {

            String search = (prefix == null) ? null : prefix.toString().replaceAll(" ", "");

            FilterResults results = new FilterResults();
            if (dataListAllItems == null) {
                synchronized (lock) {
                    dataListAllItems = new ArrayList<>(dataList);
                }
            }

            if (search == null || search.length() == 0) {
                synchronized (lock) {
                    results.values = dataListAllItems;
                    results.count = dataListAllItems.size();
                }
            } else {
                ArrayList<PhoneInputDropdownItem> matchValues = new ArrayList<>();
                for (PhoneInputDropdownItem dataItem : dataListAllItems) {
                    if (dataItem.getCode().startsWith(search)) {
                        matchValues.add(dataItem);
                        if (dataItem.getCode().equals(search)) {
                            updateIcon(dataItem);
                        } else {
                            clearIcon();
                        }
                    }
                }

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        private void clearIcon() {
            Handler mainHandler = new Handler(Looper.getMainLooper());

            Runnable myRunnable = () -> {
                if (communicator != null)
                    communicator.clearIcon();
            };
            mainHandler.post(myRunnable);
        }

        private void updateIcon(PhoneInputDropdownItem item) {
            Handler mainHandler = new Handler(Looper.getMainLooper());

            Runnable myRunnable = () -> {
                if (communicator != null)
                    communicator.updateUI(item);
            };
            mainHandler.post(myRunnable);
        }

        /**
         * {@link FilterResults#values} has type Object which requires cast to List<PhoneInputDropdownItem>
         * @param charSequence
         * @param filterResults
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults.values != null) {
                dataList = (List<PhoneInputDropdownItem>) filterResults.values;
            } else {
                dataList = null;
            }
            if (filterResults.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
