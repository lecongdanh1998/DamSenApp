package vn.edu.poly.damsenapp.Model.UpdaterInfomation;

public interface UpdaterInformationModel {

    interface OnUpdateListener {
        void updateInfo(int success, String message);
    }

    void updateUser(OnUpdateListener listener, String name, String email,
                    String phone, String password, String confirmPassword);
}
