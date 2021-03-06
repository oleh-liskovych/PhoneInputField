package zp.olehliskovych.phoneinput.phone_field;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.NonNull;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.Locale;

import zp.olehliskovych.phoneinput.R;
import zp.olehliskovych.phoneinput.databinding.PhoneInputEditTextBinding;
import zp.olehliskovych.phoneinput.phone_field.adapter.BasePhoneFieldAdapter;

public class PhoneInputEditText extends LinearLayout implements BasePhoneFieldAdapter.AdapterCallback {

    private final TextWatcher phoneNumberFormatter = new PhoneNumberFormattingTextWatcher(getPrimaryLocale().getCountry());
    private PhoneInputEditTextBinding binding;
//    private PhonesInfoDataSource dataSource;

    public PhoneInputEditText(Context context) {
        this(context, null, 0);
    }

    public PhoneInputEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhoneInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(LayoutInflater.from(context));
        getAttrs(context, attrs);
        init();
    }

    public void setAdapter(BasePhoneFieldAdapter adapter) {
        binding.textview.setAdapter(adapter);
        binding.textview.setOnItemClickListener((adapterView, view, i, l) -> {
            PhoneInputDropdownItem item = (PhoneInputDropdownItem) adapterView.getItemAtPosition(i);
            updateUI(item);
        });
    }

    private void inflateView(@NonNull LayoutInflater inflater) {
        binding = DataBindingUtil.inflate(inflater, R.layout.phone_input_edit_text, this, true);
    }

    private void init() {
        addTextChangedListener();

//        setupAdapter(context);

    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PhoneInputEditText, 0, 0);
        try {
            setHint(array.getString(R.styleable.PhoneInputEditText_android_hint));
            setText(array.getString(R.styleable.PhoneInputEditText_android_text));
            setTextSize(array.getString(R.styleable.PhoneInputEditText_android_textSize));
            boolean iconIsVisible = (array.getBoolean(R.styleable.PhoneInputEditText_flag_is_visible, true));

            showFlagIcon(iconIsVisible);
        } finally {
            array.recycle();
        }
    }

    public static int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public void showFlagIcon(boolean showIcon) {
        int iconVisibility = showIcon ? VISIBLE : GONE;
        binding.flagIcon.setVisibility(iconVisibility);
    }

    @Override
    public void updateUI(PhoneInputDropdownItem item) {
        binding.flagIcon.setImageResource(item.getFlag());
    }

    @Override
    public void clearIcon() {
        binding.flagIcon.setImageResource(android.R.color.transparent);
    }

    public void setHint(CharSequence hint) {
        binding.textview.setHint(hint);
    }
    public void setText(CharSequence text) {
        binding.textview.setText(text);
    }

    public void setTextSize(String rawSize) {
        if (rawSize == null) return;
        String temp = rawSize.replaceAll("[^0-9.]", ""); // "(?:sp|dp|dip)"
        float size = Float.parseFloat(temp);
        binding.textview.setTextSize(size);
    }

    public void setTextSize(int size) {
        binding.textview.setTextSize(size);
    }

    public CharSequence getHint() {
        return binding.textview.getHint();
    }

    private void addTextChangedListener() {
        binding.textview.addTextChangedListener(phoneNumberFormatter);
    }

    @SuppressWarnings("deprecation")
    @NonNull
    private static Locale getPrimaryLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            return Resources.getSystem().getConfiguration().locale;
        }
    }

}
