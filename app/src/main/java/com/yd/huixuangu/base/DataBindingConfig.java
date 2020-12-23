package com.yd.huixuangu.base;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class DataBindingConfig {
    private final int layout;
    private final int vmVariableId;
    private final ViewModel stateViewModel;
    private SparseArray bindingParams = new SparseArray();

    public DataBindingConfig(@NonNull int layout, @NonNull int vmVariableId, @NonNull ViewModel stateViewModel) {
        this.layout = layout;
        this.vmVariableId = vmVariableId;
        this.stateViewModel = stateViewModel;
    }


    public int getLayout() {
        return layout;
    }

    public int getVmVariableId() {
        return vmVariableId;
    }

    public ViewModel getStateViewModel() {
        return stateViewModel;
    }

    public SparseArray getBindingParams() {
        return bindingParams;
    }

    public DataBindingConfig addBindingParam(@NonNull Integer variableId,
                                             @NonNull Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }
}
