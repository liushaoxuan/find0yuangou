package com.ahxd.lingyuangou.permission;

public abstract class PermissionResultAdapter implements PermissionResultCallBack {

    @Override
    public void onPermissionGranted() {

    }

    @Override
    public void onPermissionGranted(String... permissions) {
        StringBuilder builder = new StringBuilder();
        for (String permission : permissions) {
            builder.append(permission.substring(permission.lastIndexOf(".") + 1) + " ");
        }
    }

    @Override
    public void onPermissionDenied(String... permissions) {
        StringBuilder builder = new StringBuilder();
        for (String permission : permissions) {
            builder.append(permission.substring(permission.lastIndexOf(".") + 1) + " ");
        }
    }

    @Override
    public void onRationalShow(String... permissions) {
        StringBuilder builder = new StringBuilder();
        for (String permission : permissions) {
            builder.append(permission.substring(permission.lastIndexOf(".") + 1) + " ");
        }
    }
}