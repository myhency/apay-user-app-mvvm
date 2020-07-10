package com.autoever.apay_user_app.ui.base;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

    public interface Callback {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
